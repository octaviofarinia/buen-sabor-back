package com.tup.buensabor.services;

import com.tup.buensabor.dtos.detallepedido.AltaPedidoDetallePedidoDto;
import com.tup.buensabor.dtos.detallepedido.DetallePedidoSimpleDto;
import com.tup.buensabor.dtos.pedido.AltaPedidoDto;
import com.tup.buensabor.dtos.pedido.PedidoDto;
import com.tup.buensabor.entities.*;
import com.tup.buensabor.enums.EstadoPedido;
import com.tup.buensabor.enums.FormaPago;
import com.tup.buensabor.enums.TipoEnvio;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.PedidoMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.DetallePedidoRepository;
import com.tup.buensabor.repositories.PedidoRepository;
import com.tup.buensabor.services.interfaces.PedidoService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Log4j2
@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido, PedidoDto, Long> implements PedidoService {

    @Autowired
    private DomicilioServiceImpl domicilioService;

    @Autowired
    private ArticuloManufacturadoServiceImpl articuloManufacturadoService;

    @Autowired
    private ArticuloInsumoServiceImpl articuloInsumoService;

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private FacturaServiceImpl facturaService;

    @Autowired
    private DetallePedidoServiceImpl detallePedidoService;

    @Autowired
    private MailService mailService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    public PedidoServiceImpl(BaseRepository<Pedido, Long> baseRepository, BaseMapper<Pedido, PedidoDto> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Transactional(rollbackOn = ServicioException.class)
    public Pedido altaPedido(AltaPedidoDto altaPedidoDto) throws ServicioException {
        Pedido pedido = new Pedido();

        AtomicReference<BigDecimal> totalCostoAtomicReference = new AtomicReference<>(BigDecimal.ZERO);
        AtomicReference<BigDecimal> totalAtomicReference = new AtomicReference<>(BigDecimal.ZERO);

        List<DetallePedido> detallesPedido = getValidDetallesPedido(altaPedidoDto.getProductos(), totalAtomicReference, totalCostoAtomicReference);
        BigDecimal total = totalAtomicReference.get();
        BigDecimal totalCosto = totalCostoAtomicReference.get();


        Domicilio domicilio = null;
        if (!TipoEnvio.TAKE_AWAY.equals(altaPedidoDto.getTipoEnvio())) {
            Optional<Domicilio> domicilioOptional = domicilioService.findOptionalById(altaPedidoDto.getIdDomicilioEntrega());
            if (domicilioOptional.isEmpty()) {
                throw new ServicioException("No se encontro un domicilio con el id " + altaPedidoDto.getIdDomicilioEntrega());
            }
            domicilio = domicilioOptional.get();
        }

        Optional<Cliente> clienteOptional = clienteService.findOptionalByAuth0Id(altaPedidoDto.getAuth0Id());
        if (clienteOptional.isEmpty()) {
            throw new ServicioException("No se encontro un cliente con el Auth0Id " + altaPedidoDto.getAuth0Id());
        }
        Cliente cliente = clienteOptional.get();

        if (FormaPago.EFECTIVO.equals(altaPedidoDto.getFactura().getFormaPago())) {
            pedido.setEstado(EstadoPedido.PENDIENTE_PAGO);
        } else {
            pedido.setEstado(EstadoPedido.PAGADO);
        }

        LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.UTC).plus(Duration.of(altaPedidoDto.getTiempoEstimadoFinalizacion(), ChronoUnit.MINUTES));
        Date horaEstimadaFinalizacion = Date.from(dateTime.toInstant(ZoneOffset.UTC));

        Instant now = Instant.now();
        Date fechaPedido = Date.from(now);

        pedido.setDomicilioEntrega(domicilio);
        pedido.setCliente(cliente);
        pedido.setFechaPedido(fechaPedido);
        pedido.setHoraEstimadaFinalizacion(horaEstimadaFinalizacion);
        pedido.setTipoEnvio(altaPedidoDto.getTipoEnvio());
        pedido.setFormaPago(altaPedidoDto.getFactura().getFormaPago());
        pedido.setTotalCosto(totalCosto);
        pedido.setTotal(total);

        pedido.setFechaAlta(fechaPedido);

        pedido = this.save(pedido);

        for (DetallePedido detallePedido : detallesPedido) {
            detallePedido.setPedido(pedido);
            detallePedidoService.save(detallePedido);
            articuloInsumoService.restarStock(detallePedido);
        }

        if (!FormaPago.EFECTIVO.equals(altaPedidoDto.getFactura().getFormaPago())) {
            facturaService.saveFacturaFromPedidoAlta(pedido, detallesPedido, altaPedidoDto.getFactura());
        }

        try {
            this.mailService.sendAltaPedidoEmail(cliente.getEmail(), pedido);
        } catch (Exception e) {
            log.error(e);
        }

        return pedido;
    }

    private List<DetallePedido> getValidDetallesPedido(List<AltaPedidoDetallePedidoDto> productos, AtomicReference<BigDecimal> totalReference, AtomicReference<BigDecimal> totalCostoReference) throws ServicioException {
        if (productos.size() == 0) {
            throw new ServicioException("Debe seleccionar al menos un producto para realizar un pedido.");
        }

        BigDecimal total = BigDecimal.ZERO;
        BigDecimal totalCosto = BigDecimal.ZERO;

        List<DetallePedido> detallesPedido = new ArrayList<>();
        for (AltaPedidoDetallePedidoDto detalleAlta : productos) {
            Optional<ArticuloManufacturado> articuloManufacturadoOptional = articuloManufacturadoService.findOptionalById(detalleAlta.getIdArticuloManufacturado());
            if (articuloManufacturadoOptional.isEmpty()) {
                throw new ServicioException("No existe un articulo manufacturado con el id " + detalleAlta.getIdArticuloManufacturado() + ".");
            }

            Integer cantidad = detalleAlta.getCantidad();
            if (cantidad == null || cantidad <= 0) {
                throw new ServicioException("No se puede seleccionar una cantidad menor o igual a cero - id: " + detalleAlta.getIdArticuloManufacturado() + "  cantidad: " + cantidad);
            }

            ArticuloManufacturado articuloManufacturado = articuloManufacturadoOptional.get();

            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setArticuloManufacturado(articuloManufacturado);
            detallePedido.setCantidad(cantidad);
            detallePedido.setSubtotal(articuloManufacturado.getPrecioVenta().multiply(new BigDecimal(cantidad)));
            detallePedido.setSubtotalCosto(articuloManufacturado.getCosto().multiply(new BigDecimal(cantidad)));

            detallesPedido.add(detallePedido);

            total = total.add(detallePedido.getSubtotal());
            totalCosto = totalCosto.add(detallePedido.getSubtotalCosto());
        }

        totalReference.set(total);
        totalCostoReference.set(totalCosto);

        return detallesPedido;
    }

    @Transactional(rollbackOn = ServicioException.class)
    public void updateEstado(Long id, EstadoPedido estado) throws ServicioException {
        Optional<Pedido> optionalPedido = baseRepository.findById(id);
        if (optionalPedido.isEmpty()) {
            throw new ServicioException("No se encontro el pedido con el id dado.");
        }
        if (estado == null) {
            throw new ServicioException("El estado nuevo no puede ser nulo.");
        }

        Pedido pedido = optionalPedido.get();
        EstadoPedido estadoAnterior = pedido.getEstado();
        switch (pedido.getFormaPago()) {
            case EFECTIVO -> updateEstadoEfectivo(estado, pedido);
            case MERCADO_PAGO -> updateEstadoMercadoPago(estado, pedido);
        }

        try {
            this.mailService.sendEmail(
                    pedido.getCliente().getEmail(),
                    "Actualizacion pedido Buen Sabor",
                    "Tu pedido a cambiado de estado: " + estadoAnterior + " -> " + estado
            );
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void updateEstadoEfectivo(EstadoPedido newEstado, Pedido pedido) throws ServicioException {
        if(!pedido.getEstado().isValidNextState(newEstado, FormaPago.EFECTIVO)) {
            throw new ServicioException(pedido.getEstado() + " -> " + newEstado + " es una transicion de estados invalida en pedidos en EFECTIVO");
        }

        //TODO: incluir estas nuevas validaciones en el enum (va a ser necesario pasar como parametro el tipo envio)
        if(pedido.getTipoEnvio().equals(TipoEnvio.TAKE_AWAY) && newEstado.equals(EstadoPedido.EN_CAMINO)) {
            throw new ServicioException(pedido.getEstado() + " -> " + newEstado + " es una transicion de estados invalida en pedidos TAKE_AWAY");
        }

        if(pedido.getTipoEnvio().equals(TipoEnvio.DELIVERY) && pedido.getEstado().equals(EstadoPedido.PENDIENTE_ENTREGA) && (newEstado.equals(EstadoPedido.PAGADO) || newEstado.equals(EstadoPedido.COMPLETADO))) {
            throw new ServicioException(pedido.getEstado() + " -> " + newEstado + " es una transicion de estados invalida en pedidos DELIVERY");
        }

        switch (newEstado) {
            case PAGADO, COMPLETADO -> {
                pedido.setEstado(EstadoPedido.COMPLETADO);
                pedido.setFechaModificacion(new Date());
                pedido = pedidoRepository.save(pedido);
                facturaService.saveFacturaAfterPagoEfectivo(pedido);
            }
            case PENDIENTE_PAGO, PENDIENTE_ENTREGA, PREPARACION, EN_CAMINO -> {
                pedido.setEstado(newEstado);
                pedido.setFechaModificacion(new Date());
                pedidoRepository.save(pedido);
            }
            case CANCELADO -> {
                this.retornarStock(pedido);
                pedido.setEstado(newEstado);
                pedido.setFechaModificacion(new Date());
                pedido.setFechaBaja(new Date());
                pedidoRepository.save(pedido);
            }
            case NOTA_CREDITO -> {
                this.retornarStock(pedido);
                pedido.setEstado(newEstado);
                pedido.setFechaModificacion(new Date());
                pedido.setFechaBaja(new Date());
                pedido = pedidoRepository.save(pedido);
                facturaService.crearNotaCredito(pedido);
            }
            default -> throw new ServicioException("Invalid state");
        }
    }

    public void updateEstadoMercadoPago(EstadoPedido newEstado, Pedido pedido) throws ServicioException {
        if(!pedido.getEstado().isValidNextState(newEstado, FormaPago.MERCADO_PAGO)) {
            throw new ServicioException(pedido.getEstado() + " -> " + newEstado + " es una transicion de estados invalida en pedidos de MERCADO_PAGO");
        }

        //TODO: incluir estas nuevas validaciones en el enum (va a ser necesario pasar como parametro el tipo envio)
        if(pedido.getTipoEnvio().equals(TipoEnvio.TAKE_AWAY) && newEstado.equals(EstadoPedido.EN_CAMINO)) {
            throw new ServicioException(pedido.getEstado() + " -> " + newEstado + " es una transicion de estados invalida en pedidos TAKE_AWAY");
        }

        if(pedido.getTipoEnvio().equals(TipoEnvio.DELIVERY) && pedido.getEstado().equals(EstadoPedido.PENDIENTE_ENTREGA) && (newEstado.equals(EstadoPedido.PAGADO) || newEstado.equals(EstadoPedido.COMPLETADO))) {
            throw new ServicioException(pedido.getEstado() + " -> " + newEstado + " es una transicion de estados invalida en pedidos DELIVERY");
        }

        switch (newEstado) {
            case PAGADO, PREPARACION, PENDIENTE_ENTREGA, EN_CAMINO, COMPLETADO -> {
                pedido.setEstado(newEstado);
                pedido.setFechaModificacion(new Date());
                pedidoRepository.save(pedido);
            }
            case NOTA_CREDITO -> {
                if (pedido.getEstado().equals(EstadoPedido.PAGADO)) {
                    this.retornarStock(pedido);
                }
                pedido.setEstado(newEstado);
                pedido.setFechaModificacion(new Date());
                pedido.setFechaBaja(new Date());
                pedido = pedidoRepository.save(pedido);
                facturaService.crearNotaCredito(pedido);
            }
            case PENDIENTE_PAGO ->
                    throw new ServicioException("Estado PENDIENTE_PAGO en pedidos de MERCADO_PAGO no implementado a traves de metodo updateEstado.");
            case CANCELADO ->
                    throw new ServicioException("Estado CANCELADO en pedidos de MERCADO_PAGO no implementado a traves de metodo updateEstado.");
            default -> throw new ServicioException("Estado seleccionado invalido.");
        }


    }

    public List<PedidoDto> findAll(EstadoPedido estado) throws ServicioException {
        if (estado != null && StringUtils.isNotBlank(estado.name())) {
            return baseMapper.toDTOsList(pedidoRepository.findAllByEstado(estado));
        }

        return findAll();
    }

    public boolean validarStock(List<AltaPedidoDetallePedidoDto> productos) throws ServicioException {
        List<DetallePedido> productosEntity = this.getValidDetallesPedido(productos, new AtomicReference<>(BigDecimal.ZERO), new AtomicReference<>(BigDecimal.ZERO));

        for (DetallePedido detallePedido : productosEntity) {
            if (!articuloInsumoService.validarStock(detallePedido)) {
                return false;
            }
        }
        return true;
    }

    private void retornarStock(Pedido pedido) throws ServicioException {
        List<DetallePedido> detallesPedidos = detallePedidoRepository.findAllByPedidoId(pedido.getId());

        for (DetallePedido detallePedido : detallesPedidos) {
            articuloInsumoService.retornarStock(detallePedido);
        }
    }

    public List<DetallePedidoSimpleDto> getDetallesByIdPedido(Long idPedido) throws ServicioException {
        Optional<Pedido> optionalPedido = this.findOptionalById(idPedido);
        if(optionalPedido.isEmpty()) {
            throw new ServicioException("No existe un pedido con el id seleccionado.");
        }

        return detallePedidoService.getByPedido(optionalPedido.get());
    }

    public List<PedidoDto> findAllByUser(String auth0Id) throws ServicioException {
        if (StringUtils.isBlank(auth0Id)) {
            throw new ServicioException("Debe proporcionar un Auth0Id.");
        }

        return baseMapper.toDTOsList(pedidoRepository.findAllByAuth0Id(auth0Id));
    }
}

package com.tup.buensabor.services;

import com.tup.buensabor.dtos.factura.AltaPedidoFacturaDto;
import com.tup.buensabor.dtos.factura.FacturaDto;
import com.tup.buensabor.entities.DetallePedido;
import com.tup.buensabor.entities.Factura;
import com.tup.buensabor.entities.Pedido;
import com.tup.buensabor.enums.FormaPago;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.FacturaMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.DetallePedidoRepository;
import com.tup.buensabor.repositories.FacturaRepository;
import com.tup.buensabor.services.interfaces.FacturaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl extends BaseServiceImpl<Factura, FacturaDto, Long> implements FacturaService {

    @Autowired
    private DetalleFacturaServiceImpl detalleFacturaService;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private FacturaMapper facturaMapper;

    public FacturaServiceImpl(BaseRepository<Factura, Long> baseRepository, BaseMapper<Factura, FacturaDto> baseMapper) {
        super(baseRepository, baseMapper);
    }

    public Factura saveFacturaFromPedidoAlta(Pedido pedido, List<DetallePedido> detallesPedido, AltaPedidoFacturaDto facturaAltaDto) throws ServicioException {
        Factura factura = new Factura();

        validateFacturaAltaDto(facturaAltaDto);

        factura.setMpMerchantOrderId(facturaAltaDto.getMpMerchantOrderId());
        factura.setMpPaymentId(facturaAltaDto.getMpPaymentId());
        factura.setMpPreferenceId(facturaAltaDto.getMpPreferenceId());
        factura.setMpPaymentType(facturaAltaDto.getMpPaymentType());
        factura.setFormaPago(facturaAltaDto.getFormaPago());
        factura.setFechaFacturacion(pedido.getFechaPedido());
        factura.setFechaAlta(new Date());
        factura.setTotalVenta(pedido.getTotal());
        factura.setPedido(pedido);

        factura = this.save(factura);

        for(DetallePedido detallePedido : detallesPedido) {
            detalleFacturaService.saveDetalleFromPedido(detallePedido, factura);
        }

        return factura;
    }

    private void validateFacturaAltaDto(AltaPedidoFacturaDto facturaAltaDto) throws ServicioException {
        if(facturaAltaDto.getFormaPago().equals(FormaPago.MERCADO_PAGO)) {
            if(facturaAltaDto.getMpPaymentId() == null ) {
                throw new ServicioException("El campo mpPaymentId es obligatorio cuando el metodo de pago es MERCADO_PAGO.");
            } else if (facturaAltaDto.getMpMerchantOrderId() == null) {
                throw new ServicioException("El campo mpMerchantOrderId es obligatorio cuando el metodo de pago es MERCADO_PAGO.");
            } else if (StringUtils.isBlank(facturaAltaDto.getMpPreferenceId())) {
                throw new ServicioException("El campo mpPreferenceId es obligatorio cuando el metodo de pago es MERCADO_PAGO.");
            } else if (StringUtils.isBlank(facturaAltaDto.getMpPaymentType())) {
                throw new ServicioException("El campo mpPaymentType es obligatorio cuando el metodo de pago es MERCADO_PAGO.");
            }
        }
    }

    public Factura saveFacturaAfterPagoEfectivo(Pedido pedido) throws ServicioException {
        if(facturaRepository.existsByPedidoId(pedido.getId())) {
            throw new ServicioException("Ya existe una factura para el pedido dado.");
        }

        Factura factura = new Factura();

        factura.setFechaFacturacion(pedido.getFechaPedido());
        factura.setFechaAlta(new Date());
        factura.setTotalVenta(pedido.getTotal());
        factura.setPedido(pedido);
        factura.setFormaPago(FormaPago.EFECTIVO);

        factura = this.save(factura);

        List<DetallePedido> detallesPedidos = detallePedidoRepository.findAllByPedidoId(pedido.getId());

        for(DetallePedido detallePedido : detallesPedidos) {
            detalleFacturaService.saveDetalleFromPedido(detallePedido, factura);
        }

        return factura;
    }

    public FacturaDto crearNotaCredito(Pedido pedido) throws ServicioException {
        Optional<Factura> optionalFactura = facturaRepository.findByPedidoId(pedido.getId());
        if(optionalFactura.isEmpty()) {
            throw new ServicioException("No se encontro factura para el pedido dado.");
        }

        Factura factura = optionalFactura.get();
        factura.setFechaModificacion(new Date());
        factura.setFechaBaja(new Date());

        return facturaMapper.toDTO(factura);
    }

}

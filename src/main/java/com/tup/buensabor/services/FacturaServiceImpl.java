package com.tup.buensabor.services;

import com.tup.buensabor.dtos.factura.AltaPedidoFacturaDto;
import com.tup.buensabor.dtos.factura.FacturaDto;
import com.tup.buensabor.entities.DetalleFactura;
import com.tup.buensabor.entities.DetallePedido;
import com.tup.buensabor.entities.Factura;
import com.tup.buensabor.entities.Pedido;
import com.tup.buensabor.enums.FormaPago;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.FacturaMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.FacturaRepository;
import com.tup.buensabor.services.interfaces.DetalleFacturaService;
import com.tup.buensabor.services.interfaces.FacturaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FacturaServiceImpl extends BaseServiceImpl<Factura, FacturaDto, Long> implements FacturaService {

    @Autowired
    private DetalleFacturaServiceImpl detalleFacturaService;

    @Autowired
    private FacturaRepository facturaRepository;

    private final FacturaMapper facturaMapper = FacturaMapper.getInstance();

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
}

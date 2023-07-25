package com.tup.buensabor.services;

import com.tup.buensabor.dtos.detallefactura.DetalleFacturaDto;
import com.tup.buensabor.entities.DetalleFactura;
import com.tup.buensabor.entities.DetallePedido;
import com.tup.buensabor.entities.Factura;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.DetalleFacturaMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.DetalleFacturaRepository;
import com.tup.buensabor.services.interfaces.DetalleFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleFacturaServiceImpl extends BaseServiceImpl<DetalleFactura, DetalleFacturaDto, Long> implements DetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @Autowired
    private DetalleFacturaMapper detalleFacturaMapper;

    public DetalleFacturaServiceImpl(BaseRepository<DetalleFactura, Long> baseRepository, BaseMapper<DetalleFactura, DetalleFacturaDto> baseMapper) {
        super(baseRepository, baseMapper);
    }

    public void saveDetalleFromPedido(DetallePedido detallePedido, Factura factura) throws ServicioException {
        DetalleFactura detalleFactura = new DetalleFactura();

        detalleFactura.setFactura(factura);
        detalleFactura.setCantidad(detallePedido.getCantidad());
        detalleFactura.setSubtotal(detallePedido.getSubtotal());
        if(detallePedido.getArticuloManufacturado() != null) {
            detalleFactura.setArticuloManufacturado(detallePedido.getArticuloManufacturado());
        } else if(detallePedido.getArticuloInsumo() != null) {
            detalleFactura.setArticuloInsumo(detallePedido.getArticuloInsumo());
        } else {
            throw new ServicioException("Un detalle factura debe hacer referencia o a un articulo manufacturado o a un articulo insumo.");
        }

        this.save(detalleFactura);
    }
}

package com.tup.buensabor.dtos.detallefactura;

import com.tup.buensabor.dtos.BaseDto;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoDto;
import com.tup.buensabor.dtos.articulomanufacturado.ArticuloManufacturadoDto;
import com.tup.buensabor.dtos.factura.FacturaDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class DetalleFacturaDto extends BaseDto {
    private Integer cantidad;
    private BigDecimal subtotal;
    private ArticuloInsumoDto articuloInsumo;
    private ArticuloManufacturadoDto articuloManufacturado;
    private FacturaDto facturaDto;
}

package com.tup.buensabor.dtos.detallearticulomanufacturado;

import com.tup.buensabor.dtos.BaseDto;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoDto;
import com.tup.buensabor.dtos.articulomanufacturado.ArticuloManufacturadoDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class DetalleArticuloManufacturadoDto extends BaseDto {
    private BigDecimal cantidad;
    private ArticuloInsumoDto articuloInsumo;
    private ArticuloManufacturadoDto articuloManufacturado;
}

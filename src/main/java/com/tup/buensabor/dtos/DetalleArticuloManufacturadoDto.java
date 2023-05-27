package com.tup.buensabor.dtos;

import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalleArticuloManufacturadoDto extends BaseDto {
    private BigDecimal cantidad;
    private UnidadMedidaDto unidadMedida;
    private ArticuloInsumoDto articuloInsumo;
    private ArticuloManufacturadoDto articuloManufacturado;
}

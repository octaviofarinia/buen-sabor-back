package com.tup.buensabor.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class DetalleArticuloManufacturadoDto {
    private BigDecimal cantidad;
    private UnidadMedidaDto unidadMedida;
    private ArticuloInsumoDto articuloInsumo;
    private ArticuloManufacturadoDto articuloManufacturado;
}

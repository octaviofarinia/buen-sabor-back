package com.tup.buensabor.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticuloManufacturadoDto extends BaseDto {
    private String denominacion;
    private String descripcion;
    private Integer tiempoEstimadoCocina;
    private BigDecimal precioVenta;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal costo;
    private String urlImagen;
    private String fechaBaja;
}

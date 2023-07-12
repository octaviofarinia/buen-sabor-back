package com.tup.buensabor.dtos.articulomanufacturado;

import com.tup.buensabor.dtos.BaseDto;
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
    private BigDecimal costo;
    private String urlImagen;
    private String fechaBaja;
}

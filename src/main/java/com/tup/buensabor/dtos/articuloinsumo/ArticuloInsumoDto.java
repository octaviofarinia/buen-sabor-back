package com.tup.buensabor.dtos.articuloinsumo;

import com.tup.buensabor.dtos.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticuloInsumoDto extends BaseDto {
    private String denominacion;
    private String urlImagen;
    private BigDecimal precioCompra;
    private BigDecimal stockActual;
    private BigDecimal stockMinimo;
    private Long idUnidadMedida;
    private Long idRubroArticulo;
}

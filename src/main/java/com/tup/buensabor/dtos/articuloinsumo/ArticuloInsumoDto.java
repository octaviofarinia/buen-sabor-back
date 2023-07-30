package com.tup.buensabor.dtos.articuloinsumo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tup.buensabor.dtos.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private OffsetDateTime fechaBaja;
}

package com.tup.buensabor.dtos.articuloinsumo;

import com.tup.buensabor.dtos.BaseDto;
import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ArticuloInsumoDto extends BaseDto {
    private Long id;
    private String denominacion;
    private String urlImagen;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private BigDecimal stockActual;
    private BigDecimal stockMinimo;
    private Long idUnidadMedida;
    private Long idRubroArticulo;
}

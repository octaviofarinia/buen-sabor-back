package com.tup.buensabor.dtos.articuloinsumo;

import com.tup.buensabor.dtos.BaseDto;
import com.tup.buensabor.dtos.unidadmedida.UnidadMedidaDto;
import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloCompleteDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticuloInsumoCompleteDto extends BaseDto {
    private String denominacion;
    private String urlImagen;
    private BigDecimal precioCompra;
    private BigDecimal stockActual;
    private BigDecimal stockMinimo;
    private UnidadMedidaDto unidadMedida;
    private RubroArticuloCompleteDto rubroArticulo;
}

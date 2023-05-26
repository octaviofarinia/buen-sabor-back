package com.tup.buensabor.dtos.articuloinsumo;

import com.tup.buensabor.dtos.UnidadMedidaDto;
import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloCompleteDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ArticuloInsumoCompleteDto {
    private Long id;
    private String denominacion;
    private String urlImagen;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private BigDecimal stockActual;
    private BigDecimal stockMinimo;
    private UnidadMedidaDto unidadMedida;
    private RubroArticuloCompleteDto rubroArticulo;
}

package com.tup.buensabor.dtos;

import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ArticuloInsumoDto {
    private Long id;
    private String denominacion;
    private String urlImage;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private BigDecimal stockActual;
    private BigDecimal stockMinimo;
    private UnidadMedidaDto unidadMedida;
    private RubroArticuloDto rubroArticulo;
    private Date fechaAlta;
    private Date fechaModificacion;
    private Date fechaBaja;
}

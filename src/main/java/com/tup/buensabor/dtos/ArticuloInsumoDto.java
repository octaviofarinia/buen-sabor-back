package com.tup.buensabor.dtos;

import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloDto;
import com.tup.buensabor.entities.Base;
import com.tup.buensabor.entities.RubroArticulo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

public class ArticuloInsumoDto {
    private Long id;
    private String denominacion;
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

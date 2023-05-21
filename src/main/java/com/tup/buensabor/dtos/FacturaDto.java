package com.tup.buensabor.dtos;

import com.tup.buensabor.enums.FormaPago;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class FacturaDto {
    private Date fechaFacturacion;
    private Integer numero;
    private BigDecimal montoDescuento;
    private FormaPago formaPago;
    private String numeroTarjeta;
    private BigDecimal totalVenta;
    private BigDecimal totalCosto;
    private Date fechaAlta;
    private Date fechaModificacion;
    private Date fechaBaja;
}

package com.tup.buensabor.dtos;

import com.tup.buensabor.enums.FormaPago;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FacturaDto extends BaseDto {
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

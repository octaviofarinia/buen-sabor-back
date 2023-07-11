package com.tup.buensabor.dtos.factura;

import com.tup.buensabor.dtos.BaseDto;
import com.tup.buensabor.dtos.pedido.PedidoDto;
import com.tup.buensabor.enums.FormaPago;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class FacturaDto extends BaseDto {
    private Date fechaFacturacion;
    private Long paymenteId;
    private Long merchantOrderId;
    private String preferenceId;
    private FormaPago formaPago;
    private BigDecimal totalVenta;
    private PedidoDto pedido;
    private Date fechaAlta;
    private Date fechaModificacion;
    private Date fechaBaja;
}

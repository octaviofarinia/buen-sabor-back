package com.tup.buensabor.dtos.factura;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tup.buensabor.dtos.BaseDto;
import com.tup.buensabor.dtos.pedido.PedidoDto;
import com.tup.buensabor.enums.FormaPago;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class FacturaDto extends BaseDto {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private OffsetDateTime fechaFacturacion;
    private Long mpPaymentId;
    private Long mpMerchantOrderId;
    private String mpPreferenceId;
    private String mpPaymentType;
    private FormaPago formaPago;
    private BigDecimal totalVenta;
    private PedidoDto pedido;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private OffsetDateTime fechaAlta;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private OffsetDateTime fechaModificacion;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private OffsetDateTime fechaBaja;
}

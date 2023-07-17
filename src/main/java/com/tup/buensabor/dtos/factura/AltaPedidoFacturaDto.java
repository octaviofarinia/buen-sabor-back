package com.tup.buensabor.dtos.factura;

import com.tup.buensabor.enums.FormaPago;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AltaPedidoFacturaDto {
    private Long mpPaymentId;
    private Long mpMerchantOrderId;
    private String mpPreferenceId;
    private String mpPaymentType;
    @NotNull
    private FormaPago formaPago;
}

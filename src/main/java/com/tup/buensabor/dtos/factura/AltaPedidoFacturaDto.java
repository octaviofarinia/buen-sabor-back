package com.tup.buensabor.dtos.factura;

import com.tup.buensabor.enums.FormaPago;

public class AltaPedidoFacturaDto {
    private Long paymentId;
    private Long merchantOrderId;
    private String preferenceId;
    private FormaPago formaPago;
}

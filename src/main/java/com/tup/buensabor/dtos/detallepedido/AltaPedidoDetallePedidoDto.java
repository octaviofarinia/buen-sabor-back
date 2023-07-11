package com.tup.buensabor.dtos.detallepedido;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AltaPedidoDetallePedidoDto {
    private Long idArticuloManufacturado;
    private Integer cantidad;
}

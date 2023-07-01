package com.tup.buensabor.websocket;

import com.tup.buensabor.dtos.BaseDto;
import com.tup.buensabor.enums.EstadoPedido;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PedidoWSDto extends BaseDto {
    private Long numero;
    private BigDecimal total;
    private EstadoPedido estado;
}

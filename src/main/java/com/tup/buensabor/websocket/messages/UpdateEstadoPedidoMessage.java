package com.tup.buensabor.websocket.messages;

import com.tup.buensabor.enums.EstadoPedido;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateEstadoPedidoMessage {
    private Long id;
    private EstadoPedido estado;
}

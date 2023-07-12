package com.tup.buensabor.websocket.messages;

import com.tup.buensabor.dtos.pedido.PedidoDto;
import com.tup.buensabor.enums.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PedidoNotificationMessage {
    private Long id;
}

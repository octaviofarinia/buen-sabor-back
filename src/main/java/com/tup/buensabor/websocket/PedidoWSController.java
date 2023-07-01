package com.tup.buensabor.websocket;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.PedidoDto;
import com.tup.buensabor.entities.Pedido;
import com.tup.buensabor.services.PedidoServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/pedidos-ws")
public class PedidoWSController extends BaseControllerImpl<PedidoWS, PedidoWSDto, PedidoWSServiceImpl> {
}

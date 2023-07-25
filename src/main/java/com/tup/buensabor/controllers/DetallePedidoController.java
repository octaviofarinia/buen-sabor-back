package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.detallepedido.DetallePedidoDto;
import com.tup.buensabor.entities.DetallePedido;
import com.tup.buensabor.services.DetallePedidoServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/detalles-pedidos")
public class DetallePedidoController extends BaseControllerImpl<DetallePedido, DetallePedidoDto, DetallePedidoServiceImpl> {
}

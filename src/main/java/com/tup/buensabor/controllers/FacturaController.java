package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.factura.FacturaDto;
import com.tup.buensabor.entities.Factura;
import com.tup.buensabor.enums.EstadoPedido;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.FacturaServiceImpl;
import com.tup.buensabor.services.PedidoServiceImpl;
import com.tup.buensabor.websocket.messages.PedidoNotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/facturas")
public class FacturaController extends BaseControllerImpl<Factura, FacturaDto, FacturaServiceImpl> {
}

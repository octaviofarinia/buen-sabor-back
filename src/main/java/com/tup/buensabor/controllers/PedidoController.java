package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.detallepedido.AltaPedidoDetallePedidoDto;
import com.tup.buensabor.dtos.pedido.AltaPedidoDto;
import com.tup.buensabor.dtos.pedido.PedidoDto;
import com.tup.buensabor.entities.Pedido;
import com.tup.buensabor.enums.EstadoPedido;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.PedidoServiceImpl;
import com.tup.buensabor.websocket.messages.PedidoNotificationMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(path = "api/v1/pedidos")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoDto, PedidoServiceImpl> {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/listar")
    public ResponseEntity<?> getAllFiltrados(@RequestParam(name = "estado", required = false) EstadoPedido estado) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll(estado));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"error\": \"Error. Por favor intente mas tarde\"}");
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> save(@RequestBody AltaPedidoDto altaPedidoDto) {
        try {
            Pedido pedido = servicio.altaPostPago(altaPedidoDto);
            simpMessagingTemplate.convertAndSend("/pedidos", new PedidoNotificationMessage(pedido.getId()));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el insumo: " + e.getMessage());
        }
    }

    @PutMapping("/cambiar-estado")
    public ResponseEntity<?> updateEstado(@RequestParam(name = "id") Long id, @RequestParam(name = "estado") EstadoPedido estado) {
        try {
            servicio.updateEstado(id, estado);
            simpMessagingTemplate.convertAndSend("/pedidos", new PedidoNotificationMessage(id));

            return ResponseEntity.ok().build();
        } catch (ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al crear el pedidoWS: " + e.getMessage());
        }
    }

    @GetMapping("/validar-stock")
    public ResponseEntity<?> validarStock(@RequestBody List<AltaPedidoDetallePedidoDto> productos) {
        try {
            boolean isValid = servicio.validarStock(productos);
            return ResponseEntity.ok(isValid);
        } catch (ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al validar el pedido: " + e.getMessage());
        }
    }

}

package com.tup.buensabor.websocket;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.websocket.messages.UpdateEstadoPedidoMessage;
import lombok.extern.log4j.Log4j2;
import org.cloudinary.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(path = "api/v1/pedidos-ws")
public class PedidoWSController extends BaseControllerImpl<PedidoWS, PedidoWSDto, PedidoWSServiceImpl> {

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody PedidoWSDto pedidoWSDto) {
        try {
            return ResponseEntity.ok().body(servicio.save(pedidoWSDto));
        } catch (ServicioException e) {
            return ResponseEntity.badRequest().body("Error al crear la unidad de medida: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody PedidoWSDto pedidoWSDto) {
        try {
            return ResponseEntity.ok().body(servicio.update(id, pedidoWSDto));
        } catch (ServicioException e) {
            return ResponseEntity.badRequest().body("Error al crear el pedidoWS: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        try {
            servicio.hardDelete(id);
            return ResponseEntity.noContent().build();
        } catch (ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al eliminar el pedidoWS: " + e.getMessage());
        }
    }

    @MessageMapping("/cambiar-estado")
    @SendTo("/pedidos")
    public UpdateEstadoPedidoMessage updateEstado(@Payload UpdateEstadoPedidoMessage message) {
        log.info("MENSAJE: " + message);
        try {
            servicio.updateEstado(message.getId(), message.getEstado());
            return message;
        } catch (ServicioException e) {
            return null;
        }
    }

}

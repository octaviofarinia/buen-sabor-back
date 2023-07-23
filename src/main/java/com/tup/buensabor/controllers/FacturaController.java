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

    @Autowired
    private PedidoServiceImpl pedidoService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/anular")
    public ResponseEntity<?> anularFactura(@RequestParam(name = "id") Long id) {
        try {
            FacturaDto facturaAnulada = pedidoService.anularFromFactura(id);
            simpMessagingTemplate.convertAndSend("/pedidos", new PedidoNotificationMessage(id));

            if(facturaAnulada != null) {
                return ResponseEntity.ok(facturaAnulada);
            } else {
                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al anular la factura: " + e.getMessage());
        }
    }

}

package com.tup.buensabor.controllers;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.tup.buensabor.dtos.detallepedido.AltaPedidoDetallePedidoDto;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.MercadoPagoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(path = "api/v1/mercado-pago")
public class MercadoPagoController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PostMapping(value = "/create-preference")
    public ResponseEntity<?> createPreference(@RequestBody List<AltaPedidoDetallePedidoDto> detallesPedido) {
        try {
            log.info("CREATE PREFERENCE");
            Preference preference = mercadoPagoService.createPreference(detallesPedido);
            return ResponseEntity.ok().body(preference);
        } catch (MPException | ServicioException | MPApiException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al crear preferencia: " + e.getMessage());
        }
    }

}

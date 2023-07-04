package com.tup.buensabor.controllers;

import com.mercadopago.resources.preference.Preference;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoCompleteDto;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoDto;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.MercadoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/mercado-pago")
public class MercadoPagoController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PostMapping(value = "/create-preference")
    public ResponseEntity<?> createPreference() {
        try {
            Preference preference = mercadoPagoService.createPreference();
            return ResponseEntity.ok(preference);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al crear preferencia: " + e.getMessage());
        }
    }

}

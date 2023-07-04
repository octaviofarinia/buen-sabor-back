package com.tup.buensabor.controllers;

import com.mercadopago.resources.preference.Preference;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoCompleteDto;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoDto;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.MercadoPagoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
@RestController
@RequestMapping(path = "api/v1/mercado-pago")
public class MercadoPagoController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PostMapping(value = "/create-preference")
    public ResponseEntity<?> createPreference() {
        try {
            log.info("CREATE PREFERENCE");
            Preference preference = mercadoPagoService.createPreference();
            return ResponseEntity.ok().body(preference);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al crear preferencia: " + e.getMessage());
        }
    }

}

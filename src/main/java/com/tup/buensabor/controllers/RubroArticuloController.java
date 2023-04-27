package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.RubroArticuloSimpleDto;
import com.tup.buensabor.entities.RubroArticulo;
import com.tup.buensabor.services.RubroArticuloServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/rubros-articulos")
public class RubroArticuloController extends BaseControllerImpl<RubroArticulo, RubroArticuloServiceImpl> {


    @PostMapping("/simple-save")
    public ResponseEntity<?> save(@RequestBody RubroArticuloSimpleDto rubroArticulo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.save(rubroArticulo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Error. Por favor intente mas tarde\"}");
        }
    }

}

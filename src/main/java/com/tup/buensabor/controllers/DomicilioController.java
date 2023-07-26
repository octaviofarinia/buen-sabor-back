package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.domicilio.DomicilioDto;
import com.tup.buensabor.entities.Domicilio;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.DomicilioServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/domicilios")
public class DomicilioController extends BaseControllerImpl<Domicilio, DomicilioDto, DomicilioServiceImpl> {

    @GetMapping("/listar")
    public ResponseEntity<?> getByAuth0Id(@RequestHeader("auth0Id") String auth0Id) {
        try {
            List<DomicilioDto> domicilios = servicio.findByAuth0Id(auth0Id);
            return ResponseEntity.ok(domicilios);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el domicilio: " + e.getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> save(@RequestBody() DomicilioDto domicilio) {
        try {
            DomicilioDto domicilioDto = servicio.save(domicilio);
            return ResponseEntity.ok(domicilioDto);
        } catch (ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el domicilio: " + e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody() DomicilioDto domicilio) {
        try {
            DomicilioDto articuloInsumo = servicio.update(domicilio);
            return ResponseEntity.ok(articuloInsumo);
        } catch (ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el domicilio: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        try {
            servicio.softDelete(id);
            return ResponseEntity.noContent().build();
        } catch (ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al eliminar el domicilio: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/hard_delete/{id}")
    public ResponseEntity<?> hardDelete(@PathVariable(name = "id") Long id) {
        try {
            servicio.hardDelete(id);
            return ResponseEntity.noContent().build();
        } catch (ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al eliminar el domicilio: " + e.getMessage());
        }
    }

}

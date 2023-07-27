package com.tup.buensabor.controllers;

import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloCompleteDto;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.RubroArticuloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/rubros-articulos")
public class RubroArticuloController {

    @Autowired
    private RubroArticuloServiceImpl servicio;

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody RubroArticuloCompleteDto rubroArticulo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.save(rubroArticulo));
        } catch (ServicioException servicioException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(servicioException.getMessage());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Error. Por favor intente mas tarde\"}");
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getAllSimple());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Error. Por favor intente mas tarde\"}");
        }
    }

    @GetMapping("/get-all-parents")
    public ResponseEntity<?> getAllParents() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getAllParents());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Error. Por favor intente mas tarde\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getOne(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Error. Por favor intente mas tarde\"}");
        }
    }

    @GetMapping("/{id}/complete")
    public ResponseEntity<?> getOneComplete(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getOneComplete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Error. Por favor intente mas tarde\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RubroArticuloCompleteDto object) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.update(object));
        } catch (ServicioException servicioException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(servicioException.getMessage());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Error. Por favor intente mas tarde\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicio.delete(id));
        } catch (ServicioException servicioException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(servicioException.getMessage());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Error. Por favor intente mas tarde\"}");
        }
    }

}

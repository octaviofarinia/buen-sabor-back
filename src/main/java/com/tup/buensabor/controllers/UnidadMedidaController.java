package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.unidadmedida.UnidadMedidaDto;
import com.tup.buensabor.entities.UnidadMedida;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.UnidadMedidaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/unidades-medida")
public class UnidadMedidaController extends BaseControllerImpl<UnidadMedida, UnidadMedidaDto, UnidadMedidaServiceImpl> {

    @GetMapping("/listar")
    public ResponseEntity<?> getAllActive() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAllActive());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"error\": \"Error. Por favor intente mas tarde\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody UnidadMedidaDto unidadMedidaDto) {
        try {
            return ResponseEntity.ok().body(servicio.save(unidadMedidaDto));
        } catch (ServicioException e) {
            return ResponseEntity.badRequest().body("Error al crear la unidad de medida: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody UnidadMedidaDto unidadMedidaDto) {
        try {
            return ResponseEntity.ok().body(servicio.update(unidadMedidaDto));
        } catch (ServicioException e) {
            return ResponseEntity.badRequest().body("Error al crear la unidad de medida: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        try {
            servicio.softDelete(id);
            return ResponseEntity.noContent().build();
        } catch (ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al eliminar la unidad de medida: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @DeleteMapping(value = "/hard_delete/{id}")
    public ResponseEntity<?> hardDelete(@PathVariable(name = "id") Long id) {
        try {
            servicio.hardDeleteValidated(id);
            return ResponseEntity.noContent().build();
        } catch (ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al eliminar la unidad de medida: " + e.getMessage());
        }
    }

}

package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.UnidadMedidaDto;
import com.tup.buensabor.entities.UnidadMedida;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.UnidadMedidaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/unidades-medida")
public class UnidadMedidaController extends BaseControllerImpl<UnidadMedida, UnidadMedidaDto, UnidadMedidaServiceImpl> {

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody UnidadMedidaDto unidadMedidaDto) {
        try {
            return ResponseEntity.ok().body(servicio.save(unidadMedidaDto));
        } catch (ServicioException e) {
            return ResponseEntity.badRequest().body("Error al crear la unidad de medida: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody UnidadMedidaDto unidadMedidaDto) {
        try {
            return ResponseEntity.ok().body(servicio.update(id, unidadMedidaDto));
        } catch (ServicioException e) {
            return ResponseEntity.badRequest().body("Error al crear la unidad de medida: " + e.getMessage());
        }
    }

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

    @DeleteMapping(value = "/hard_delete/{id}")
    public ResponseEntity<?> hardDelete(@PathVariable(name = "id") Long id) {
        try {
            servicio.hardDelete(id);
            return ResponseEntity.noContent().build();
        } catch (ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al eliminar la unidad de medida: " + e.getMessage());
        }
    }

}

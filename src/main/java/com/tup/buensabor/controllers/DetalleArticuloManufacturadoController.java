package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.detallearticulomanufacturado.DetalleArticuloManufacturadoDto;
import com.tup.buensabor.dtos.detallearticulomanufacturado.DetalleArticuloManufacturadoSimpleDto;
import com.tup.buensabor.entities.DetalleArticuloManufacturado;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.DetalleArticuloManufacturadoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/detalles-articulos-manufacturados")
public class DetalleArticuloManufacturadoController extends BaseControllerImpl<DetalleArticuloManufacturado, DetalleArticuloManufacturadoDto, DetalleArticuloManufacturadoServiceImpl> {

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @PostMapping(value = "")
    public ResponseEntity<?> save(@RequestBody() DetalleArticuloManufacturadoSimpleDto detalle) {
        try {
            DetalleArticuloManufacturadoDto detalleArticuloManufacturad = servicio.save(detalle);
            return ResponseEntity.ok(detalleArticuloManufacturad);
        } catch (IOException | ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el detalle: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@RequestBody() DetalleArticuloManufacturadoSimpleDto detalle) {
        try {
            DetalleArticuloManufacturadoDto detalleArticuloManufacturad = servicio.update(detalle);
            return ResponseEntity.ok(detalleArticuloManufacturad);
        } catch (IOException | ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el detalle: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        try {
            servicio.hardDelete(id);
            return ResponseEntity.noContent().build();
        } catch (ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al eliminar el detalle: " + e.getMessage());
        }
    }
    
}

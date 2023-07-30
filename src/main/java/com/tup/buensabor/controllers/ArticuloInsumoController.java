package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoCompleteDto;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoDto;
import com.tup.buensabor.entities.ArticuloInsumo;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.ArticuloInsumoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping(path = "api/v1/articulos-insumo")
public class ArticuloInsumoController extends BaseControllerImpl<ArticuloInsumo, ArticuloInsumoCompleteDto, ArticuloInsumoServiceImpl> {

    @GetMapping("/listar")
    public ResponseEntity<?> getAllActive() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAllActive());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"error\": \"Error. Por favor intente mas tarde\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@RequestPart("insumo") ArticuloInsumoDto insumo, @RequestParam("imagen") MultipartFile imagen) {
        try {
            ArticuloInsumoCompleteDto articuloInsumo = servicio.save(insumo, imagen);
            return ResponseEntity.ok(articuloInsumo);
        } catch (IOException | ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el insumo: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(@RequestPart("insumo") ArticuloInsumoDto insumo, @RequestParam(value = "imagen", required = false) MultipartFile imagen) {
        try {
            ArticuloInsumoCompleteDto articuloInsumo = servicio.update(insumo, imagen);
            return ResponseEntity.ok(articuloInsumo);
        } catch (IOException | ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el insumo: " + e.getMessage());
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
            return ResponseEntity.internalServerError().body("Error al eliminar el insumo: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @DeleteMapping(value = "/hard_delete/{id}")
    public ResponseEntity<?> hardDelete(@PathVariable(name = "id") Long id) {
        try {
            servicio.hardDeleteImage(id);
            return ResponseEntity.noContent().build();
        } catch (IOException | ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al eliminar el insumo: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @PutMapping(value = "/update-stock/{id}")
    public ResponseEntity<?> updateStock(@RequestParam("idInsumo") Long idInsumo, @RequestParam("stock") BigDecimal stock, @RequestParam(value = "precio", required = false) BigDecimal precio) {
        try {
            ArticuloInsumoCompleteDto articuloInsumo = servicio.updateStock(idInsumo, stock, precio);
            return ResponseEntity.ok(articuloInsumo);
        } catch (ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el insumo: " + e.getMessage());
        }
    }

}

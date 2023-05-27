package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.UnidadMedidaDto;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoCompleteDto;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoDto;
import com.tup.buensabor.entities.ArticuloInsumo;
import com.tup.buensabor.entities.UnidadMedida;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.ArticuloInsumoServiceImpl;
import com.tup.buensabor.services.UnidadMedidaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/articulos-insumo")
public class ArticuloInsumoController extends BaseControllerImpl<ArticuloInsumo, ArticuloInsumoCompleteDto, ArticuloInsumoServiceImpl> {

    @Autowired
    private ArticuloInsumoServiceImpl servicio;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAllDto());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Error. Por favor intente mas tarde\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findByIdDto(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Error. Por favor intente mas tarde\"}");
        }
    }

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

    @PutMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(@RequestPart("insumo") ArticuloInsumoDto insumo, @RequestParam("imagen") MultipartFile imagen) {
        try {
            ArticuloInsumoCompleteDto articuloInsumo = servicio.update(insumo, imagen);
            return ResponseEntity.ok(articuloInsumo);
        } catch (IOException | ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el insumo: " + e.getMessage());
        }
    }

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

}

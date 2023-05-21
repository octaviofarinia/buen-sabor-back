package com.tup.buensabor.controllers;

import com.tup.buensabor.dtos.ArticuloManufacturadoDto;
import com.tup.buensabor.entities.ArticuloManufacturado;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.ArticuloManufacturadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/articulos-manufacturados")
public class ArticuloManufacturadoController {

    @Autowired
    private ArticuloManufacturadoServiceImpl articuloManufacturadoService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@RequestPart("producto") ArticuloManufacturadoDto producto, @RequestParam("imagen") MultipartFile imagen) {
        try {
            ArticuloManufacturado articuloManufacturado = articuloManufacturadoService.save(producto, imagen);
            return ResponseEntity.ok(articuloManufacturado);
        } catch (IOException | ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el producto: " + e.getMessage());
        }
    }

    @PutMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(@RequestPart("producto") ArticuloManufacturadoDto producto, @RequestParam("imagen") MultipartFile imagen) {
        try {
            ArticuloManufacturado articuloManufacturado = articuloManufacturadoService.update(producto, imagen);
            return ResponseEntity.ok(articuloManufacturado);
        } catch (IOException | ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el producto: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        try {
            articuloManufacturadoService.softDelete(id);
            return ResponseEntity.noContent().build();
        } catch (ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al eliminar el producto: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/hard_delete/{id}")
    public ResponseEntity<?> hardDelete(@PathVariable(name = "id") Long id) {
        try {
            articuloManufacturadoService.hardDeleteImage(id);
            return ResponseEntity.noContent().build();
        } catch (IOException | ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al eliminar el producto: " + e.getMessage());
        }
    }

}

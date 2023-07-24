package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.articulomanufacturado.ArticuloManufacturadoDto;
import com.tup.buensabor.entities.ArticuloManufacturado;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.ArticuloManufacturadoServiceImpl;
import com.tup.buensabor.services.DetalleArticuloManufacturadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping(path = "api/v1/articulos-manufacturados")
public class ArticuloManufacturadoController extends BaseControllerImpl<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoServiceImpl> {

    @Autowired
    private DetalleArticuloManufacturadoServiceImpl detalleArticuloManufacturadoService;

    @GetMapping("/listar")
    public ResponseEntity<?> getAllFiltrados(@RequestParam(name = "filtro", required = false, defaultValue = "") String nombre) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"error\": \"Error. Por favor intente mas tarde\"}");
        }
    }

    @GetMapping(value = "/{id}/detalles")
    public ResponseEntity<?> getDetalles(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(detalleArticuloManufacturadoService.getByIdArticuloManufacturado(id));
        } catch (ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@RequestPart("producto") ArticuloManufacturadoDto producto, @RequestParam("imagen") MultipartFile imagen) {
        try {
            ArticuloManufacturadoDto articuloManufacturado = servicio.save(producto, imagen);
            return ResponseEntity.ok(articuloManufacturado);
        } catch (IOException | ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el producto: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(@RequestPart("producto") ArticuloManufacturadoDto producto, @RequestParam(value = "imagen", required = false) MultipartFile imagen) {
        try {
            ArticuloManufacturadoDto articuloManufacturado = servicio.update(producto, imagen);
            return ResponseEntity.ok(articuloManufacturado);
        } catch (IOException | ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el producto: " + e.getMessage());
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
            return ResponseEntity.internalServerError().body("Error al eliminar el producto: " + e.getMessage());
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
            return ResponseEntity.internalServerError().body("Error al eliminar el producto: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('administrador', 'logistica')")
    @GetMapping("/ranking")
    public ResponseEntity<?> ranking(@RequestParam(name = "desde", required = false) Date desde, @RequestParam(name = "hasta", required = false) Date hasta) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.ranking(desde, hasta));
        } catch (ServicioException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"error\": \"Error. Por favor intente mas tarde\"}");
        }
    }

}

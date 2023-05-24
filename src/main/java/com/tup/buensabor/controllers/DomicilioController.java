package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.ArticuloManufacturadoDto;
import com.tup.buensabor.dtos.usuario.PostRegisterUserDto;
import com.tup.buensabor.entities.ArticuloManufacturado;
import com.tup.buensabor.entities.Usuario;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.UsuarioServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/domicilios")
public class DomicilioController extends BaseControllerImpl<Usuario, UsuarioServiceImpl> {

    @PostMapping(value = "/prueba")
    public ResponseEntity<?> save(@RequestBody()PostRegisterUserDto userDto) {
        try {
            System.out.println("DTO: " + userDto);
            return ResponseEntity.ok().body("TODO BIEN");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el producto: " + e.getMessage());
        }
    }

}

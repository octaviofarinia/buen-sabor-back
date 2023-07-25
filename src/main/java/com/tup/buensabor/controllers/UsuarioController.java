package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.usuario.UsuarioDto;
import com.tup.buensabor.entities.Usuario;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.UsuarioServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/usuarios")
public class UsuarioController extends BaseControllerImpl<Usuario, UsuarioDto, UsuarioServiceImpl> {

    @PostMapping(value = "/post_register_save")
    public ResponseEntity<?> save(@RequestBody() UsuarioDto userDto) {
        try {
            servicio.postRegisterSave(userDto);
            return ResponseEntity.ok().body("Usuario persistido correctamente.");
        } catch (ServicioException e) {
            return ResponseEntity.internalServerError().body("Error al persistir usuario: " + e.getMessage());
        }
    }

}

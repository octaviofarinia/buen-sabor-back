package com.tup.buensabor.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClienteDto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private UsuarioDto usuario;
    private Date fechaAlta;
    private Date fechaModificacion;
    private Date fechaBaja;
}

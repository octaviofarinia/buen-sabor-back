package com.tup.buensabor.dtos;

import com.tup.buensabor.enums.Rol;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {
    private String usuario;
    private String clave;
    private Rol rol;
}

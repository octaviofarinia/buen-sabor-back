package com.tup.buensabor.dtos;

import com.tup.buensabor.enums.Rol;
import lombok.Data;

@Data
public class UsuarioDto {
    private String usuario;
    private String clave;
    private Rol rol;
}

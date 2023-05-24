package com.tup.buensabor.dtos.usuario;

import com.tup.buensabor.enums.Rol;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UsuarioDto {
    @NotBlank
    private String auth0Identifier;
    @NotBlank
    private String identityProvider;
    @NotBlank
    private String username;
}

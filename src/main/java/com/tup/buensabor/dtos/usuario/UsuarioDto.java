package com.tup.buensabor.dtos.usuario;

import com.tup.buensabor.dtos.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UsuarioDto extends BaseDto {
    @NotBlank
    private String auth0Identifier;
    @NotBlank
    private String identityProvider;
    @NotBlank
    private String username;
}

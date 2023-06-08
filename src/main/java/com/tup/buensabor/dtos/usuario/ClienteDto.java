package com.tup.buensabor.dtos.usuario;

import com.tup.buensabor.dtos.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteDto extends BaseDto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private UsuarioDto usuario;
    private Date fechaAlta;
    private Date fechaModificacion;
    private Date fechaBaja;
}

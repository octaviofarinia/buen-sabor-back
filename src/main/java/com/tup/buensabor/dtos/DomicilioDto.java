package com.tup.buensabor.dtos;

import com.tup.buensabor.dtos.usuario.ClienteDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class DomicilioDto extends BaseDto {
    private String calle;
    private Integer numero;
    private String localidad;
    private Integer numeroVivienda;
    private Integer pisoVivienda;
    private ClienteDto cliente;
    private Date fechaAlta;
    private Date fechaModificacion;
    private Date fechaBaja;
}

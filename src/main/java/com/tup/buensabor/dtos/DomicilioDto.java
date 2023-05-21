package com.tup.buensabor.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DomicilioDto {
    private String calle;
    private Integer numero;
    private String localidad;
    private ClienteDto cliente;
    private Date fechaAlta;
    private Date fechaModificacion;
    private Date fechaBaja;
}

package com.tup.buensabor.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UnidadMedidaDto {
    private String denominacion;
    private String abreviatura;
    private Date fechaAlta;

    private Date fechaModificacion;

    private Date fechaBaja;
}

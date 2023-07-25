package com.tup.buensabor.dtos.domicilio;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tup.buensabor.dtos.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class DomicilioDto extends BaseDto {
    private String calle;
    private Integer numero;
    private Integer codigoPostal;
    private String localidad;
    private Integer numeroDpto;
    private Integer pisoDpto;
    private String auth0Id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private OffsetDateTime fechaAlta;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private OffsetDateTime fechaModificacion;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private OffsetDateTime fechaBaja;
}

package com.tup.buensabor.dtos.unidadmedida;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tup.buensabor.dtos.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnidadMedidaDto extends BaseDto {
    private String denominacion;
    private String abreviatura;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private OffsetDateTime fechaBaja;
}

package com.tup.buensabor.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnidadMedidaDto extends BaseDto {
    private String denominacion;
    private String abreviatura;
}

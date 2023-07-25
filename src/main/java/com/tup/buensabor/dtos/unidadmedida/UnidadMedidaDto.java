package com.tup.buensabor.dtos.unidadmedida;

import com.tup.buensabor.dtos.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnidadMedidaDto extends BaseDto {
    private String denominacion;
    private String abreviatura;
}

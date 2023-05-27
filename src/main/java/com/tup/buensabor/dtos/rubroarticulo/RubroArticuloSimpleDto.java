package com.tup.buensabor.dtos.rubroarticulo;

import com.tup.buensabor.dtos.BaseDto;
import lombok.Data;

@Data
public class RubroArticuloSimpleDto extends BaseDto {
    private Long id;
    private Long idRubroPadre;
    private String denominacion;
}

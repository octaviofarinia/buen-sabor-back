package com.tup.buensabor.dtos.rubroarticulo;

import com.tup.buensabor.dtos.BaseDto;
import lombok.Data;

@Data
public class RubroArticuloCompleteDto extends BaseDto {
    private Long id;
    private RubroArticuloSimpleDto rubroPadre;
    private Long idRubroPadre;
    private String denominacion;
}

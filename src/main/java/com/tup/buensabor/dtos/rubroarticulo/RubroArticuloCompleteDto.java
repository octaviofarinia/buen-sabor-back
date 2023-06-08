package com.tup.buensabor.dtos.rubroarticulo;

import com.tup.buensabor.dtos.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RubroArticuloCompleteDto extends BaseDto {
    private RubroArticuloSimpleDto rubroPadre;
    private Long idRubroPadre;
    private String denominacion;
}

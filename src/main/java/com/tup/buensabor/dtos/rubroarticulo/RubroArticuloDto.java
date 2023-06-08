package com.tup.buensabor.dtos.rubroarticulo;

import com.tup.buensabor.dtos.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class RubroArticuloDto extends BaseDto {
    private String denominacion;
    private Long idRubroPadre;
    private List<RubroArticuloDto> subRubros;
}

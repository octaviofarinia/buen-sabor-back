package com.tup.buensabor.dtos.rubroarticulo;

import com.tup.buensabor.dtos.BaseDto;
import lombok.Data;

import java.util.List;

@Data
public class RubroArticuloDto extends BaseDto {
    private Long id;
    private String denominacion;
    private Long idRubroPadre;
    private List<RubroArticuloDto> subRubros;
}

package com.tup.buensabor.dtos.rubroarticulo;

import lombok.Data;

@Data
public class RubroArticuloCompleteDto {
    private Long id;
    private RubroArticuloSimpleDto rubroPadre;
    private Long idRubroPadre;
    private String denominacion;
}

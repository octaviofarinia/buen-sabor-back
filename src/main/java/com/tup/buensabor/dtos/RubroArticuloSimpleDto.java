package com.tup.buensabor.dtos;

import lombok.Data;

@Data
public class RubroArticuloSimpleDto {
    private Long id;
    private Long idRubroPadre;
    private String denominacion;
}

package com.tup.buensabor.dtos.rubroarticulo;

import lombok.Data;

import java.util.List;

@Data
public class RubroArticuloDto {
    private Long id;
    private String denominacion;
    private List<RubroArticuloDto> subRubros;
}

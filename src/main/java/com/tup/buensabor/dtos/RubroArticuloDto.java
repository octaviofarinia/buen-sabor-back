package com.tup.buensabor.dtos;

import com.tup.buensabor.entities.RubroArticulo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class RubroArticuloDto {
    private String denominacion;
    private List<RubroArticuloDto> subRubros;
}

package com.tup.buensabor.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ArticuloManufacturadoDto {
    private Long id;
    private String denominacion;
    private String descripcion;
    private Integer tiempoEstimadoCocina;
    private String urlImagen;
    private Date fechaAlta;
    private Date fechaModificacion;
    private Date fechaBaja;
}

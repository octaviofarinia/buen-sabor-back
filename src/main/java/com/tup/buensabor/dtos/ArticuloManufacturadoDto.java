package com.tup.buensabor.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class ArticuloManufacturadoDto extends BaseDto {
    private Long id;
    private String denominacion;
    private String descripcion;
    private Integer tiempoEstimadoCocina;
    private String urlImagen;
    private Date fechaAlta;
    private Date fechaModificacion;
    private Date fechaBaja;
}

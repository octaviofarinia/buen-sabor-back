package com.tup.buensabor.dtos.detallearticulomanufacturado;

import com.tup.buensabor.dtos.BaseDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalleArticuloManufacturadoSimpleDto extends BaseDto {
    private BigDecimal cantidad;
    private Long idUnidadMedida;
    private Long idArticuloInsumo;
    private Long idArticuloManufacturado;
}

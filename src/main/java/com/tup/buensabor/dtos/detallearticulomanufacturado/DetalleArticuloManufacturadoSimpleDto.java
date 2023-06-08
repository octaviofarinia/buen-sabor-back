package com.tup.buensabor.dtos.detallearticulomanufacturado;

import com.tup.buensabor.dtos.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class DetalleArticuloManufacturadoSimpleDto extends BaseDto {
    private BigDecimal cantidad;
    private Long idArticuloInsumo;
    private Long idArticuloManufacturado;
}

package com.tup.buensabor.dtos.detallepedido;

import com.tup.buensabor.dtos.BaseDto;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoDto;
import com.tup.buensabor.dtos.articulomanufacturado.ArticuloManufacturadoDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class DetallePedidoSimpleDto extends BaseDto {
    private Integer cantidad;
    private BigDecimal subtotal;
    private BigDecimal subtotalCosto;
    private ArticuloInsumoDto articuloInsumo;
    private ArticuloManufacturadoDto articuloManufacturado;
    private Long idPedido;
}

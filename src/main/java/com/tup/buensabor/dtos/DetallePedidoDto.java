package com.tup.buensabor.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetallePedidoDto {
    private Integer cantidad;
    private BigDecimal subtotal;
    private ArticuloInsumoDto articuloInsumo;
    private ArticuloManufacturadoDto articuloManufacturado;
    private FacturaDto factura;
}

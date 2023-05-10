package com.tup.buensabor.dtos;

import java.math.BigDecimal;

public class DetalleFacturaDto {
    private Integer cantidad;
    private BigDecimal subtotal;
    private ArticuloInsumoDto articuloInsumo;
    private ArticuloManufacturadoDto articuloManufacturado;
    private PedidoDto pedido;
}

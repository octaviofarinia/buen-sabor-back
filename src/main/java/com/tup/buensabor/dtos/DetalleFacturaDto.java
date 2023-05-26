package com.tup.buensabor.dtos;

import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalleFacturaDto {
    private Integer cantidad;
    private BigDecimal subtotal;
    private ArticuloInsumoDto articuloInsumo;
    private ArticuloManufacturadoDto articuloManufacturado;
    private PedidoDto pedido;
}

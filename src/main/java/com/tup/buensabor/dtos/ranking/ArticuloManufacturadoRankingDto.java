package com.tup.buensabor.dtos.ranking;

import java.math.BigDecimal;

public interface ArticuloManufacturadoRankingDto {
    String getDenominacion();
    Long getCantidadVendida();
    BigDecimal getCosto();
    BigDecimal getPrecioVenta();
    BigDecimal getCostoTotal();
    BigDecimal getVentaTotal();
    BigDecimal getUtilidadTotal();
}

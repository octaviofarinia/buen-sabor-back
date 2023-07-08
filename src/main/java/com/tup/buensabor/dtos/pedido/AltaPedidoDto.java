package com.tup.buensabor.dtos.pedido;

import com.tup.buensabor.dtos.detallepedido.AltaPedidoDetallePedidoDto;
import com.tup.buensabor.dtos.factura.AltaPedidoFacturaDto;
import com.tup.buensabor.enums.TipoEnvio;

import java.math.BigDecimal;
import java.util.List;

public class AltaPedidoDto {
    private BigDecimal total;
    private String mp_status;
    private TipoEnvio tipoEnvio;
    private AltaPedidoFacturaDto factura;
    private Long idDomicilioEntrega;
    private String auth0Id;
    private List<AltaPedidoDetallePedidoDto> productos;
}

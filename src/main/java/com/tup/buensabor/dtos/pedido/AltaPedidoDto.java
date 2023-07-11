package com.tup.buensabor.dtos.pedido;

import com.tup.buensabor.dtos.detallepedido.AltaPedidoDetallePedidoDto;
import com.tup.buensabor.dtos.factura.AltaPedidoFacturaDto;
import com.tup.buensabor.enums.TipoEnvio;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AltaPedidoDto {
    private BigDecimal total;
    private String mpStatus;
    private TipoEnvio tipoEnvio;
    private AltaPedidoFacturaDto factura;
    private Long idDomicilioEntrega;
    private String auth0Id;
    private Integer tiempoEstimadoFinalizacion;
    private List<AltaPedidoDetallePedidoDto> productos;
}

package com.tup.buensabor.websocket;

import com.tup.buensabor.dtos.BaseDto;
import com.tup.buensabor.enums.EstadoPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PedidoWSDto extends BaseDto {
    private BigDecimal total;
    private EstadoPedido estado;
    private Date fechaAlta;
    private Date fechaModificacion;
}

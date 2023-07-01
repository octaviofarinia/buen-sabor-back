package com.tup.buensabor.websocket;

import com.tup.buensabor.entities.Base;
import com.tup.buensabor.enums.EstadoPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "pedido_ws")
public class PedidoWS extends Base {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long numero;

    @NotNull
    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;
}

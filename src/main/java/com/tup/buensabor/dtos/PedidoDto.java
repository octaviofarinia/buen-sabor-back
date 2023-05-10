package com.tup.buensabor.dtos;

import com.tup.buensabor.entities.Base;
import com.tup.buensabor.enums.EstadoPedido;
import com.tup.buensabor.enums.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

public class PedidoDto {
    private Date fechaPedido;
    private Integer numero;
    private Date horaEstimadaFinalizacion;
    private BigDecimal total;
    private EstadoPedido estado;
    private TipoEnvio tipoEnvio;
    private FacturaDto factura;
    private DomicilioDto domicilioEntrega;
    private ClienteDto cliente;
    private Date fechaAlta;
    private Date fechaModificacion;
    private Date fechaBaja;
}

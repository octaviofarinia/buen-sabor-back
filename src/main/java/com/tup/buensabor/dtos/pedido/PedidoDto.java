package com.tup.buensabor.dtos.pedido;

import com.tup.buensabor.dtos.BaseDto;
import com.tup.buensabor.dtos.domicilio.DomicilioDto;
import com.tup.buensabor.dtos.factura.FacturaDto;
import com.tup.buensabor.dtos.usuario.ClienteDto;
import com.tup.buensabor.enums.EstadoPedido;
import com.tup.buensabor.enums.TipoEnvio;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class PedidoDto extends BaseDto {
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

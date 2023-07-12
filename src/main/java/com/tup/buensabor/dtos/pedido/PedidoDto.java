package com.tup.buensabor.dtos.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tup.buensabor.dtos.BaseDto;
import com.tup.buensabor.dtos.domicilio.DomicilioDto;
import com.tup.buensabor.dtos.factura.FacturaDto;
import com.tup.buensabor.dtos.usuario.ClienteDto;
import com.tup.buensabor.enums.EstadoPedido;
import com.tup.buensabor.enums.TipoEnvio;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class PedidoDto extends BaseDto {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private OffsetDateTime fechaPedido;
    @JsonFormat(pattern = "HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private OffsetDateTime  horaEstimadaFinalizacion;
    private BigDecimal total;
    private BigDecimal totalCosto;
    private EstadoPedido estado;
    private TipoEnvio tipoEnvio;
    private DomicilioDto domicilioEntrega;
    private ClienteDto cliente;
    private Date fechaAlta;
    private Date fechaModificacion;
    private Date fechaBaja;
}

package com.tup.buensabor.dtos.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tup.buensabor.dtos.BaseDto;
import com.tup.buensabor.dtos.domicilio.DomicilioDto;
import com.tup.buensabor.dtos.usuario.ClienteDto;
import com.tup.buensabor.enums.EstadoPedido;
import com.tup.buensabor.enums.FormaPago;
import com.tup.buensabor.enums.TipoEnvio;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

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
    private FormaPago formaPago;
    private DomicilioDto domicilioEntrega;
    private ClienteDto cliente;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private OffsetDateTime fechaAlta;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private OffsetDateTime fechaModificacion;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private OffsetDateTime fechaBaja;
}

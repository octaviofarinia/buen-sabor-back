package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.detallepedido.DetallePedidoDto;
import com.tup.buensabor.dtos.detallepedido.DetallePedidoSimpleDto;
import com.tup.buensabor.entities.DetallePedido;
import com.tup.buensabor.mappers.utils.DateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface DetallePedidoMapper extends BaseMapper<DetallePedido, DetallePedidoDto> {
    DetallePedidoDto toDTO(DetallePedido source);
    DetallePedido toEntity(DetallePedidoDto source);

    List<DetallePedidoDto> toDTOsList(List<DetallePedido> source);
    List<DetallePedido> toEntitiesList(List<DetallePedidoDto> source);

    @Mapping(source = "source.pedido.id", target = "idPedido")
    DetallePedidoSimpleDto toSimpleDTO(DetallePedido source);
    List<DetallePedidoSimpleDto> toSimpleDTOsList(List<DetallePedido> source);
}

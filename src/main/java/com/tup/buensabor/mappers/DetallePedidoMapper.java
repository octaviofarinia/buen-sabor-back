package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.detallepedido.DetallePedidoDto;
import com.tup.buensabor.entities.DetallePedido;
import com.tup.buensabor.mappers.utils.DateMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface DetallePedidoMapper extends BaseMapper<DetallePedido, DetallePedidoDto> {
    DetallePedidoDto toDTO(DetallePedido source);
    DetallePedido toEntity(DetallePedidoDto source);

    List<DetallePedidoDto> toDTOsList(List<DetallePedido> source);
    List<DetallePedido> toEntitiesList(List<DetallePedidoDto> source);
}

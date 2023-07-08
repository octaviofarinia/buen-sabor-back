package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.detallepedido.DetallePedidoDto;
import com.tup.buensabor.entities.DetallePedido;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetallePedidoMapper extends BaseMapper<DetallePedido, DetallePedidoDto> {
    static DetallePedidoMapper getInstance() {
        return Mappers.getMapper(DetallePedidoMapper.class);
    }

    DetallePedidoDto toDTO(DetallePedido source);
    DetallePedido toEntity(DetallePedidoDto source);

    List<DetallePedidoDto> toDTOsList(List<DetallePedido> source);
    List<DetallePedido> toEntitiesList(List<DetallePedidoDto> source);
}

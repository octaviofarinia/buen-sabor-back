package com.tup.buensabor.websocket;

import com.tup.buensabor.mappers.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoWSMapper extends BaseMapper<PedidoWS, PedidoWSDto> {

    static PedidoWSMapper getInstance() {
        return Mappers.getMapper(PedidoWSMapper.class);
    }

    PedidoWSDto toDTO(PedidoWS source);
    PedidoWS toEntity(PedidoWSDto source);

    List<PedidoWSDto> toDTOsList(List<PedidoWS> source);
    List<PedidoWS> toEntitiesList(List<PedidoWSDto> source);
}

package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.pedido.PedidoDto;
import com.tup.buensabor.entities.Pedido;
import com.tup.buensabor.mappers.utils.DateMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface PedidoMapper extends BaseMapper<Pedido, PedidoDto> {
    PedidoDto toDTO(Pedido source);
    Pedido toEntity(PedidoDto source);

    List<PedidoDto> toDTOsList(List<Pedido> source);
    List<Pedido> toEntitiesList(List<PedidoDto> source);
}

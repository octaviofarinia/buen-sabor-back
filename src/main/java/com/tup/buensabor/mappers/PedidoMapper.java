package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.PedidoDto;
import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloSimpleDto;
import com.tup.buensabor.entities.Pedido;
import com.tup.buensabor.entities.RubroArticulo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper extends BaseMapper<Pedido, PedidoDto> {
    static PedidoMapper getInstance() {
        return Mappers.getMapper(PedidoMapper.class);
    }

    PedidoDto toDTO(Pedido source);
    Pedido toEntity(PedidoDto source);

    List<PedidoDto> toDTOsList(List<Pedido> source);
    List<Pedido> toEntitiesList(List<PedidoDto> source);
}

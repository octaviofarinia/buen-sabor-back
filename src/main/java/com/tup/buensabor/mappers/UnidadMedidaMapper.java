package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.UnidadMedidaDto;
import com.tup.buensabor.entities.UnidadMedida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadMedidaMapper {
    static UnidadMedidaMapper getInstance() {
        return Mappers.getMapper(UnidadMedidaMapper.class);
    }

    UnidadMedidaDto toDTO(UnidadMedida source);
    UnidadMedida toEntity(UnidadMedidaDto source);

    List<UnidadMedidaDto> toDTOsList(List<UnidadMedida> source);
    List<UnidadMedida> toEntitiesList(List<UnidadMedidaDto> source);
}

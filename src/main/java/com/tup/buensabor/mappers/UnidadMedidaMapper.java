package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.unidadmedida.UnidadMedidaDto;
import com.tup.buensabor.entities.UnidadMedida;
import com.tup.buensabor.mappers.utils.DateMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface UnidadMedidaMapper extends BaseMapper<UnidadMedida, UnidadMedidaDto> {
    UnidadMedidaDto toDTO(UnidadMedida source);
    UnidadMedida toEntity(UnidadMedidaDto source);

    List<UnidadMedidaDto> toDTOsList(List<UnidadMedida> source);
    List<UnidadMedida> toEntitiesList(List<UnidadMedidaDto> source);
}

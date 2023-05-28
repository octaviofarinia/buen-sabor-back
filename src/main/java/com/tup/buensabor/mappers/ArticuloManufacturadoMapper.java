package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.ArticuloManufacturadoDto;
import com.tup.buensabor.entities.ArticuloManufacturado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticuloManufacturadoMapper extends BaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto> {
    static ArticuloManufacturadoMapper getInstance() {
        return Mappers.getMapper(ArticuloManufacturadoMapper.class);
    }

    @Mapping(source = "id", target = "id")
    ArticuloManufacturadoDto toDTO(ArticuloManufacturado source);

    @Mapping(source = "id", target = "id")
    ArticuloManufacturado toEntity(ArticuloManufacturadoDto source);

    List<ArticuloManufacturadoDto> toDTOsList(List<ArticuloManufacturado> source);
    List<ArticuloManufacturado> toEntitiesList(List<ArticuloManufacturadoDto> source);
}

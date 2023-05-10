package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.ArticuloInsumoDto;
import com.tup.buensabor.entities.ArticuloInsumo;
import com.tup.buensabor.entities.ArticuloInsumo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticuloInsumoMapper {
    static ArticuloInsumoMapper getInstance() {
        return Mappers.getMapper(ArticuloInsumoMapper.class);
    }

    ArticuloInsumoDto toDTO(ArticuloInsumo source);
    ArticuloInsumo toEntity(ArticuloInsumoDto source);

    List<ArticuloInsumoDto> toDTOsList(List<ArticuloInsumo> source);
    List<ArticuloInsumo> toEntitiesList(List<ArticuloInsumoDto> source);
}

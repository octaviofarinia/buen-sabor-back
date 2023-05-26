package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoCompleteDto;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoDto;
import com.tup.buensabor.entities.ArticuloInsumo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticuloInsumoMapper {
    static ArticuloInsumoMapper getInstance() {
        return Mappers.getMapper(ArticuloInsumoMapper.class);
    }

    @Mapping(source = "source.unidadMedida.id", target = "idUnidadMedida")
    @Mapping(source = "source.rubroArticulo.id", target = "idRubroArticulo")
    ArticuloInsumoDto toDTO(ArticuloInsumo source);
    ArticuloInsumo toEntity(ArticuloInsumoDto source);

    ArticuloInsumoCompleteDto toCompleteDTO(ArticuloInsumo source);
    List<ArticuloInsumoCompleteDto> toCompleteDTOList(List<ArticuloInsumo> source);

    List<ArticuloInsumoDto> toDTOsList(List<ArticuloInsumo> source);
    List<ArticuloInsumo> toEntitiesList(List<ArticuloInsumoDto> source);
}

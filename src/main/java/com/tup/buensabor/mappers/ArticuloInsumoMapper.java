package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoCompleteDto;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoDto;
import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloSimpleDto;
import com.tup.buensabor.entities.ArticuloInsumo;
import com.tup.buensabor.entities.RubroArticulo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticuloInsumoMapper extends BaseMapper<ArticuloInsumo, ArticuloInsumoCompleteDto> {
    static ArticuloInsumoMapper getInstance() {
        return Mappers.getMapper(ArticuloInsumoMapper.class);
    }

    @Mapping(source = "source.unidadMedida.id", target = "idUnidadMedida")
    @Mapping(source = "source.rubroArticulo.id", target = "idRubroArticulo")
    ArticuloInsumoDto toSimpleDTO(ArticuloInsumo source);
    ArticuloInsumo toEntity(ArticuloInsumoDto source);


    ArticuloInsumoCompleteDto toDTO(ArticuloInsumo source);
    ArticuloInsumo toEntity(ArticuloInsumoCompleteDto source);


    List<ArticuloInsumoCompleteDto> toDTOsList(List<ArticuloInsumo> source);
    List<ArticuloInsumo> toEntitiesList(List<ArticuloInsumoCompleteDto> source);
}

package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoCompleteDto;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoDto;
import com.tup.buensabor.entities.ArticuloInsumo;
import com.tup.buensabor.mappers.utils.DateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface ArticuloInsumoMapper extends BaseMapper<ArticuloInsumo, ArticuloInsumoCompleteDto> {
    @Mapping(source = "source.unidadMedida.id", target = "idUnidadMedida")
    @Mapping(source = "source.rubroArticulo.id", target = "idRubroArticulo")
    ArticuloInsumoDto toSimpleDTO(ArticuloInsumo source);
    ArticuloInsumo toEntity(ArticuloInsumoDto source);


    ArticuloInsumoCompleteDto toDTO(ArticuloInsumo source);
    ArticuloInsumo toEntity(ArticuloInsumoCompleteDto source);


    List<ArticuloInsumoCompleteDto> toDTOsList(List<ArticuloInsumo> source);
    List<ArticuloInsumo> toEntitiesList(List<ArticuloInsumoCompleteDto> source);
}

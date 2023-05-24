package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloCompleteDto;
import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloDto;
import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloSimpleDto;
import com.tup.buensabor.entities.RubroArticulo;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RubroArticuloMapper {

    static RubroArticuloMapper getInstance() {
        return Mappers.getMapper(RubroArticuloMapper.class);
    }

    @Mapping(source = "source.rubroPadre.id", target = "idRubroPadre")
    @Mapping(source = "source.id", target = "id")
    RubroArticuloSimpleDto toSimpleDTO(RubroArticulo source);

    @Mapping(source = "source.rubroPadre.id", target = "idRubroPadre")
    @Mapping(source = "source.rubroPadre", target = "rubroPadre")
    @Mapping(source = "source.id", target = "id")
    RubroArticuloCompleteDto toCompleteDTO(RubroArticulo source);

    List<RubroArticuloSimpleDto> toSimpleDTOList(List<RubroArticulo> source);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "source.rubroPadre.id", target = "idRubroPadre")
    RubroArticuloCompleteDto toRubroArticuloDTO(RubroArticulo source, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @DoIgnore
    default RubroArticuloCompleteDto toRubroArticuloDTO(RubroArticulo source) {
        return toRubroArticuloDTO(source, new CycleAvoidingMappingContext());
    }



}

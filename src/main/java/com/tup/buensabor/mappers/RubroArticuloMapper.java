package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.RubroArticuloDto;
import com.tup.buensabor.dtos.RubroArticuloSimpleDto;
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

    List<RubroArticuloSimpleDto> toSimpleDTOList(List<RubroArticulo> source);

    RubroArticuloDto toRubroArticuloDTO(RubroArticulo source, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @DoIgnore
    default RubroArticuloDto toRubroArticuloDTO(RubroArticulo source) {
        return toRubroArticuloDTO(source, new CycleAvoidingMappingContext());
    }

}

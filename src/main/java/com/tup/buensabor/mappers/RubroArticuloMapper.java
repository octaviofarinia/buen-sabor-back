package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.RubroArticuloSimpleDto;
import com.tup.buensabor.entities.RubroArticulo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RubroArticuloMapper {

    static RubroArticuloMapper getInstance() {
        return Mappers.getMapper(RubroArticuloMapper.class);
    }

    RubroArticuloSimpleDto toSimpleDTO(RubroArticulo source);

}

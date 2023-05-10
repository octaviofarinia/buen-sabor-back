package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.DomicilioDto;
import com.tup.buensabor.entities.Domicilio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DomicilioMapper {
    static DomicilioMapper getInstance() {
        return Mappers.getMapper(DomicilioMapper.class);
    }

    DomicilioDto toDTO(Domicilio source);
    Domicilio toEntity(DomicilioDto source);

    List<DomicilioDto> toDTOsList(List<Domicilio> source);
    List<Domicilio> toEntitiesList(List<DomicilioDto> source);
}

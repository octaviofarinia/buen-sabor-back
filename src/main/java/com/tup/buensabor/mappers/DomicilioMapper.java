package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.DomicilioDto;
import com.tup.buensabor.entities.Domicilio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DomicilioMapper extends BaseMapper<Domicilio, DomicilioDto> {
    static DomicilioMapper getInstance() {
        return Mappers.getMapper(DomicilioMapper.class);
    }

    @Mapping(source = "source.cliente.usuario.auth0Id", target = "auth0Id")
    DomicilioDto toDTO(Domicilio source);
    Domicilio toEntity(DomicilioDto source);

    List<DomicilioDto> toDTOsList(List<Domicilio> source);
    List<Domicilio> toEntitiesList(List<DomicilioDto> source);
}

package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.domicilio.DomicilioDto;
import com.tup.buensabor.entities.Domicilio;
import com.tup.buensabor.mappers.utils.DateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface DomicilioMapper extends BaseMapper<Domicilio, DomicilioDto> {
    @Mapping(source = "source.cliente.usuario.auth0Id", target = "auth0Id")
    DomicilioDto toDTO(Domicilio source);
    Domicilio toEntity(DomicilioDto source);

    List<DomicilioDto> toDTOsList(List<Domicilio> source);
    List<Domicilio> toEntitiesList(List<DomicilioDto> source);
}

package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.usuario.ClienteDto;
import com.tup.buensabor.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    static ClienteMapper getInstance() {
        return Mappers.getMapper(ClienteMapper.class);
    }

    ClienteDto toDTO(Cliente source);
    Cliente toEntity(ClienteDto source);

    List<ClienteDto> toDTOsList(List<Cliente> source);
    List<Cliente> toEntitiesList(List<ClienteDto> source);
}

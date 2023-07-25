package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.usuario.ClienteDto;
import com.tup.buensabor.entities.Cliente;
import com.tup.buensabor.mappers.utils.DateMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface ClienteMapper extends BaseMapper<Cliente, ClienteDto> {
    ClienteDto toDTO(Cliente source);
    Cliente toEntity(ClienteDto source);

    List<ClienteDto> toDTOsList(List<Cliente> source);
    List<Cliente> toEntitiesList(List<ClienteDto> source);
}

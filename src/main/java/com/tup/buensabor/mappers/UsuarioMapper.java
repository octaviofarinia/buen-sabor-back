package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.UsuarioDto;
import com.tup.buensabor.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    static UsuarioMapper getInstance() {
        return Mappers.getMapper(UsuarioMapper.class);
    }

    UsuarioDto toDTO(Usuario source);
    Usuario toEntity(UsuarioDto source);

    List<UsuarioDto> toDTOsList(List<Usuario> source);
    List<Usuario> toEntitiesList(List<UsuarioDto> source);
}

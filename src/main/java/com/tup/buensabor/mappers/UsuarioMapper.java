package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.usuario.UsuarioDto;
import com.tup.buensabor.entities.Usuario;
import com.tup.buensabor.mappers.utils.DateMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDto>{
    UsuarioDto toDTO(Usuario source);
    Usuario toEntity(UsuarioDto source);


    List<UsuarioDto> toDTOsList(List<Usuario> source);
    List<Usuario> toEntitiesList(List<UsuarioDto> source);
}

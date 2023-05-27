package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloSimpleDto;
import com.tup.buensabor.dtos.usuario.UsuarioDto;
import com.tup.buensabor.entities.RubroArticulo;
import com.tup.buensabor.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDto>{
    static UsuarioMapper getInstance() {
        return Mappers.getMapper(UsuarioMapper.class);
    }

    UsuarioDto toDTO(Usuario source);
    Usuario toEntity(UsuarioDto source);


    List<UsuarioDto> toDTOsList(List<Usuario> source);
    List<Usuario> toEntitiesList(List<UsuarioDto> source);
}

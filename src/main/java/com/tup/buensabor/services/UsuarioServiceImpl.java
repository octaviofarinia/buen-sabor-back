package com.tup.buensabor.services;

import com.tup.buensabor.dtos.usuario.UsuarioDto;
import com.tup.buensabor.entities.Usuario;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.UsuarioMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.UsuarioRepository;
import com.tup.buensabor.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, UsuarioDto, Long> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper = UsuarioMapper.getInstance();

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository, BaseMapper<Usuario, UsuarioDto> baseMapper) {
        super(baseRepository, baseMapper);
    }

    public Usuario save(UsuarioDto userDto) throws ServicioException {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByAuth0IdentifierAndIdentityProvider(
                userDto.getAuth0Identifier(),
                userDto.getIdentityProvider()
        );
        if (optionalUsuario.isPresent()) {
            return null;
        }

        Usuario usuario = usuarioMapper.toEntity(userDto);
        usuario.setFechaAlta(new Date());
        return this.save(usuario);
    }

}

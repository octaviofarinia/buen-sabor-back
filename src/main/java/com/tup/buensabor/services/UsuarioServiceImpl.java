package com.tup.buensabor.services;

import com.tup.buensabor.dtos.usuario.UsuarioDto;
import com.tup.buensabor.entities.Cliente;
import com.tup.buensabor.entities.Usuario;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.UsuarioMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.UsuarioRepository;
import com.tup.buensabor.services.interfaces.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Log4j2
@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, UsuarioDto, Long> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository, BaseMapper<Usuario, UsuarioDto> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Transactional
    public Usuario postRegisterSave(UsuarioDto userDto) throws ServicioException {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByAuth0Id(userDto.getAuth0Id());
        if (optionalUsuario.isPresent()) {
            return null;
        }

        Usuario usuario = usuarioMapper.toEntity(userDto);
        usuario.setFechaAlta(new Date());
        usuario = this.save(usuario);

        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        cliente.setFechaAlta(new Date());
        cliente.setEmail(userDto.getEmail());

        clienteService.save(cliente);

        return usuario;
    }

}

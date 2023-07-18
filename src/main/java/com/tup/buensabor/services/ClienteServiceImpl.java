package com.tup.buensabor.services;

import com.tup.buensabor.dtos.usuario.ClienteDto;
import com.tup.buensabor.entities.Cliente;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.ClienteMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.ClienteRepository;
import com.tup.buensabor.services.interfaces.ClienteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, ClienteDto, Long> implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    public ClienteServiceImpl(BaseRepository<Cliente, Long> baseRepository, BaseMapper<Cliente, ClienteDto> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Transactional
    public Optional<Cliente> findOptionalByAuth0Id(String id) throws ServicioException {
        try {
            return clienteRepository.findByUsuarioAuth0Id(id);
        }catch (Exception e) {
            throw new ServicioException(e.getMessage());
        }
    }

}

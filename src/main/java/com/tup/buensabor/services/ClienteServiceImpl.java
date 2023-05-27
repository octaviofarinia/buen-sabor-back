package com.tup.buensabor.services;

import com.tup.buensabor.dtos.usuario.ClienteDto;
import com.tup.buensabor.entities.Cliente;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.ClienteMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.ClienteRepository;
import com.tup.buensabor.services.interfaces.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, ClienteDto, Long> implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private ClienteMapper clienteMapper = ClienteMapper.getInstance();

    public ClienteServiceImpl(BaseRepository<Cliente, Long> baseRepository, BaseMapper<Cliente, ClienteDto> baseMapper) {
        super(baseRepository, baseMapper);
    }


}

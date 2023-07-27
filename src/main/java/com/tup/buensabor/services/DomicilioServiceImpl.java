package com.tup.buensabor.services;

import com.tup.buensabor.dtos.domicilio.DomicilioDto;
import com.tup.buensabor.entities.Cliente;
import com.tup.buensabor.entities.Domicilio;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.DomicilioMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.DomicilioRepository;
import com.tup.buensabor.services.interfaces.DomicilioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DomicilioServiceImpl extends BaseServiceImpl<Domicilio, DomicilioDto, Long> implements DomicilioService {

    @Autowired
    private DomicilioRepository domicilioRepository;

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private DomicilioMapper domicilioMapper;

    public DomicilioServiceImpl(BaseRepository<Domicilio, Long> baseRepository, BaseMapper<Domicilio, DomicilioDto> baseMapper) {
        super(baseRepository, baseMapper);
    }
    
    @Transactional
    public DomicilioDto save(DomicilioDto domicilioDto) throws ServicioException {

        Optional<Cliente> optionalCliente = clienteService.findOptionalByAuth0Id(domicilioDto.getAuth0Id());
        if(optionalCliente.isEmpty()) {
            throw new ServicioException("No se encontro un cliente con el id provisto.");
        }

        Domicilio domicilio = domicilioMapper.toEntity(domicilioDto);
        domicilio.setCliente(optionalCliente.get());
        domicilio.setFechaAlta(new Date());

        return domicilioMapper.toDTO(save(domicilio));
    }

    @Transactional
    public DomicilioDto update(DomicilioDto domicilioDto) throws ServicioException {
        if (domicilioDto.getId() == null || domicilioDto.getId() <= 0) {
            throw new ServicioException("El campo id es obligatorio y mayor a cero.");
        }

        Optional<Domicilio> optionalDomicilio = baseRepository.findById(domicilioDto.getId());
        if(optionalDomicilio.isEmpty()) {
            throw new ServicioException("No se encontro el domicilio con el id dado.");
        }

        Optional<Cliente> optionalCliente = clienteService.findOptionalByAuth0Id(domicilioDto.getAuth0Id());
        if(optionalCliente.isEmpty()) {
            throw new ServicioException("No se encontro un cliente con el id provisto.");
        }

        Domicilio domicilio = domicilioMapper.toEntity(domicilioDto);
        domicilio.setFechaAlta(optionalDomicilio.get().getFechaAlta());
        domicilio.setFechaModificacion(new Date());

        return domicilioMapper.toDTO(baseRepository.save(domicilio));
    }

    @Transactional
    public void softDelete(Long id) throws ServicioException {
        Optional<Domicilio> optionalDomicilio = domicilioRepository.findById(id);
        if(optionalDomicilio.isEmpty()) {
            throw new ServicioException("No existe un domicilio con el id seleccionado.");
        }

        Domicilio domicilio = optionalDomicilio.get();
        domicilio.setFechaBaja(new Date());

        domicilioRepository.save(domicilio);
    }

    public List<DomicilioDto> findByAuth0Id(String auth0Id) {
        List<Domicilio> domicilios = domicilioRepository.getByClienteAuth0Id(auth0Id);

        return domicilioMapper.toDTOsList(domicilios);
    }
}

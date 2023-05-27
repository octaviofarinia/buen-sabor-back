package com.tup.buensabor.services;

import com.tup.buensabor.dtos.DomicilioDto;
import com.tup.buensabor.entities.Domicilio;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.DomicilioMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.DomicilioRepository;
import com.tup.buensabor.services.interfaces.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomicilioServiceImpl extends BaseServiceImpl<Domicilio, DomicilioDto, Long> implements DomicilioService {

    @Autowired
    private DomicilioRepository domicilioRepository;

    private DomicilioMapper domicilioMapper = DomicilioMapper.getInstance();

    public DomicilioServiceImpl(BaseRepository<Domicilio, Long> baseRepository, BaseMapper<Domicilio, DomicilioDto> baseMapper) {
        super(baseRepository, baseMapper);
    }


}

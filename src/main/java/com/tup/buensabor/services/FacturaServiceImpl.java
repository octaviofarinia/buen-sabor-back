package com.tup.buensabor.services;

import com.tup.buensabor.dtos.FacturaDto;
import com.tup.buensabor.entities.Factura;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.FacturaMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.FacturaRepository;
import com.tup.buensabor.services.interfaces.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaServiceImpl extends BaseServiceImpl<Factura, FacturaDto, Long> implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    private FacturaMapper facturaMapper = FacturaMapper.getInstance();

    public FacturaServiceImpl(BaseRepository<Factura, Long> baseRepository, BaseMapper<Factura, FacturaDto> baseMapper) {
        super(baseRepository, baseMapper);
    }


}

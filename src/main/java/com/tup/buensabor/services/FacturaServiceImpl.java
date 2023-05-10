package com.tup.buensabor.services;

import com.tup.buensabor.entities.Factura;
import com.tup.buensabor.mappers.FacturaMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.FacturaRepository;
import com.tup.buensabor.services.interfaces.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaServiceImpl extends BaseServiceImpl<Factura, Long> implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    private FacturaMapper facturaMapper = FacturaMapper.getInstance();

    public FacturaServiceImpl(BaseRepository<Factura, Long> baseRepository) {
        super(baseRepository);
    }


}

package com.tup.buensabor.services;

import com.tup.buensabor.dtos.DetalleFacturaDto;
import com.tup.buensabor.entities.DetalleFactura;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.DetalleFacturaMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.DetalleFacturaRepository;
import com.tup.buensabor.services.interfaces.DetalleFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleFacturaServiceImpl extends BaseServiceImpl<DetalleFactura, DetalleFacturaDto, Long> implements DetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    private DetalleFacturaMapper detalleFacturaMapper = DetalleFacturaMapper.getInstance();

    public DetalleFacturaServiceImpl(BaseRepository<DetalleFactura, Long> baseRepository, BaseMapper<DetalleFactura, DetalleFacturaDto> baseMapper) {
        super(baseRepository, baseMapper);
    }


}

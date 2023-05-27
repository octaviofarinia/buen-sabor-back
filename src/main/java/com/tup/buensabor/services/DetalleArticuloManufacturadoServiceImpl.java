package com.tup.buensabor.services;

import com.tup.buensabor.dtos.DetalleArticuloManufacturadoDto;
import com.tup.buensabor.entities.DetalleArticuloManufacturado;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.DetalleArticuloManufacturadoMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.DetalleArticuloManufacturadoRepository;
import com.tup.buensabor.services.interfaces.DetalleArticuloManufacturadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleArticuloManufacturadoServiceImpl extends BaseServiceImpl<DetalleArticuloManufacturado, DetalleArticuloManufacturadoDto, Long> implements DetalleArticuloManufacturadoService {

    @Autowired
    private DetalleArticuloManufacturadoRepository detalleArticuloManufacturadoRepository;

    private DetalleArticuloManufacturadoMapper detalleArticuloManufacturadoMapper = DetalleArticuloManufacturadoMapper.getInstance();

    public DetalleArticuloManufacturadoServiceImpl(BaseRepository<DetalleArticuloManufacturado, Long> baseRepository, BaseMapper<DetalleArticuloManufacturado, DetalleArticuloManufacturadoDto> baseMapper) {
        super(baseRepository, baseMapper);
    }


}

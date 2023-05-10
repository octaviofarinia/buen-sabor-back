package com.tup.buensabor.services;

import com.tup.buensabor.entities.DetalleArticuloManufacturado;
import com.tup.buensabor.mappers.DetalleArticuloManufacturadoMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.DetalleArticuloManufacturadoRepository;
import com.tup.buensabor.services.interfaces.DetalleArticuloManufacturadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleArticuloManufacturadoServiceImpl extends BaseServiceImpl<DetalleArticuloManufacturado, Long> implements DetalleArticuloManufacturadoService {

    @Autowired
    private DetalleArticuloManufacturadoRepository detalleArticuloManufacturadoRepository;

    private DetalleArticuloManufacturadoMapper detalleArticuloManufacturadoMapper = DetalleArticuloManufacturadoMapper.getInstance();

    public DetalleArticuloManufacturadoServiceImpl(BaseRepository<DetalleArticuloManufacturado, Long> baseRepository) {
        super(baseRepository);
    }


}

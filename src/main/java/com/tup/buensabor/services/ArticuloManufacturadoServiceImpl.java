package com.tup.buensabor.services;

import com.tup.buensabor.entities.ArticuloManufacturado;
import com.tup.buensabor.mappers.ArticuloManufacturadoMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.ArticuloManufacturadoRepository;
import com.tup.buensabor.services.interfaces.ArticuloManufacturadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloManufacturadoServiceImpl extends BaseServiceImpl<ArticuloManufacturado, Long> implements ArticuloManufacturadoService {

    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;

    private ArticuloManufacturadoMapper articuloManufacturadoMapper = ArticuloManufacturadoMapper.getInstance();

    public ArticuloManufacturadoServiceImpl(BaseRepository<ArticuloManufacturado, Long> baseRepository) {
        super(baseRepository);
    }

}

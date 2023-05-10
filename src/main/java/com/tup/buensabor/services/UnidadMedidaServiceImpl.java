package com.tup.buensabor.services;

import com.tup.buensabor.entities.UnidadMedida;
import com.tup.buensabor.mappers.UnidadMedidaMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.UnidadMedidaRepository;
import com.tup.buensabor.services.interfaces.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnidadMedidaServiceImpl extends BaseServiceImpl<UnidadMedida, Long> implements UnidadMedidaService {

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    private UnidadMedidaMapper unidadMedidaMapper = UnidadMedidaMapper.getInstance();

    public UnidadMedidaServiceImpl(BaseRepository<UnidadMedida, Long> baseRepository) {
        super(baseRepository);
    }


}

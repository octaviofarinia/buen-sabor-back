package com.tup.buensabor.services;

import com.tup.buensabor.dtos.RubroArticuloSimpleDto;
import com.tup.buensabor.entities.RubroArticulo;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.RubroArticuloMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.RubroArticuloRepository;
import com.tup.buensabor.services.interfaces.RubroArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RubroArticuloServiceImpl extends BaseServiceImpl<RubroArticulo, Long> implements RubroArticuloService {

    @Autowired
    private RubroArticuloRepository rubroArticuloRepository;

    private RubroArticuloMapper rubroArticuloMapper = RubroArticuloMapper.getInstance();

    public RubroArticuloServiceImpl(BaseRepository<RubroArticulo, Long> baseRepository) {
        super(baseRepository);
    }

    public RubroArticulo save(RubroArticuloSimpleDto rubroDto) throws Exception {
        if(rubroDto.getIdRubroPadre() != null) {
            if(baseRepository.existsById(rubroDto.getIdRubroPadre())) {
                RubroArticulo entity = new RubroArticulo(rubroDto.getDenominacion(), baseRepository.getReferenceById(rubroDto.getIdRubroPadre()));
                return rubroArticuloRepository.save(entity);
            } else {
                throw new ServicioException("No existe un RubroArticulo con id " + rubroDto.getIdRubroPadre());
            }
        } else {
            RubroArticulo entity = new RubroArticulo(rubroDto.getDenominacion(), null);
            return rubroArticuloRepository.save(entity);
        }
    }

}

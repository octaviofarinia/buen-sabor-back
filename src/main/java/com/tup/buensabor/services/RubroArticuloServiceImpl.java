package com.tup.buensabor.services;

import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloCompleteDto;
import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloSimpleDto;
import com.tup.buensabor.entities.RubroArticulo;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.RubroArticuloMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.RubroArticuloRepository;
import com.tup.buensabor.services.interfaces.RubroArticuloService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RubroArticuloServiceImpl extends BaseServiceImpl<RubroArticulo, RubroArticuloSimpleDto, Long> implements RubroArticuloService {

    @Autowired
    private RubroArticuloRepository rubroArticuloRepository;

    @Autowired
    private RubroArticuloMapper rubroArticuloMapper;

    public RubroArticuloServiceImpl(BaseRepository<RubroArticulo, Long> baseRepository, BaseMapper<RubroArticulo, RubroArticuloSimpleDto> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Transactional
    public RubroArticuloCompleteDto save(RubroArticuloCompleteDto rubroDto) throws Exception {
        if(rubroDto.getIdRubroPadre() != null) {
            if(baseRepository.existsById(rubroDto.getIdRubroPadre())) {
                RubroArticulo entity = new RubroArticulo(rubroDto.getDenominacion(), rubroArticuloRepository.findById(rubroDto.getIdRubroPadre()).get());
                entity.setFechaAlta(new Date());
                RubroArticulo entityPersisted = rubroArticuloRepository.save(entity);
                return rubroArticuloMapper.toCompleteDTO(entityPersisted);
            } else {
                throw new ServicioException("No existe un RubroArticulo con id " + rubroDto.getIdRubroPadre());
            }
        } else {
            RubroArticulo entity = new RubroArticulo(rubroDto.getDenominacion(), null);
            entity.setFechaAlta(new Date());
            return rubroArticuloMapper.toCompleteDTO(rubroArticuloRepository.save(entity));
        }
    }

    public RubroArticuloCompleteDto getOne(Long id) {
        RubroArticulo entity = rubroArticuloRepository.findById(id).get();
        RubroArticuloCompleteDto dto = rubroArticuloMapper.toRubroArticuloDTO(entity);
        return dto;
    }

    public RubroArticuloCompleteDto getOneComplete(Long id) {
        RubroArticulo entity = rubroArticuloRepository.findById(id).get();
        RubroArticuloCompleteDto dto = rubroArticuloMapper.toCompleteDTO(entity);
        return dto;
    }

    public List<RubroArticuloCompleteDto> getAllParents() {
        List<RubroArticuloCompleteDto> dtoList = new ArrayList<RubroArticuloCompleteDto>();
        List<RubroArticulo> parentsList = rubroArticuloRepository.getAllParents();

        for (RubroArticulo rubroEntity : parentsList) {
            dtoList.add(rubroArticuloMapper.toRubroArticuloDTO(rubroEntity));
        }

        return dtoList;
    }

    public List<RubroArticuloCompleteDto> getAllSimple() {
        return rubroArticuloMapper.toCompleteDTOList(rubroArticuloRepository.getAll());
    }

    @Transactional
    public RubroArticuloCompleteDto update(RubroArticuloCompleteDto rubroDto) throws ServicioException {
        try {
            if (rubroDto.getId() == null || rubroDto.getId() <= 0) {
                throw new ServicioException("El campo id es obligatorio y mayor a cero.");
            }

            Optional<RubroArticulo> entityOptional = baseRepository.findById(rubroDto.getId());

            if(entityOptional.isEmpty()) {
                throw new ServicioException("No se encontro la entidad con el id dado.");
            }

            RubroArticulo entityDB = entityOptional.get();

            if(rubroDto.getIdRubroPadre() != null) {
                if(baseRepository.existsById(rubroDto.getIdRubroPadre())) {
                    entityDB.setRubroPadre(rubroArticuloRepository.getReferenceById(rubroDto.getIdRubroPadre()));
                } else {
                    throw new ServicioException("No existe un RubroArticulo con id " + rubroDto.getIdRubroPadre());
                }
            }else{
                entityDB.setRubroPadre(null);
            }

            entityDB.setDenominacion(rubroDto.getDenominacion());
            entityDB.setFechaModificacion(new Date());

            return rubroArticuloMapper.toCompleteDTO(rubroArticuloRepository.save(entityDB));
        }catch (Exception e) {
            e.printStackTrace();
            throw new ServicioException(e.getMessage());
        }
    }

    @Transactional
    public boolean delete(Long id) throws ServicioException {
        try {
            if (baseRepository.existsById(id)) {
                recursiveDelete(baseRepository.findById(id).get());
                return true;
            }else {
                throw new Exception("No existe la entidad");
            }
        }catch (Exception e) {
            throw new ServicioException(e.getMessage());
        }
    }

    public void recursiveDelete(RubroArticulo entity) {
        for (RubroArticulo child : entity.getSubRubros()) {
            recursiveDelete(child);
        }

        entity.setFechaBaja(new Date());
        baseRepository.save(entity);
    }

}

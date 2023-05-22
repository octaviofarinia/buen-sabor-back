package com.tup.buensabor.services;

import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloCompleteDto;
import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloDto;
import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloSimpleDto;
import com.tup.buensabor.entities.RubroArticulo;
import com.tup.buensabor.exceptions.ServicioException;
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
public class RubroArticuloServiceImpl extends BaseServiceImpl<RubroArticulo, Long> implements RubroArticuloService {

    @Autowired
    private RubroArticuloRepository rubroArticuloRepository;

    private RubroArticuloMapper rubroArticuloMapper = RubroArticuloMapper.getInstance();

    public RubroArticuloServiceImpl(BaseRepository<RubroArticulo, Long> baseRepository) {
        super(baseRepository);
    }

    @Transactional
    public RubroArticuloSimpleDto save(RubroArticuloSimpleDto rubroDto) throws Exception {
        if(rubroDto.getIdRubroPadre() != null) {
            if(baseRepository.existsById(rubroDto.getIdRubroPadre())) {
                RubroArticulo entity = new RubroArticulo(rubroDto.getDenominacion(), rubroArticuloRepository.findById(rubroDto.getIdRubroPadre()).get());
                entity.setFechaAlta(new Date());
                RubroArticulo entityPersisted = rubroArticuloRepository.save(entity);
                return rubroArticuloMapper.toSimpleDTO(entityPersisted);
            } else {
                throw new ServicioException("No existe un RubroArticulo con id " + rubroDto.getIdRubroPadre());
            }
        } else {
            RubroArticulo entity = new RubroArticulo(rubroDto.getDenominacion(), null);
            entity.setFechaAlta(new Date());
            return rubroArticuloMapper.toSimpleDTO(rubroArticuloRepository.save(entity));
        }
    }

    public RubroArticuloDto getOne(Long id) {
        RubroArticulo entity = rubroArticuloRepository.findById(id).get();
        RubroArticuloDto dto = rubroArticuloMapper.toRubroArticuloDTO(entity);
        return dto;
    }

    public RubroArticuloCompleteDto getOneComplete(Long id) {
        RubroArticulo entity = rubroArticuloRepository.findById(id).get();
        RubroArticuloCompleteDto dto = rubroArticuloMapper.toCompleteDTO(entity);
        return dto;
    }

    public List<RubroArticuloDto> getAllParents() {
        List<RubroArticuloDto> dtoList = new ArrayList<RubroArticuloDto>();
        List<RubroArticulo> parentsList = rubroArticuloRepository.getAllParents();

        for (RubroArticulo rubroEntity : parentsList) {
            dtoList.add(rubroArticuloMapper.toRubroArticuloDTO(rubroEntity));
        }

        return dtoList;
    }

    public List<RubroArticuloSimpleDto> getAllSimple() {
        return rubroArticuloMapper.toSimpleDTOList(rubroArticuloRepository.getAll());
    }

    @Transactional
    public RubroArticuloCompleteDto update(Long id, RubroArticuloCompleteDto rubroDto) throws ServicioException {
        try {
            if (rubroDto.getId() == null) {
                throw new ServicioException("La entidad a modificar debe contener un Id.");
            }

            Optional<RubroArticulo> entityOptional = baseRepository.findById(id);

            if(!entityOptional.isPresent()) {
                throw new ServicioException("No se encontro la entidad con el id dado.");
            }

            RubroArticulo entityDB = entityOptional.get();

            if(rubroDto.getIdRubroPadre() != null) {
                if(baseRepository.existsById(rubroDto.getIdRubroPadre())) {
                    entityDB.setRubroPadre(rubroArticuloRepository.findById(rubroDto.getIdRubroPadre()).get());
                } else {
                    throw new ServicioException("No existe un RubroArticulo con id " + rubroDto.getIdRubroPadre());
                }
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

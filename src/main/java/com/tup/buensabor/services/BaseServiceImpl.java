package com.tup.buensabor.services;

import com.tup.buensabor.dtos.BaseDto;
import com.tup.buensabor.entities.Base;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.services.interfaces.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public abstract class BaseServiceImpl<E extends Base, D extends BaseDto, ID extends Serializable> implements BaseService<E, D, ID> {
    protected BaseRepository<E, ID> baseRepository;

    protected BaseMapper<E, D> baseMapper;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository, BaseMapper<E, D> baseMapper) {
        this.baseRepository = baseRepository;
        this.baseMapper = baseMapper;
    }

    @Override
    @Transactional
    public List<D> findAll() throws ServicioException {
        try {
            List<E> entities = baseRepository.findAll();
            return baseMapper.toDTOsList(entities);
        }catch (Exception e) {
            e.printStackTrace();
            throw new ServicioException(e.getMessage());
        }
    }

    @Transactional
    public Optional<E> findOptionalById(ID id) throws ServicioException {
        try {
            return baseRepository.findById(id);
        }catch (Exception e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public D findById(ID id) throws ServicioException {
        try {
            return baseMapper.toDTO(baseRepository.findById(id).get());
        }catch (Exception e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws ServicioException {
        try {
            entity = baseRepository.save(entity);
            return entity;
        }catch (Exception e) {
            e.printStackTrace();
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws ServicioException {
        try {
            if (entity.getId() == null) {
                throw new ServicioException("La entidad a modificar debe contener un Id.");
            }

            Optional<E> entityOptional = baseRepository.findById(id);

            if(entityOptional.isEmpty()) {
                throw new ServicioException("No se encontro la entidad con el id dado.");
            }

            return baseRepository.save(entity);
        }catch (Exception e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean hardDelete(ID id) throws ServicioException {
        try {
            if (baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
                return true;
            }else {
                throw new Exception("No existe la entidad");
            }
        }catch (Exception e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<E> findAll(Pageable pageable) throws ServicioException {
        try {
            return baseRepository.findAll(pageable);
        }catch (Exception e) {
            throw new ServicioException(e.getMessage());
        }
    }
}

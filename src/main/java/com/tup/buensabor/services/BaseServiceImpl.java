package com.tup.buensabor.services;

import com.tup.buensabor.entities.Base;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.services.interfaces.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {
    protected BaseRepository<E, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public List<E> findAll() throws ServicioException {
        try {
            List<E> entities = baseRepository.findAll();
            return entities;
        }catch (Exception e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(ID id) throws ServicioException {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            return entityOptional.get();
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

            if(!entityOptional.isPresent()) {
                throw new ServicioException("No se encontro la entidad con el id dado.");
            }

            E entityDB = baseRepository.save(entity);
            return entityDB;
        }catch (Exception e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) throws ServicioException {
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
            Page<E> entities = baseRepository.findAll(pageable);
            return entities;
        }catch (Exception e) {
            throw new ServicioException(e.getMessage());
        }
    }
}

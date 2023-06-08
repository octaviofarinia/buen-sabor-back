package com.tup.buensabor.services.interfaces;

import com.tup.buensabor.dtos.BaseDto;
import com.tup.buensabor.entities.Base;
import com.tup.buensabor.exceptions.ServicioException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<E extends Base, D extends BaseDto, ID extends Serializable> {
    List<D> findAll() throws ServicioException;
    Page<E> findAll(Pageable pageable) throws ServicioException;
    D findById(ID id) throws ServicioException;
    Optional<E> findOptionalById(ID id) throws ServicioException;
    E save(E entity) throws ServicioException;
    E update(ID id, E entity) throws ServicioException;
    boolean hardDelete(ID id) throws ServicioException;
}
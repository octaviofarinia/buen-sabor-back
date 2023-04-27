package com.tup.buensabor.services.interfaces;

import com.tup.buensabor.entities.Base;
import com.tup.buensabor.exceptions.ServicioException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E extends Base, ID extends Serializable> {
    List<E> findAll() throws ServicioException;
    Page<E> findAll(Pageable pageable) throws ServicioException;
    E findById(ID id) throws ServicioException;
    E save(E entity) throws ServicioException;
    E update(ID id, E entity) throws ServicioException;
    boolean delete(ID id) throws ServicioException;
}
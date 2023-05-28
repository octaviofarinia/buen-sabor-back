package com.tup.buensabor.controllers.base;

import com.tup.buensabor.entities.Base;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;

public interface BaseController<E extends Base, ID extends Serializable> {
    ResponseEntity<?> getAll();
    ResponseEntity<?> getOne(@PathVariable ID id);
    ResponseEntity<?> getAll(Pageable pageable);
}

package com.tup.buensabor.repositories;

import com.tup.buensabor.entities.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long> {
}

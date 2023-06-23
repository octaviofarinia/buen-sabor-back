package com.tup.buensabor.repositories;

import com.tup.buensabor.entities.Domicilio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomicilioRepository  extends BaseRepository<Domicilio, Long> {

    @Query("SELECT d FROM Domicilio d WHERE d.cliente.usuario.auth0Id = :auth0Id")
    List<Domicilio> getByClienteAuth0Id(@Param("auth0Id") String auth0Id);

}

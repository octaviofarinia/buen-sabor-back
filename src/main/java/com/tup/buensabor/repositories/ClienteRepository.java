package com.tup.buensabor.repositories;

import com.tup.buensabor.entities.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.usuario.auth0Id = :auth0Id")
    Optional<Cliente> findByUsuarioAuth0Id(@Param("auth0Id") String auth0Id);

}

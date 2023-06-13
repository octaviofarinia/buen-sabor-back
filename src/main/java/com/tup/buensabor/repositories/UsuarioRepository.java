package com.tup.buensabor.repositories;

import com.tup.buensabor.entities.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long>{
    Optional<Usuario> findByAuth0IdentifierAndIdentityProvider(String auth0Identifier, String identityProvider);
}

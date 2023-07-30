package com.tup.buensabor.repositories;

import com.tup.buensabor.entities.UnidadMedida;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadMedidaRepository extends BaseRepository<UnidadMedida, Long> {

    @Query("SELECT count(ai) > 0 FROM ArticuloInsumo ai WHERE ai.unidadMedida.id = :idUnidadMedida")
    boolean isPresentInArticuloInsumo(Long idUnidadMedida);

    @Query("SELECT u FROM UnidadMedida u WHERE u.fechaBaja IS NULL")
    List<UnidadMedida> findAllActive();

}

package com.tup.buensabor.repositories;

import com.tup.buensabor.entities.UnidadMedida;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadMedidaRepository extends BaseRepository<UnidadMedida, Long> {

    @Query("SELECT count(ai) > 0 FROM ArticuloInsumo ai WHERE ai.unidadMedida.id = :idUnidadMedida")
    boolean isPresentInArticuloInsumo(Long idUnidadMedida);

}

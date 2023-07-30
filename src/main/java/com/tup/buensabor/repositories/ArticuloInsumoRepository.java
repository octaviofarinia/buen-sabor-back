package com.tup.buensabor.repositories;

import com.tup.buensabor.entities.ArticuloInsumo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloInsumoRepository extends BaseRepository<ArticuloInsumo, Long> {

    @Query("SELECT count(dm) > 0 FROM DetalleArticuloManufacturado dm WHERE dm.articuloInsumo.id = :idInsumo")
    boolean isPresentInArticuloManufacturado(Long idInsumo);

    @Query("SELECT a FROM ArticuloInsumo a WHERE a.fechaBaja IS NULL")
    List<ArticuloInsumo> findAllActive();

}

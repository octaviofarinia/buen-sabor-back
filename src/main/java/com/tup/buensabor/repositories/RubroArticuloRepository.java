package com.tup.buensabor.repositories;

import com.tup.buensabor.entities.RubroArticulo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RubroArticuloRepository extends BaseRepository<RubroArticulo, Long> {

    @Query("SELECT r FROM RubroArticulo r WHERE r.fechaBaja is null")
    List<RubroArticulo> getAll();

    @Query("SELECT r FROM RubroArticulo r WHERE r.fechaBaja is null order by r.rubroPadre.id")
    List<RubroArticulo> getAllParents();

}

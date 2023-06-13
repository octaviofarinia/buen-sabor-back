package com.tup.buensabor.repositories;

import com.tup.buensabor.entities.DetalleArticuloManufacturado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleArticuloManufacturadoRepository  extends BaseRepository<DetalleArticuloManufacturado, Long> {

    @Query("SELECT d FROM DetalleArticuloManufacturado d WHERE d.articuloManufacturado.id = :idArticuloManufacturado")
    List<DetalleArticuloManufacturado> getByIdArticuloManufacturado(@Param("idArticuloManufacturado") Long id);

}

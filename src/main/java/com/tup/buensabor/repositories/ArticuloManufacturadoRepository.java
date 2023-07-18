package com.tup.buensabor.repositories;

import com.tup.buensabor.entities.ArticuloManufacturado;
import com.tup.buensabor.entities.Pedido;
import com.tup.buensabor.enums.EstadoPedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloManufacturadoRepository extends BaseRepository<ArticuloManufacturado, Long> {

    @Query("SELECT a FROM ArticuloManufacturado a WHERE a.denominacion LIKE %:nombre%")
    List<ArticuloManufacturado> findAllByNombre(@Param("nombre") String nombre);

}

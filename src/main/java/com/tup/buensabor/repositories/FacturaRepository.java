package com.tup.buensabor.repositories;

import com.tup.buensabor.entities.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturaRepository extends BaseRepository<Factura, Long> {

    Optional<Factura> findByPedidoId(Long idPedido);

    boolean existsByPedidoId(Long idPedido);

}

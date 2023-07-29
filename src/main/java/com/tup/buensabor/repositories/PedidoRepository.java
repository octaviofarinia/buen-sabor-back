package com.tup.buensabor.repositories;

import com.tup.buensabor.entities.Pedido;
import com.tup.buensabor.enums.EstadoPedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p WHERE p.estado = :estado")
    List<Pedido> findAllByEstado(@Param("estado") EstadoPedido estado);

    @Query("SELECT p FROM Pedido p WHERE p.cliente.usuario.auth0Id = :auth0Id")
    List<Pedido> findAllByAuth0Id(@Param("auth0Id") String auth0Id);

}

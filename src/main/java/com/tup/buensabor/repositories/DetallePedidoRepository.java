package com.tup.buensabor.repositories;

import com.tup.buensabor.entities.DetallePedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends BaseRepository<DetallePedido, Long> {

    @Query("SELECT d FROM DetallePedido d WHERE d.pedido.id = :idPedido")
    List<DetallePedido> findAllByPedidoId(@Param("idPedido") Long id);

}

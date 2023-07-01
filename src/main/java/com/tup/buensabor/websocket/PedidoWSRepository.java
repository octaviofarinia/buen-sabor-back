package com.tup.buensabor.websocket;

import com.tup.buensabor.websocket.PedidoWS;
import com.tup.buensabor.repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoWSRepository extends BaseRepository<PedidoWS, Long> {
}

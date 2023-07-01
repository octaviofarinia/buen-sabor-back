package com.tup.buensabor.websocket;

import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.services.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoWSServiceImpl extends BaseServiceImpl<PedidoWS, PedidoWSDto, Long> implements PedidoWSService {

    @Autowired
    private PedidoWSRepository pedidoWSRepository;

    private final PedidoWSMapper pedidoWSMapper = PedidoWSMapper.getInstance();

    public PedidoWSServiceImpl(BaseRepository<PedidoWS, Long> baseRepository, BaseMapper<PedidoWS, PedidoWSDto> baseMapper) {
        super(baseRepository, baseMapper);
    }

}

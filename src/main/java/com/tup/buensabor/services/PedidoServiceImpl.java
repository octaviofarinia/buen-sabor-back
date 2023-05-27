package com.tup.buensabor.services;

import com.tup.buensabor.dtos.PedidoDto;
import com.tup.buensabor.entities.Pedido;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.PedidoMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.PedidoRepository;
import com.tup.buensabor.services.interfaces.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido, PedidoDto, Long> implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    private PedidoMapper pedidoMapper = PedidoMapper.getInstance();

    public PedidoServiceImpl(BaseRepository<Pedido, Long> baseRepository, BaseMapper<Pedido, PedidoDto> baseMapper) {
        super(baseRepository, baseMapper);
    }


}

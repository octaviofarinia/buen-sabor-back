package com.tup.buensabor.services;

import com.tup.buensabor.dtos.detallepedido.DetallePedidoDto;
import com.tup.buensabor.dtos.detallepedido.DetallePedidoSimpleDto;
import com.tup.buensabor.entities.DetallePedido;
import com.tup.buensabor.entities.Pedido;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.DetallePedidoMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.DetallePedidoRepository;
import com.tup.buensabor.services.interfaces.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoServiceImpl extends BaseServiceImpl<DetallePedido, DetallePedidoDto, Long> implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private DetallePedidoMapper detallePedidoMapper;

    public DetallePedidoServiceImpl(BaseRepository<DetallePedido, Long> baseRepository, BaseMapper<DetallePedido, DetallePedidoDto> baseMapper) {
        super(baseRepository, baseMapper);
    }

    public List<DetallePedidoSimpleDto> getByPedido(Pedido pedido) {
        return detallePedidoMapper.toSimpleDTOsList(detallePedidoRepository.findAllByPedidoId(pedido.getId()));
    }

}

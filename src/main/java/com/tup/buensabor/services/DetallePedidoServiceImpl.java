package com.tup.buensabor.services;

import com.tup.buensabor.entities.DetallePedido;
import com.tup.buensabor.mappers.DetallePedidoMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.DetallePedidoRepository;
import com.tup.buensabor.services.interfaces.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoServiceImpl extends BaseServiceImpl<DetallePedido, Long> implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    private DetallePedidoMapper detallePedidoMapper = DetallePedidoMapper.getInstance();

    public DetallePedidoServiceImpl(BaseRepository<DetallePedido, Long> baseRepository) {
        super(baseRepository);
    }


}

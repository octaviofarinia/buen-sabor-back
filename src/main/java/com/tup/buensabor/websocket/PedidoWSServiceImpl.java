package com.tup.buensabor.websocket;

import com.tup.buensabor.enums.EstadoPedido;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.services.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoWSServiceImpl extends BaseServiceImpl<PedidoWS, PedidoWSDto, Long> implements PedidoWSService {

    @Autowired
    private PedidoWSRepository pedidoWSRepository;

    private final PedidoWSMapper pedidoWSMapper = PedidoWSMapper.getInstance();

    public PedidoWSServiceImpl(BaseRepository<PedidoWS, Long> baseRepository, BaseMapper<PedidoWS, PedidoWSDto> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Transactional
    public PedidoWSDto save(PedidoWSDto pedidoWSDto) throws ServicioException {
        PedidoWS pedidoWS = pedidoWSMapper.toEntity(pedidoWSDto);
        pedidoWS.setFechaAlta(new Date());
        return pedidoWSMapper.toDTO(save(pedidoWS));
    }

    @Transactional
    public PedidoWSDto update(Long id, PedidoWSDto pedidoWSDto) throws ServicioException {
        Optional<PedidoWS> optionalPedidoWS = baseRepository.findById(id);
        if(optionalPedidoWS.isEmpty()) {
            throw new ServicioException("No se encontro el peidido con el id dado.");
        }

        PedidoWS pedidoWS = optionalPedidoWS.get();
        pedidoWS.setTotal(pedidoWSDto.getTotal());
        pedidoWS.setEstado(pedidoWSDto.getEstado());
        pedidoWS.setFechaModificacion(new Date());

        return pedidoWSMapper.toDTO(baseRepository.save(pedidoWS));
    }

    @Transactional
    public void updateEstado(Long id, EstadoPedido newEstado) throws ServicioException {
        Optional<PedidoWS> optionalPedidoWS = baseRepository.findById(id);
        if(optionalPedidoWS.isEmpty()) {
            throw new ServicioException("No se encontro el peidido con el id dado.");
        }

        if(newEstado == null) {
            throw new ServicioException("El estado nuevo no puede ser nulo");
        }

        PedidoWS pedidoWS = optionalPedidoWS.get();
        pedidoWS.setEstado(newEstado);
        pedidoWS.setFechaModificacion(new Date());
        baseRepository.save(pedidoWS);
    }

}

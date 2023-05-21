package com.tup.buensabor.services;

import com.tup.buensabor.dtos.UnidadMedidaDto;
import com.tup.buensabor.entities.ArticuloManufacturado;
import com.tup.buensabor.entities.UnidadMedida;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.UnidadMedidaMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.UnidadMedidaRepository;
import com.tup.buensabor.services.interfaces.UnidadMedidaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UnidadMedidaServiceImpl extends BaseServiceImpl<UnidadMedida, Long> implements UnidadMedidaService {

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    private UnidadMedidaMapper unidadMedidaMapper = UnidadMedidaMapper.getInstance();

    public UnidadMedidaServiceImpl(BaseRepository<UnidadMedida, Long> baseRepository) {
        super(baseRepository);
    }

    @Transactional
    public UnidadMedida save(UnidadMedidaDto unidadMedidaDto) throws ServicioException {
        UnidadMedida unidadMedida = unidadMedidaMapper.toEntity(unidadMedidaDto);
        unidadMedida.setFechaAlta(new Date());
        return save(unidadMedida);
    }

    @Transactional
    public UnidadMedida update(Long id, UnidadMedidaDto unidadMedidaDto) throws ServicioException {
        Optional<UnidadMedida> optionalUnidadMedida = baseRepository.findById(id);
        if(optionalUnidadMedida.isEmpty()) {
            throw new ServicioException("No se encontro la unidad de medida con el id dado.");
        }

        UnidadMedida unidadMedida = optionalUnidadMedida.get();
        unidadMedida.setDenominacion(unidadMedidaDto.getDenominacion());
        unidadMedida.setAbreviatura(unidadMedidaDto.getAbreviatura());
        unidadMedida.setFechaModificacion(new Date());

        return baseRepository.save(unidadMedida);
    }

    @Transactional
    public void softDelete(Long id) throws ServicioException {
        Optional<UnidadMedida> optionalUnidadMedida = unidadMedidaRepository.findById(id);
        if(optionalUnidadMedida.isEmpty()) {
            throw new ServicioException("No existe una unidad de medida con el id seleccionado.");
        }

        UnidadMedida unidadMedida = optionalUnidadMedida.get();
        unidadMedida.setFechaBaja(new Date());

        unidadMedidaRepository.save(unidadMedida);
    }

}

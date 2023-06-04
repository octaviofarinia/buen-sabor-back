package com.tup.buensabor.services;

import com.tup.buensabor.dtos.detallearticulomanufacturado.DetalleArticuloManufacturadoDto;
import com.tup.buensabor.dtos.detallearticulomanufacturado.DetalleArticuloManufacturadoSimpleDto;
import com.tup.buensabor.entities.ArticuloInsumo;
import com.tup.buensabor.entities.ArticuloManufacturado;
import com.tup.buensabor.entities.DetalleArticuloManufacturado;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.DetalleArticuloManufacturadoMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.DetalleArticuloManufacturadoRepository;
import com.tup.buensabor.services.interfaces.DetalleArticuloManufacturadoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DetalleArticuloManufacturadoServiceImpl extends BaseServiceImpl<DetalleArticuloManufacturado, DetalleArticuloManufacturadoDto, Long> implements DetalleArticuloManufacturadoService {

    @Autowired
    private DetalleArticuloManufacturadoRepository detalleArticuloManufacturadoRepository;

    @Autowired
    private ArticuloInsumoServiceImpl articuloInsumoService;

    @Autowired
    private UnidadMedidaServiceImpl unidadMedidaService;

    @Autowired
    private ArticuloManufacturadoServiceImpl articuloManufacturadoService;

    private final DetalleArticuloManufacturadoMapper detalleArticuloManufacturadoMapper = DetalleArticuloManufacturadoMapper.getInstance();

    public DetalleArticuloManufacturadoServiceImpl(BaseRepository<DetalleArticuloManufacturado, Long> baseRepository, BaseMapper<DetalleArticuloManufacturado, DetalleArticuloManufacturadoDto> baseMapper) {
        super(baseRepository, baseMapper);
    }

    public List<DetalleArticuloManufacturadoDto> getByIdArticuloManufacturado(Long id) throws ServicioException {
        Optional<ArticuloManufacturado> optionalArticuloManufacturado = articuloManufacturadoService.findOptionalById(id);
        if(optionalArticuloManufacturado.isEmpty()) {
            throw new ServicioException("No existe un producto con el id seleccionado.");
        }

        return detalleArticuloManufacturadoMapper.toDTOsList(detalleArticuloManufacturadoRepository.getByIdArticuloManufacturado(id));
    }

    @Transactional
    public DetalleArticuloManufacturadoDto save(DetalleArticuloManufacturadoSimpleDto detalleArticuloManufacturadoDto) throws IOException, ServicioException {
        Optional<ArticuloInsumo> optionalArticuloInsumo = articuloInsumoService.findOptionalById(detalleArticuloManufacturadoDto.getIdArticuloInsumo());
        if(optionalArticuloInsumo.isEmpty()) {
            throw new ServicioException("No se encontro el articulo seleccionado.");
        }

        Optional<ArticuloManufacturado> optionalArticuloManufacturado = articuloManufacturadoService.findOptionalById(detalleArticuloManufacturadoDto.getIdArticuloManufacturado());
        if(optionalArticuloManufacturado.isEmpty()) {
            throw new ServicioException("No se encontro el articulo manufacturado seleccionado.");
        }

        DetalleArticuloManufacturado detalleArticuloManufacturado = detalleArticuloManufacturadoMapper.toEntity(detalleArticuloManufacturadoDto);

        detalleArticuloManufacturado.setArticuloInsumo(optionalArticuloInsumo.get());
        detalleArticuloManufacturado.setArticuloManufacturado(optionalArticuloManufacturado.get());

        return detalleArticuloManufacturadoMapper.toDTO(this.save(detalleArticuloManufacturado));
    }

    @Transactional
    public DetalleArticuloManufacturadoDto update(DetalleArticuloManufacturadoSimpleDto detalleArticuloManufacturadoDto) throws IOException, ServicioException {
        if (detalleArticuloManufacturadoDto.getId() == null || detalleArticuloManufacturadoDto.getId() <= 0) {
            throw new ServicioException("El campo id es obligatorio y mayor a cero.");
        }

        Optional<DetalleArticuloManufacturado> optionalDetalleArticuloManufacturado = detalleArticuloManufacturadoRepository.findById(detalleArticuloManufacturadoDto.getId());
        if(optionalDetalleArticuloManufacturado.isEmpty()) {
            throw new ServicioException("No existe un detalle de articulo manufacturado con el id seleccionado.");
        }

        Optional<ArticuloInsumo> optionalArticuloInsumo = articuloInsumoService.findOptionalById(detalleArticuloManufacturadoDto.getIdArticuloInsumo());
        if(optionalArticuloInsumo.isEmpty()) {
            throw new ServicioException("No se encontro el articulo seleccionado.");
        }

        Optional<ArticuloManufacturado> optionalArticuloManufacturado = articuloManufacturadoService.findOptionalById(detalleArticuloManufacturadoDto.getIdArticuloManufacturado());
        if(optionalArticuloManufacturado.isEmpty()) {
            throw new ServicioException("No se encontro el articulo manufacturado seleccionado.");
        }

        DetalleArticuloManufacturado detalleArticuloManufacturado = optionalDetalleArticuloManufacturado.get();

        detalleArticuloManufacturado.setCantidad(detalleArticuloManufacturadoDto.getCantidad());
        detalleArticuloManufacturado.setArticuloInsumo(optionalArticuloInsumo.get());
        detalleArticuloManufacturado.setArticuloManufacturado(optionalArticuloManufacturado.get());

        return detalleArticuloManufacturadoMapper.toDTO(this.save(detalleArticuloManufacturado));
    }

}

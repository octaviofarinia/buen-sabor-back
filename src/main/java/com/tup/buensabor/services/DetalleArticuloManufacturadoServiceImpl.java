package com.tup.buensabor.services;

import com.tup.buensabor.dtos.detallearticulomanufacturado.DetalleArticuloManufacturadoCompleteDto;
import com.tup.buensabor.dtos.detallearticulomanufacturado.DetalleArticuloManufacturadoDto;
import com.tup.buensabor.dtos.detallearticulomanufacturado.DetalleArticuloManufacturadoSimpleDto;
import com.tup.buensabor.entities.ArticuloInsumo;
import com.tup.buensabor.entities.ArticuloManufacturado;
import com.tup.buensabor.entities.DetalleArticuloManufacturado;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.mappers.DetalleArticuloManufacturadoMapper;
import com.tup.buensabor.mappers.UnidadMedidaMapper;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.DetalleArticuloManufacturadoRepository;
import com.tup.buensabor.services.interfaces.DetalleArticuloManufacturadoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DetalleArticuloManufacturadoServiceImpl extends BaseServiceImpl<DetalleArticuloManufacturado, DetalleArticuloManufacturadoDto, Long> implements DetalleArticuloManufacturadoService {

    @Autowired
    private DetalleArticuloManufacturadoRepository detalleArticuloManufacturadoRepository;

    @Autowired
    private ArticuloInsumoServiceImpl articuloInsumoService;

    @Autowired
    private UnidadMedidaMapper unidadMedidaMapper;

    @Autowired
    private ArticuloManufacturadoServiceImpl articuloManufacturadoService;

    @Autowired
    private DetalleArticuloManufacturadoMapper detalleArticuloManufacturadoMapper;

    public DetalleArticuloManufacturadoServiceImpl(BaseRepository<DetalleArticuloManufacturado, Long> baseRepository, BaseMapper<DetalleArticuloManufacturado, DetalleArticuloManufacturadoDto> baseMapper) {
        super(baseRepository, baseMapper);
    }

    public List<DetalleArticuloManufacturadoCompleteDto> getByIdArticuloManufacturado(Long id) throws ServicioException {
        Optional<ArticuloManufacturado> optionalArticuloManufacturado = articuloManufacturadoService.findOptionalById(id);
        if(optionalArticuloManufacturado.isEmpty()) {
            throw new ServicioException("No existe un producto con el id seleccionado.");
        }

        List<DetalleArticuloManufacturadoCompleteDto> detallesDto = new ArrayList<>();
        List<DetalleArticuloManufacturado> detallesEntity = detalleArticuloManufacturadoRepository.getByIdArticuloManufacturado(id);
        for (DetalleArticuloManufacturado detalleEntity : detallesEntity) {
            DetalleArticuloManufacturadoCompleteDto dto = detalleArticuloManufacturadoMapper.toCompleteDTO(detalleEntity);
            dto.setUnidadMedida(unidadMedidaMapper.toDTO(detalleEntity.getArticuloInsumo().getUnidadMedida()));
            detallesDto.add(dto);
        }

        return detallesDto;
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

        ArticuloInsumo articuloInsumo = optionalArticuloInsumo.get();
        ArticuloManufacturado articuloManufacturado = optionalArticuloManufacturado.get();

        BigDecimal costo = Optional.ofNullable(articuloManufacturado.getCosto()).orElse(BigDecimal.ZERO);
        BigDecimal cantidad = Optional.ofNullable(detalleArticuloManufacturado.getCantidad()).orElse(BigDecimal.ZERO);
        BigDecimal precioCompra = Optional.ofNullable(articuloInsumo.getPrecioCompra()).orElse(BigDecimal.ZERO);

        if (!Objects.isNull(costo) && !Objects.isNull(cantidad) && !Objects.isNull(precioCompra)) {
            articuloManufacturado.setCosto(costo.add(cantidad.multiply(precioCompra)));
        }

        detalleArticuloManufacturado.setArticuloInsumo(articuloInsumo);
        detalleArticuloManufacturado.setArticuloManufacturado(articuloManufacturado);

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

        BigDecimal oldQuantity = Optional.ofNullable(detalleArticuloManufacturado.getCantidad()).orElse(BigDecimal.ZERO);

        detalleArticuloManufacturado.setCantidad(detalleArticuloManufacturadoDto.getCantidad());

        ArticuloInsumo articuloInsumo = optionalArticuloInsumo.get();
        ArticuloManufacturado articuloManufacturado = optionalArticuloManufacturado.get();

        BigDecimal costo = Optional.ofNullable(articuloManufacturado.getCosto()).orElse(BigDecimal.ZERO);
        BigDecimal newQuantity = Optional.ofNullable(detalleArticuloManufacturado.getCantidad()).orElse(BigDecimal.ZERO);
        BigDecimal precioCompra = Optional.ofNullable(articuloInsumo.getPrecioCompra()).orElse(BigDecimal.ZERO);

        if (!Objects.isNull(costo) && !Objects.isNull(oldQuantity) && !Objects.isNull(newQuantity) && !Objects.isNull(precioCompra)) {
            articuloManufacturado.setCosto(costo.subtract(oldQuantity.multiply(precioCompra)).add(newQuantity.multiply(precioCompra)));
        }

        detalleArticuloManufacturado.setArticuloInsumo(articuloInsumo);
        detalleArticuloManufacturado.setArticuloManufacturado(articuloManufacturado);

        return detalleArticuloManufacturadoMapper.toDTO(this.save(detalleArticuloManufacturado));
    }

    @Override
    @Transactional
    public boolean hardDelete(Long id) throws ServicioException {
        try {
            if (detalleArticuloManufacturadoRepository.existsById(id)) {
                DetalleArticuloManufacturado detalleArticuloManufacturado = detalleArticuloManufacturadoRepository.findById(id).get();

                Optional<ArticuloInsumo> optionalArticuloInsumo = articuloInsumoService.findOptionalById(detalleArticuloManufacturado.getArticuloInsumo().getId());
                if(optionalArticuloInsumo.isEmpty()) {
                    throw new ServicioException("No se encontro un articulo para el detalle a eliminar.");
                }

                Optional<ArticuloManufacturado> optionalArticuloManufacturado = articuloManufacturadoService.findOptionalById(detalleArticuloManufacturado.getArticuloManufacturado().getId());
                if(optionalArticuloManufacturado.isEmpty()) {
                    throw new ServicioException("No se encontro un articulo manufacturado para el detalle a eliminar.");
                }

                ArticuloInsumo articuloInsumo = optionalArticuloInsumo.get();
                ArticuloManufacturado articuloManufacturado = optionalArticuloManufacturado.get();

                BigDecimal costo = Optional.ofNullable(articuloManufacturado.getCosto()).orElse(BigDecimal.ZERO);
                BigDecimal cantidad = Optional.ofNullable(detalleArticuloManufacturado.getCantidad()).orElse(BigDecimal.ZERO);
                BigDecimal precioCompra = Optional.ofNullable(articuloInsumo.getPrecioCompra()).orElse(BigDecimal.ZERO);

                if (!Objects.isNull(costo) && !Objects.isNull(cantidad) && !Objects.isNull(precioCompra)) {
                    articuloManufacturado.setCosto(costo.subtract(cantidad.multiply(precioCompra)));
                }

                detalleArticuloManufacturadoRepository.deleteById(id);
                return true;
            }else {
                throw new Exception("No existe la entidad");
            }
        }catch (Exception e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Transactional
    public void hardDeleteAllByIdArticuloManufacturado(Long idArticuloManufacturado) {
        detalleArticuloManufacturadoRepository.deleteAll(detalleArticuloManufacturadoRepository.getByIdArticuloManufacturado(idArticuloManufacturado));
    }

}

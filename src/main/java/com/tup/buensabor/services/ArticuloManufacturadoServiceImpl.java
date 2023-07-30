package com.tup.buensabor.services;

import com.tup.buensabor.dtos.articulomanufacturado.ArticuloManufacturadoDto;
import com.tup.buensabor.dtos.ranking.ArticuloManufacturadoRankingDto;
import com.tup.buensabor.entities.ArticuloManufacturado;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.ArticuloManufacturadoMapper;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.repositories.ArticuloManufacturadoRepository;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.DetalleArticuloManufacturadoRepository;
import com.tup.buensabor.services.interfaces.ArticuloManufacturadoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ArticuloManufacturadoServiceImpl extends BaseServiceImpl<ArticuloManufacturado, ArticuloManufacturadoDto, Long> implements ArticuloManufacturadoService {

    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;

    @Autowired
    private DetalleArticuloManufacturadoRepository detalleArticuloManufacturadoRepository;

    @Autowired
    private ImagenService imagenService;

    private static final String CLOUDINARY_FOLDER = "productos";

    @Autowired
    private ArticuloManufacturadoMapper articuloManufacturadoMapper;

    public ArticuloManufacturadoServiceImpl(BaseRepository<ArticuloManufacturado, Long> baseRepository, BaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto> baseMapper) {
        super(baseRepository, baseMapper);
    }

    public List<ArticuloManufacturadoDto> findAllActive(String nombre) throws ServicioException {
        try {
            List<ArticuloManufacturado> entities = articuloManufacturadoRepository.findAllActiveByNombre(nombre);
            return baseMapper.toDTOsList(entities);
        }catch (Exception e) {
            e.printStackTrace();
            throw new ServicioException(e.getMessage());
        }
    }

    @Transactional
    public ArticuloManufacturadoDto save(ArticuloManufacturadoDto articuloManufacturadoDto, MultipartFile imagen) throws IOException, ServicioException {
        if (imagen.isEmpty()) {
            throw new ServicioException("Debe seleccionar una imagen para el producto");
        }

        ArticuloManufacturado articuloManufacturado = articuloManufacturadoMapper.toEntity(articuloManufacturadoDto);
        articuloManufacturado.setFechaAlta(new Date());

        articuloManufacturado = this.save(articuloManufacturado);

        Map<String, Object> uploadData = imagenService.uploadImage(imagen, articuloManufacturado.getId(), CLOUDINARY_FOLDER);
        articuloManufacturado.setUrlImagen((String) uploadData.get("url"));

        articuloManufacturado = this.save(articuloManufacturado);

        return articuloManufacturadoMapper.toDTO(articuloManufacturado);
    }

    @Transactional
    public ArticuloManufacturadoDto update(ArticuloManufacturadoDto articuloManufacturadoDto, MultipartFile imagen) throws IOException, ServicioException {
        if (articuloManufacturadoDto.getId() == null || articuloManufacturadoDto.getId() <= 0) {
            throw new ServicioException("El campo id es obligatorio y mayor a cero.");
        }

        Optional<ArticuloManufacturado> optionalArticuloManufacturado = articuloManufacturadoRepository.findById(articuloManufacturadoDto.getId());
        if(optionalArticuloManufacturado.isEmpty()) {
            throw new ServicioException("No existe un producto con el id seleccionado.");
        }

        ArticuloManufacturado articuloManufacturado = articuloManufacturadoMapper.toEntity(articuloManufacturadoDto);
        articuloManufacturado.setFechaAlta(optionalArticuloManufacturado.get().getFechaAlta());
        articuloManufacturado.setFechaModificacion(new Date());
        articuloManufacturado = this.save(articuloManufacturado);

        if(imagen != null && !imagen.isEmpty()) {
            Map<String, Object> uploadData = imagenService.uploadImage(imagen, articuloManufacturado.getId(), CLOUDINARY_FOLDER);
            articuloManufacturado.setUrlImagen((String) uploadData.get("url"));

            articuloManufacturado = this.save(articuloManufacturado);
        }

        return articuloManufacturadoMapper.toDTO(articuloManufacturado);
    }

    @Transactional
    public void softDelete(Long id) throws ServicioException {
        Optional<ArticuloManufacturado> optionalArticuloManufacturado = articuloManufacturadoRepository.findById(id);
        if(optionalArticuloManufacturado.isEmpty()) {
            throw new ServicioException("No existe un producto con el id seleccionado.");
        }

        ArticuloManufacturado articuloManufacturado = optionalArticuloManufacturado.get();
        articuloManufacturado.setFechaBaja(new Date());

        articuloManufacturadoRepository.save(articuloManufacturado);
    }

    @Transactional(rollbackOn = ServicioException.class)
    public void hardDeleteImage(Long id) throws IOException, ServicioException {
        Optional<ArticuloManufacturado> optionalArticuloManufacturado = articuloManufacturadoRepository.findById(id);
        if(optionalArticuloManufacturado.isEmpty()) {
            throw new ServicioException("No existe un producto con el id seleccionado.");
        }else if(articuloManufacturadoRepository.isPresentInPedido(id)) {
            throw new ServicioException("No se puede hacer un hard delete del producto ya que este pertenece a pedidos ya procesados.");
        }

        detalleArticuloManufacturadoRepository.deleteAll(detalleArticuloManufacturadoRepository.getByIdArticuloManufacturado(id));

        imagenService.deleteImage(id, CLOUDINARY_FOLDER);
        this.hardDelete(id);
    }

    public List<ArticuloManufacturadoRankingDto> ranking(Date desde, Date hasta) throws ServicioException {
        try {
            if (desde == null) {
                desde = new Date(Long.MIN_VALUE);
            }
            if (hasta == null) {
                hasta = new Date();
            }

            LocalDateTime startOfDay = desde.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .atStartOfDay();

            LocalDateTime endOfDay = hasta.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .atTime(23, 59, 59);

            List<ArticuloManufacturadoRankingDto> lista = articuloManufacturadoRepository.ranking(
                    Date.from(startOfDay.toInstant(ZoneOffset.UTC)),
                    Date.from(endOfDay.toInstant(ZoneOffset.UTC)));

            return lista;
        }catch (Exception e) {
            e.printStackTrace();
            throw new ServicioException(e.getMessage());
        }
    }

}

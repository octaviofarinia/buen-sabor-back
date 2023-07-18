package com.tup.buensabor.services;

import com.tup.buensabor.dtos.articulomanufacturado.ArticuloManufacturadoDto;
import com.tup.buensabor.dtos.detallearticulomanufacturado.DetalleArticuloManufacturadoDto;
import com.tup.buensabor.entities.ArticuloManufacturado;
import com.tup.buensabor.entities.DetalleArticuloManufacturado;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.ArticuloManufacturadoMapper;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.repositories.ArticuloManufacturadoRepository;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.services.interfaces.ArticuloManufacturadoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ArticuloManufacturadoServiceImpl extends BaseServiceImpl<ArticuloManufacturado, ArticuloManufacturadoDto, Long> implements ArticuloManufacturadoService {

    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;

    @Autowired
    private ImagenService imagenService;

    private static final String CLOUDINARY_FOLDER = "productos";

    @Autowired
    private ArticuloManufacturadoMapper articuloManufacturadoMapper;

    public ArticuloManufacturadoServiceImpl(BaseRepository<ArticuloManufacturado, Long> baseRepository, BaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Transactional
    public List<ArticuloManufacturadoDto> findAll(String nombre) throws ServicioException {
        try {
            List<ArticuloManufacturado> entities = articuloManufacturadoRepository.findAllByNombre(nombre);
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

        ArticuloManufacturado articuloManufacturado = optionalArticuloManufacturado.get();
        articuloManufacturado.setDenominacion(articuloManufacturadoDto.getDenominacion());
        articuloManufacturado.setDescripcion(articuloManufacturadoDto.getDescripcion());
        articuloManufacturado.setTiempoEstimadoCocina(articuloManufacturadoDto.getTiempoEstimadoCocina());
        articuloManufacturado.setPrecioVenta(articuloManufacturadoDto.getPrecioVenta());
        articuloManufacturado.setFechaModificacion(new Date());
        articuloManufacturado = this.save(articuloManufacturado);

        if(imagen != null && !imagen.isEmpty()) {
            Map<String, Object> uploadData = imagenService.uploadImage(imagen, articuloManufacturado.getId(), CLOUDINARY_FOLDER);
            articuloManufacturado.setUrlImagen((String) uploadData.get("url"));

            articuloManufacturado = this.save(articuloManufacturado);
        }

        return articuloManufacturadoMapper.toDTO(articuloManufacturado);
    }

    public void softDelete(Long id) throws ServicioException {
        Optional<ArticuloManufacturado> optionalArticuloManufacturado = articuloManufacturadoRepository.findById(id);
        if(optionalArticuloManufacturado.isEmpty()) {
            throw new ServicioException("No existe un producto con el id seleccionado.");
        }

        ArticuloManufacturado articuloManufacturado = optionalArticuloManufacturado.get();
        articuloManufacturado.setFechaBaja(new Date());

        articuloManufacturadoRepository.save(articuloManufacturado);
    }

    @Transactional
    public void hardDeleteImage(Long id) throws IOException, ServicioException {
        Optional<ArticuloManufacturado> optionalArticuloManufacturado = articuloManufacturadoRepository.findById(id);
        if(optionalArticuloManufacturado.isEmpty()) {
            throw new ServicioException("No existe un producto con el id seleccionado.");
        }

        imagenService.deleteImage(id, CLOUDINARY_FOLDER);
        this.hardDelete(id);
    }
}

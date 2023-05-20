package com.tup.buensabor.services;

import com.tup.buensabor.dtos.ArticuloManufacturadoDto;
import com.tup.buensabor.entities.ArticuloManufacturado;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.ArticuloManufacturadoMapper;
import com.tup.buensabor.repositories.ArticuloManufacturadoRepository;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.services.interfaces.ArticuloManufacturadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Service
public class ArticuloManufacturadoServiceImpl extends BaseServiceImpl<ArticuloManufacturado, Long> implements ArticuloManufacturadoService {

    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;

    @Autowired
    private ImagenService imagenService;

    private ArticuloManufacturadoMapper articuloManufacturadoMapper = ArticuloManufacturadoMapper.getInstance();

    public ArticuloManufacturadoServiceImpl(BaseRepository<ArticuloManufacturado, Long> baseRepository) {
        super(baseRepository);
    }

    public ArticuloManufacturado save(ArticuloManufacturadoDto articuloManufacturadoDto, MultipartFile imagen) throws IOException, ServicioException {
        if (imagen.isEmpty()) {
            throw new ServicioException("Debe seleccionar una imagen para el producto");
        }

        Map<String, Object> uploadData = imagenService.uploadImage(imagen);
        articuloManufacturadoDto.setUrlImagen((String) uploadData.get("url"));

        ArticuloManufacturado articuloManufacturado = articuloManufacturadoMapper.toEntity(articuloManufacturadoDto);
        articuloManufacturado.setFechaAlta(new Date());

        return this.save(articuloManufacturado);
    }

    public ArticuloManufacturado update(ArticuloManufacturadoDto articuloManufacturadoDto, MultipartFile imagen) throws IOException, ServicioException {
        if (imagen.isEmpty()) {
            throw new ServicioException("Debe seleccionar una imagen para el producto");
        }

        if (articuloManufacturadoDto.getId() == null || articuloManufacturadoDto.getId() <= 0) {
            throw new ServicioException("El campo id es obligatorio y mayor a cero.");
        }

        Map<String, Object> uploadData = imagenService.uploadImage(imagen);
        articuloManufacturadoDto.setUrlImagen((String) uploadData.get("url"));

        ArticuloManufacturado articuloManufacturado = articuloManufacturadoMapper.toEntity(articuloManufacturadoDto);
        articuloManufacturado.setFechaAlta(new Date());

        return this.save(articuloManufacturado);
    }

}

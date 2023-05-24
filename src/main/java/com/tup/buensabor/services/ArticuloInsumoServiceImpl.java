package com.tup.buensabor.services;

import com.tup.buensabor.dtos.ArticuloInsumoDto;
import com.tup.buensabor.entities.ArticuloInsumo;
import com.tup.buensabor.entities.ArticuloInsumo;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.ArticuloInsumoMapper;
import com.tup.buensabor.repositories.ArticuloInsumoRepository;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.services.interfaces.ArticuloInsumoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class ArticuloInsumoServiceImpl extends BaseServiceImpl<ArticuloInsumo, Long> implements ArticuloInsumoService {

    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;

    @Autowired
    private ImagenService imagenService;

    private final String CLOUDINARY_FOLDER = "insumos";

    private final ArticuloInsumoMapper articuloInsumoMapper = ArticuloInsumoMapper.getInstance();

    public ArticuloInsumoServiceImpl(BaseRepository<ArticuloInsumo, Long> baseRepository) {
        super(baseRepository);
    }

    @Transactional
    public ArticuloInsumo save(ArticuloInsumoDto articuloInsumoDto, MultipartFile imagen) throws IOException, ServicioException {
        if (imagen.isEmpty()) {
            throw new ServicioException("Debe seleccionar una imagen para el insumo");
        }

        ArticuloInsumo articuloInsumo = articuloInsumoMapper.toEntity(articuloInsumoDto);
        articuloInsumo.setFechaAlta(new Date());

        articuloInsumo = this.save(articuloInsumo);

        Map<String, Object> uploadData = imagenService.uploadImage(imagen, articuloInsumo.getId(), CLOUDINARY_FOLDER);
        articuloInsumo.setUrlImagen((String) uploadData.get("url"));

        articuloInsumo = this.save(articuloInsumo);

        return articuloInsumo;
    }

    @Transactional
    public ArticuloInsumo update(ArticuloInsumoDto articuloInsumoDto, MultipartFile imagen) throws IOException, ServicioException {
        if (imagen.isEmpty()) {
            throw new ServicioException("Debe seleccionar una imagen para el insumo.");
        }

        if (articuloInsumoDto.getId() == null || articuloInsumoDto.getId() <= 0) {
            throw new ServicioException("El campo id es obligatorio y mayor a cero.");
        }

        Optional<ArticuloInsumo> optionalArticuloInsumo = articuloInsumoRepository.findById(articuloInsumoDto.getId());
        if(optionalArticuloInsumo.isEmpty()) {
            throw new ServicioException("No existe un insumo con el id seleccionado.");
        }

        ArticuloInsumo articuloInsumo = optionalArticuloInsumo.get();
        articuloInsumo.setDenominacion(articuloInsumoDto.getDenominacion());
        articuloInsumo.setFechaModificacion(new Date());
        articuloInsumo = this.save(articuloInsumo);

        Map<String, Object> uploadData = imagenService.uploadImage(imagen, articuloInsumo.getId(), CLOUDINARY_FOLDER);
        articuloInsumo.setUrlImagen((String) uploadData.get("url"));

        articuloInsumo = this.save(articuloInsumo);

        return articuloInsumo;
    }

    public void softDelete(Long id) throws ServicioException {
        Optional<ArticuloInsumo> optionalArticuloInsumo = articuloInsumoRepository.findById(id);
        if(optionalArticuloInsumo.isEmpty()) {
            throw new ServicioException("No existe un insumo con el id seleccionado.");
        }

        ArticuloInsumo articuloInsumo = optionalArticuloInsumo.get();
        articuloInsumo.setFechaBaja(new Date());

        articuloInsumoRepository.save(articuloInsumo);
    }

    @Transactional
    public void hardDeleteImage(Long id) throws IOException, ServicioException {
        imagenService.deleteImage(id, CLOUDINARY_FOLDER);
        this.hardDelete(id);
    }

}

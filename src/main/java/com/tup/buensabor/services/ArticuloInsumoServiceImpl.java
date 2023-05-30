package com.tup.buensabor.services;

import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoCompleteDto;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoDto;
import com.tup.buensabor.entities.ArticuloInsumo;
import com.tup.buensabor.entities.RubroArticulo;
import com.tup.buensabor.entities.UnidadMedida;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.ArticuloInsumoMapper;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.repositories.ArticuloInsumoRepository;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.services.interfaces.ArticuloInsumoService;
import com.tup.buensabor.services.interfaces.RubroArticuloService;
import com.tup.buensabor.services.interfaces.UnidadMedidaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class ArticuloInsumoServiceImpl extends BaseServiceImpl<ArticuloInsumo, ArticuloInsumoCompleteDto, Long> implements ArticuloInsumoService {

    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;

    @Autowired
    private ImagenService imagenService;

    @Autowired
    private RubroArticuloService rubroArticuloService;

    @Autowired
    private UnidadMedidaService unidadMedidaService;

    private final String CLOUDINARY_FOLDER = "insumos";

    private final ArticuloInsumoMapper articuloInsumoMapper = ArticuloInsumoMapper.getInstance();

    public ArticuloInsumoServiceImpl(BaseRepository<ArticuloInsumo, Long> baseRepository, BaseMapper<ArticuloInsumo, ArticuloInsumoCompleteDto> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Transactional
    public ArticuloInsumoCompleteDto save(ArticuloInsumoDto articuloInsumoDto, MultipartFile imagen) throws IOException, ServicioException {
        if (imagen.isEmpty()) {
            throw new ServicioException("Debe seleccionar una imagen para el insumo");
        }

        Optional<RubroArticulo> optionalRubroArticulo = rubroArticuloService.findOptionalById(articuloInsumoDto.getIdRubroArticulo());
        if(optionalRubroArticulo.isEmpty()) {
            throw new ServicioException("No se encontro la categoria seleccionada.");
        }

        Optional<UnidadMedida> optionalUnidadMedida = unidadMedidaService.findOptionalById(articuloInsumoDto.getIdUnidadMedida());
        if(optionalUnidadMedida.isEmpty()) {
            throw new ServicioException("No se encontro la unidad de medida seleccionada.");
        }

        ArticuloInsumo articuloInsumo = articuloInsumoMapper.toEntity(articuloInsumoDto);
        articuloInsumo.setFechaAlta(new Date());

        articuloInsumo.setRubroArticulo(optionalRubroArticulo.get());
        articuloInsumo.setUnidadMedida(optionalUnidadMedida.get());

        articuloInsumo = this.save(articuloInsumo);

        Map<String, Object> uploadData = imagenService.uploadImage(imagen, articuloInsumo.getId(), CLOUDINARY_FOLDER);
        articuloInsumo.setUrlImagen((String) uploadData.get("url"));

        articuloInsumo = this.save(articuloInsumo);

        return articuloInsumoMapper.toDTO(articuloInsumo);
    }

    @Transactional
    public ArticuloInsumoCompleteDto update(ArticuloInsumoDto articuloInsumoDto, MultipartFile imagen) throws IOException, ServicioException {
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

        Optional<RubroArticulo> optionalRubroArticulo = rubroArticuloService.findOptionalById(articuloInsumoDto.getIdRubroArticulo());
        if(optionalRubroArticulo.isEmpty()) {
            throw new ServicioException("No se encontro la categoria seleccionada.");
        }

        Optional<UnidadMedida> optionalUnidadMedida = unidadMedidaService.findOptionalById(articuloInsumoDto.getIdUnidadMedida());
        if(optionalUnidadMedida.isEmpty()) {
            throw new ServicioException("No se encontro la unidad de medida seleccionada.");
        }

        articuloInsumo.setRubroArticulo(optionalRubroArticulo.get());
        articuloInsumo.setUnidadMedida(optionalUnidadMedida.get());

        articuloInsumo = this.save(articuloInsumo);

        if(imagen != null && !imagen.isEmpty()) {
            Map<String, Object> uploadData = imagenService.uploadImage(imagen, articuloInsumo.getId(), CLOUDINARY_FOLDER);
            articuloInsumo.setUrlImagen((String) uploadData.get("url"));

            articuloInsumo = this.save(articuloInsumo);
        }

        return articuloInsumoMapper.toDTO(articuloInsumo);
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
        Optional<ArticuloInsumo> optionalArticuloInsumo = articuloInsumoRepository.findById(id);
        if(optionalArticuloInsumo.isEmpty()) {
            throw new ServicioException("No existe un insumo con el id seleccionado.");
        }

        imagenService.deleteImage(id, CLOUDINARY_FOLDER);
        this.hardDelete(id);
    }

}

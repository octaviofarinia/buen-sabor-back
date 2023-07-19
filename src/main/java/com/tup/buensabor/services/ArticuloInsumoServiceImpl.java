package com.tup.buensabor.services;

import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoCompleteDto;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoDto;
import com.tup.buensabor.entities.*;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.mappers.ArticuloInsumoMapper;
import com.tup.buensabor.mappers.BaseMapper;
import com.tup.buensabor.repositories.ArticuloInsumoRepository;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.DetalleArticuloManufacturadoRepository;
import com.tup.buensabor.services.interfaces.ArticuloInsumoService;
import com.tup.buensabor.services.interfaces.RubroArticuloService;
import com.tup.buensabor.services.interfaces.UnidadMedidaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ArticuloInsumoServiceImpl extends BaseServiceImpl<ArticuloInsumo, ArticuloInsumoCompleteDto, Long> implements ArticuloInsumoService {

    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;

    @Autowired
    private DetalleArticuloManufacturadoRepository detalleArticuloManufacturadoRepository;

    @Autowired
    private ImagenService imagenService;

    @Autowired
    private RubroArticuloService rubroArticuloService;

    @Autowired
    private UnidadMedidaService unidadMedidaService;

    private static final String CLOUDINARY_FOLDER = "insumos";

    @Autowired
    private ArticuloInsumoMapper articuloInsumoMapper;

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

        ArticuloInsumo articuloInsumo = articuloInsumoMapper.toEntity(articuloInsumoDto);
        articuloInsumo.setFechaAlta(optionalArticuloInsumo.get().getFechaAlta());
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

    public void restarStock(DetallePedido detallePedido) throws ServicioException {
        List<DetalleArticuloManufacturado> ingredientes = detalleArticuloManufacturadoRepository.getByIdArticuloManufacturado(detallePedido.getArticuloManufacturado().getId());

        for (DetalleArticuloManufacturado ingrediente : ingredientes) {
            BigDecimal cantidadEnStock = ingrediente.getArticuloInsumo().getStockActual();
            BigDecimal cantidadRequerida = ingrediente.getCantidad();
            BigDecimal cantidadProductos = BigDecimal.valueOf(detallePedido.getCantidad());

            if(cantidadEnStock.compareTo(cantidadRequerida.multiply(cantidadProductos)) < 0) {
                throw new ServicioException("No hay stock suficiente para realizar el pedido.");
            }

            ArticuloInsumo articuloInsumo = ingrediente.getArticuloInsumo();
            articuloInsumo.setStockActual(cantidadEnStock.subtract(cantidadRequerida.multiply(cantidadProductos)));
            articuloInsumoRepository.save(articuloInsumo);
        }
    }

    public boolean validarStock(DetallePedido detallePedido) throws ServicioException {
        List<DetalleArticuloManufacturado> ingredientes = detalleArticuloManufacturadoRepository.getByIdArticuloManufacturado(detallePedido.getArticuloManufacturado().getId());

        for (DetalleArticuloManufacturado ingrediente : ingredientes) {
            BigDecimal cantidadEnStock = ingrediente.getArticuloInsumo().getStockActual();
            BigDecimal cantidadRequerida = ingrediente.getCantidad();
            BigDecimal cantidadProductos = BigDecimal.valueOf(detallePedido.getCantidad());

            if(cantidadEnStock.compareTo(cantidadRequerida.multiply(cantidadProductos)) < 0) {
                return false;
            }
        }

        return true;
    }
}

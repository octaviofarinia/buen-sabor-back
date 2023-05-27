package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.DetalleArticuloManufacturadoDto;
import com.tup.buensabor.dtos.rubroarticulo.RubroArticuloSimpleDto;
import com.tup.buensabor.entities.DetalleArticuloManufacturado;
import com.tup.buensabor.entities.RubroArticulo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetalleArticuloManufacturadoMapper extends BaseMapper<DetalleArticuloManufacturado, DetalleArticuloManufacturadoDto> {
    static DetalleArticuloManufacturadoMapper getInstance() {
        return Mappers.getMapper(DetalleArticuloManufacturadoMapper.class);
    }

    DetalleArticuloManufacturadoDto toDTO(DetalleArticuloManufacturado source);
    DetalleArticuloManufacturado toEntity(DetalleArticuloManufacturadoDto source);

    List<DetalleArticuloManufacturadoDto> toDTOsList(List<DetalleArticuloManufacturado> source);
    List<DetalleArticuloManufacturado> toEntitiesList(List<DetalleArticuloManufacturadoDto> source);
}

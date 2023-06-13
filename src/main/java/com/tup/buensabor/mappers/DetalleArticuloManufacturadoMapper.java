package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.detallearticulomanufacturado.DetalleArticuloManufacturadoDto;
import com.tup.buensabor.dtos.detallearticulomanufacturado.DetalleArticuloManufacturadoSimpleDto;
import com.tup.buensabor.entities.DetalleArticuloManufacturado;
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

    DetalleArticuloManufacturado toEntity(DetalleArticuloManufacturadoSimpleDto source);

    List<DetalleArticuloManufacturadoDto> toDTOsList(List<DetalleArticuloManufacturado> source);
    List<DetalleArticuloManufacturado> toEntitiesList(List<DetalleArticuloManufacturadoDto> source);
}

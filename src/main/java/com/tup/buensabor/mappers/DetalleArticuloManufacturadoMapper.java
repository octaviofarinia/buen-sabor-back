package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.detallearticulomanufacturado.DetalleArticuloManufacturadoCompleteDto;
import com.tup.buensabor.dtos.detallearticulomanufacturado.DetalleArticuloManufacturadoDto;
import com.tup.buensabor.dtos.detallearticulomanufacturado.DetalleArticuloManufacturadoSimpleDto;
import com.tup.buensabor.entities.DetalleArticuloManufacturado;
import com.tup.buensabor.mappers.utils.DateMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface DetalleArticuloManufacturadoMapper extends BaseMapper<DetalleArticuloManufacturado, DetalleArticuloManufacturadoDto> {
    DetalleArticuloManufacturadoDto toDTO(DetalleArticuloManufacturado source);
    DetalleArticuloManufacturado toEntity(DetalleArticuloManufacturadoDto source);

    DetalleArticuloManufacturado toEntity(DetalleArticuloManufacturadoSimpleDto source);

    List<DetalleArticuloManufacturadoDto> toDTOsList(List<DetalleArticuloManufacturado> source);
    List<DetalleArticuloManufacturado> toEntitiesList(List<DetalleArticuloManufacturadoDto> source);

    DetalleArticuloManufacturadoCompleteDto toCompleteDTO(DetalleArticuloManufacturado detalleEntity);
}

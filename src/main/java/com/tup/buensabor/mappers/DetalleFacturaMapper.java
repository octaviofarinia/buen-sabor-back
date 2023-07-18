package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.detallefactura.DetalleFacturaDto;
import com.tup.buensabor.entities.DetalleFactura;
import com.tup.buensabor.mappers.utils.DateMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface DetalleFacturaMapper extends BaseMapper<DetalleFactura, DetalleFacturaDto> {
    DetalleFacturaDto toDTO(DetalleFactura source);
    DetalleFactura toEntity(DetalleFacturaDto source);

    List<DetalleFacturaDto> toDTOsList(List<DetalleFactura> source);
    List<DetalleFactura> toEntitiesList(List<DetalleFacturaDto> source);
}

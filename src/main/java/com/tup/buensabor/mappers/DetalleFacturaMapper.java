package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.detallefactura.DetalleFacturaDto;
import com.tup.buensabor.entities.DetalleFactura;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetalleFacturaMapper extends BaseMapper<DetalleFactura, DetalleFacturaDto> {
    static DetalleFacturaMapper getInstance() {
        return Mappers.getMapper(DetalleFacturaMapper.class);
    }

    DetalleFacturaDto toDTO(DetalleFactura source);
    DetalleFactura toEntity(DetalleFacturaDto source);

    List<DetalleFacturaDto> toDTOsList(List<DetalleFactura> source);
    List<DetalleFactura> toEntitiesList(List<DetalleFacturaDto> source);
}

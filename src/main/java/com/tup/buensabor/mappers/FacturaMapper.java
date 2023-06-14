package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.FacturaDto;
import com.tup.buensabor.entities.Factura;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacturaMapper extends BaseMapper<Factura, FacturaDto> {
    static FacturaMapper getInstance() {
        return Mappers.getMapper(FacturaMapper.class);
    }

    FacturaDto toDTO(Factura source);
    Factura toEntity(FacturaDto source);

    List<FacturaDto> toDTOsList(List<Factura> source);
    List<Factura> toEntitiesList(List<FacturaDto> source);
}
package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.factura.FacturaDto;
import com.tup.buensabor.entities.Factura;
import com.tup.buensabor.mappers.utils.DateMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface FacturaMapper extends BaseMapper<Factura, FacturaDto> {
    FacturaDto toDTO(Factura source);
    Factura toEntity(FacturaDto source);

    List<FacturaDto> toDTOsList(List<Factura> source);
    List<Factura> toEntitiesList(List<FacturaDto> source);
}

package com.tup.buensabor.mappers;

import com.tup.buensabor.dtos.articulomanufacturado.ArticuloManufacturadoDto;
import com.tup.buensabor.entities.ArticuloManufacturado;
import com.tup.buensabor.mappers.utils.DateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface ArticuloManufacturadoMapper extends BaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto> {
    @Override
    @Mapping(target = "fechaBaja", source = "fechaBaja", qualifiedByName = "dateToString")
    ArticuloManufacturadoDto toDTO(ArticuloManufacturado source);

    @Override
    @Mapping(target = "fechaBaja", source = "fechaBaja", qualifiedByName = "stringToDate")
    ArticuloManufacturado toEntity(ArticuloManufacturadoDto source);

    @Override
    List<ArticuloManufacturadoDto> toDTOsList(List<ArticuloManufacturado> source);

    @Override
    List<ArticuloManufacturado> toEntitiesList(List<ArticuloManufacturadoDto> source);

    @Named("dateToString")
    default String dateToString(Date fechaBaja) {
        return fechaBaja != null ? new SimpleDateFormat("dd/MM/yyyy - hh:mm").format(fechaBaja) : null;
    }

    @Named("stringToDate")
    default Date stringToDate(String fechaBaja) {
        if (fechaBaja != null) {
            try {
                return new SimpleDateFormat("dd/MM/yyyy - hh:mm").parse(fechaBaja);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }
}

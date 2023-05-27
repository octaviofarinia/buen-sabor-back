package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.DetalleArticuloManufacturadoDto;
import com.tup.buensabor.entities.DetalleArticuloManufacturado;
import com.tup.buensabor.services.DetalleArticuloManufacturadoServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/detalles-articulos-manufacturados")
public class DetalleArticuloManufacturadoController extends BaseControllerImpl<DetalleArticuloManufacturado, DetalleArticuloManufacturadoDto, DetalleArticuloManufacturadoServiceImpl> {
}

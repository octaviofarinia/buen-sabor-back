package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.entities.ArticuloManufacturado;
import com.tup.buensabor.services.ArticuloManufacturadoServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/articulos-manufacturados")
public class ArticuloManufacturadoController extends BaseControllerImpl<ArticuloManufacturado, ArticuloManufacturadoServiceImpl> {
}

package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.entities.ArticuloInsumo;
import com.tup.buensabor.services.ArticuloInsumoServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/articulos-insumo")
public class ArticuloInsumoController extends BaseControllerImpl<ArticuloInsumo, ArticuloInsumoServiceImpl> {
}

package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.detallefactura.DetalleFacturaDto;
import com.tup.buensabor.entities.DetalleFactura;
import com.tup.buensabor.services.DetalleFacturaServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/detalles-facturas")
public class DetalleFacturaController extends BaseControllerImpl<DetalleFactura, DetalleFacturaDto, DetalleFacturaServiceImpl> {
}

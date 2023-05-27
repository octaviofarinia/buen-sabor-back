package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.DomicilioDto;
import com.tup.buensabor.entities.Domicilio;
import com.tup.buensabor.entities.Usuario;
import com.tup.buensabor.services.DomicilioServiceImpl;
import com.tup.buensabor.services.UsuarioServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/domicilios")
public class DomicilioController extends BaseControllerImpl<Domicilio, DomicilioDto, DomicilioServiceImpl> {
}

package com.tup.buensabor.controllers;

import com.tup.buensabor.controllers.base.BaseControllerImpl;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoCompleteDto;
import com.tup.buensabor.dtos.articuloinsumo.ArticuloInsumoDto;
import com.tup.buensabor.dtos.pedido.AltaPedidoDto;
import com.tup.buensabor.dtos.pedido.PedidoDto;
import com.tup.buensabor.entities.Pedido;
import com.tup.buensabor.exceptions.ServicioException;
import com.tup.buensabor.services.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/pedidos")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoDto, PedidoServiceImpl> {

    @PostMapping(value = "")
    public ResponseEntity<?> save(@RequestBody AltaPedidoDto altaPedidoDto) {
        try {
            servicio.altaPostPago(altaPedidoDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el insumo: " + e.getMessage());
        }
    }

}

package com.tup.buensabor.services;

import com.tup.buensabor.entities.ArticuloInsumo;
import com.tup.buensabor.entities.Usuario;
import com.tup.buensabor.mappers.ArticuloInsumoMapper;
import com.tup.buensabor.mappers.UsuarioMapper;
import com.tup.buensabor.repositories.ArticuloInsumoRepository;
import com.tup.buensabor.repositories.BaseRepository;
import com.tup.buensabor.repositories.UsuarioRepository;
import com.tup.buensabor.services.interfaces.ArticuloInsumoService;
import com.tup.buensabor.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloInsumoServiceImpl extends BaseServiceImpl<ArticuloInsumo, Long> implements ArticuloInsumoService {

    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;

    private ArticuloInsumoMapper articuloInsumoMapper = ArticuloInsumoMapper.getInstance();

    public ArticuloInsumoServiceImpl(BaseRepository<ArticuloInsumo, Long> baseRepository) {
        super(baseRepository);
    }

}

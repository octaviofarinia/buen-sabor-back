package com.tup.buensabor.repositories;

import com.tup.buensabor.dtos.ranking.ArticuloManufacturadoRankingDto;
import com.tup.buensabor.entities.ArticuloManufacturado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ArticuloManufacturadoRepository extends BaseRepository<ArticuloManufacturado, Long> {

    @Query("SELECT a FROM ArticuloManufacturado a WHERE a.denominacion LIKE %:nombre%")
    List<ArticuloManufacturado> findAllByNombre(@Param("nombre") String nombre);

    @Query("SELECT " +
            "art.denominacion as denominacion, " +
            "art.costo as costo, " +
            "art.precioVenta as precioVenta, " +
            "SUM(d.cantidad) as cantidadVendida, " +
            "SUM(d.cantidad * art.costo) as costoTotal, " +
            "SUM(d.cantidad * art.precioVenta) as ventaTotal, " +
            "(SUM(d.cantidad * art.precioVenta) - SUM(d.cantidad * art.costo)) as utilidadTotal " +
            "FROM DetallePedido d INNER JOIN d.pedido p INNER JOIN d.articuloManufacturado art " +
            "WHERE p.estado = com.tup.buensabor.enums.EstadoPedido.COMPLETADO " +
            "AND p.fechaPedido BETWEEN :desde AND :hasta " +
            "GROUP BY art.denominacion, art.costo, art.precioVenta " +
            "ORDER BY (SUM(d.cantidad * art.precioVenta) - SUM(d.cantidad * art.costo)) DESC")
    List<ArticuloManufacturadoRankingDto> ranking(Date desde, Date hasta);

    @Query("SELECT count(dp) > 0 FROM DetallePedido dp WHERE dp.articuloManufacturado.id = :idArticuloManfacturado")
    boolean isPresentInPedido(Long idArticuloManfacturado);

}

package com.tup.buensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "articulo_insumo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticuloInsumo extends Base {

    private String denominacion;

    @Column(name = "precio_compra", precision = 10, scale = 2)
    private Double precioCompra;

    @Column(name = "precio_venta", precision = 10, scale = 2)
    private Double precioVenta;

    @Column(name = "stock_actual", precision = 10, scale = 2)
    private Double stockActual;

    @Column(name = "stock_minimo", precision = 10, scale = 2)
    private Double stockMinimo;

    @ManyToOne()
    @JoinColumn(name = "id_unidad_medida")
    private UnidadMedida unidadMedida;

    @ManyToOne()
    @JoinColumn(name = "id_rubro_articulo")
    private RubroArticulo rubroArticulo;

    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;

}

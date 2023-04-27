package com.tup.buensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "rubro-articulo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RubroArticulo extends Base {

    @ManyToOne()
    @JoinColumn(name = "id_rubro_padre")
    private RubroArticulo rubroPadre;

    @OneToMany(mappedBy = "rubroPadre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RubroArticulo> subRubros;

    @Column(nullable = false)
    private String denominacion;

    public RubroArticulo(String denominacion, RubroArticulo rubroPadre) {
        this.denominacion = denominacion;
        this.rubroPadre = rubroPadre;
    }

}

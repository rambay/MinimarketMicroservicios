package com.minimarket.boletaservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_boleta_detalle")
@Data
public class BoletaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int cantidad;

    private Long idproducto;

    private double precioTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_boleta")
    private BoletaCabecera boletaCabecera;
}

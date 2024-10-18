package com.minimarket.productsservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="tb_producto")
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProducto;
    private String nombre;
    private Long cantidad;
    private String marca;
    private double precio;
    private String image;
    private LocalDate fechaVen;
    @ManyToOne
    @JoinColumn(name = "id_tipo_producto")
    private TipoProducto tipoProd;
    private Long idProveedor;
}

package com.minimarket.productsservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_tipoproducto")
@Data
public class TipoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
}

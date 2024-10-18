package com.minimarket.supplierservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_proveedor")
@Data
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String ruc;
    private Boolean activo;
}

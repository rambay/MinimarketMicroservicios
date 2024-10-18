package com.minimarket.usersservice.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "tb_rol")
@Data
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreRol;
    private String descripcionRol;
}

package com.minimarket.usersservice.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "tb_usuario")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastname;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;
}

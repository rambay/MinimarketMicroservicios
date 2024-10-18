package com.minimarket.usersservice.dtos;

import com.minimarket.usersservice.model.Genero;
import com.minimarket.usersservice.model.Rol;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UsuarioUpdateDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastname;
    private Genero genero;
    private RolUpdateDTO rol;
}

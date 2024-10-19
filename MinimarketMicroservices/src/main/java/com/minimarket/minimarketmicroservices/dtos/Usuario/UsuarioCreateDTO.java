package com.minimarket.minimarketmicroservices.dtos.Usuario;

import com.minimarket.minimarketmicroservices.dtos.Usuario.Rol.RolCreateDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioCreateDTO {
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastname;
    private Genero genero;
    private RolCreateDTO rol;
}

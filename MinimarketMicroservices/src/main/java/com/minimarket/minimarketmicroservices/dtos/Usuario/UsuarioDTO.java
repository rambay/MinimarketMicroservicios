package com.minimarket.minimarketmicroservices.dtos.Usuario;

import com.minimarket.minimarketmicroservices.dtos.Usuario.Rol.RolDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastname;
    private Genero genero;
    private RolDTO rol;
}

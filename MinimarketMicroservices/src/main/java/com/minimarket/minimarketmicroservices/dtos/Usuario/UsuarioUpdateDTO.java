package com.minimarket.minimarketmicroservices.dtos.Usuario;

import com.minimarket.minimarketmicroservices.dtos.Usuario.Rol.RolUpdateDTO;
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
    private List<RolUpdateDTO> roles;
}

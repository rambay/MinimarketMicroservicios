package com.minimarket.minimarketmicroservices.dtos.Usuario.Rol;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolUpdateDTO {
    private Long id;
    private String nombreRol;
    private String descripcionRol;
}
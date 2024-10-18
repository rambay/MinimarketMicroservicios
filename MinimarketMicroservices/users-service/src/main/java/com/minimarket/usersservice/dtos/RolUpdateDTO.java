package com.minimarket.usersservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolUpdateDTO {
    private Long id;
    private String nombreRol;
    private String descripcionRol;
}
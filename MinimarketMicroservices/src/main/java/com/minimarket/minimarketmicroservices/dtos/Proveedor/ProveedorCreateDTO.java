package com.minimarket.minimarketmicroservices.dtos.Proveedor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProveedorCreateDTO {
    private String nombre;
    private String ruc;
    private Boolean activo;
}
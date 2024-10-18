package com.minimarket.supplierservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProveedorDTO {
    private Long id;
    private String nombre;
    private String ruc;
    private Boolean activo;
}

package com.minimarket.boletaservice.dto;

import lombok.Data;


@Data
public class BoletaDetalleCreateDTO {
    private int cantidad;
    private Long idproducto;
    private double precioTotal;
}

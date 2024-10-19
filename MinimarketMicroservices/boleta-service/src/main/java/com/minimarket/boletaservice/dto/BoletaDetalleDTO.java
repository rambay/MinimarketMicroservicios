package com.minimarket.boletaservice.dto;

import lombok.Data;


@Data
public class BoletaDetalleDTO {
    private Long id;
    private int cantidad;
    private Long idproducto;
    private double precioTotal;
}

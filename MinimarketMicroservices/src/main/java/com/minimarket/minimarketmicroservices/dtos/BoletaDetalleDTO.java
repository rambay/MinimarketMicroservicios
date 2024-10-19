package com.minimarket.minimarketmicroservices.dtos;

import lombok.Data;

@Data
public class BoletaDetalleDTO {
    private Long id;
    private int cantidad;
    private Long idproducto;
    private double precioTotal;
}

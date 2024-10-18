package com.minimarket.minimarketmicroservices.dtos.Producto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductoDTO {
    private long idProducto;
    private String nombre;
    private Long cantidad;
    private String marca;
    private double precio;
    private String image;
    private LocalDate fechaVen;
    private Long idProveedor;
}

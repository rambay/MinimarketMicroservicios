package com.minimarket.minimarketmicroservices.dtos.Producto;

import com.minimarket.minimarketmicroservices.dtos.Producto.TipoProducto.TipoProductoCreateDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductoCreateDTO {
    private String nombre;
    private Long cantidad;
    private String marca;
    private double precio;
    private String image;
    private LocalDate fechaVen;
    private TipoProductoCreateDTO tipoProd;
    private Long idProveedor;
}
package com.minimarket.minimarketmicroservices.dtos.Producto;

import com.minimarket.minimarketmicroservices.dtos.Producto.TipoProducto.TipoProductoUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductoUpdateDTO {
    private long idProducto;
    private String nombre;
    private Long cantidad;
    private String marca;
    private double precio;
    private String image;
    private LocalDate fechaVen;
    private TipoProductoUpdateDTO tipoProd;
    private Long idProveedor;
}

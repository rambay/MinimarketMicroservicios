package com.minimarket.minimarketmicroservices.feignclient;

import com.minimarket.minimarketmicroservices.dtos.Producto.ProductoCreateDTO;
import com.minimarket.minimarketmicroservices.dtos.Producto.ProductoUpdateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "products-service", url = "http://localhost:8090/productos")
public interface ProductsClient {
    @GetMapping
    ResponseEntity<Map<String, Object>> listarProductos();

    @GetMapping("/{idProducto}")
    ResponseEntity<Map<String, Object>> buscarProductoPorId(@PathVariable("idProducto") Long idProducto);

    @PostMapping
    ResponseEntity<Map<String, Object>> agregarProducto(@RequestBody ProductoCreateDTO productoCreateDTO);

    @PutMapping("/{idProducto}")
    ResponseEntity<Map<String, Object>> actualizarProducto(@PathVariable("idProducto") Long idProducto, @RequestBody ProductoUpdateDTO productoUpdateDTO);

    @DeleteMapping("/{idProducto}")
    ResponseEntity<Map<String, Object>> eliminarProducto(@PathVariable("idProducto") Long idProducto);
}

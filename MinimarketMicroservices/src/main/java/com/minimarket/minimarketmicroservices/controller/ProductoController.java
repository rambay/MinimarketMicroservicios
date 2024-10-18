package com.minimarket.minimarketmicroservices.controller;

import com.minimarket.minimarketmicroservices.dtos.Producto.ProductoCreateDTO;
import com.minimarket.minimarketmicroservices.dtos.Producto.ProductoUpdateDTO;
import com.minimarket.minimarketmicroservices.service.ProductoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoController {

    private ProductoServiceImpl productoService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<Map<String, Object>> buscarProductoPorId(@PathVariable("idProducto") Long idProducto) {
        return productoService.obtenerProductoPorId(idProducto);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregarProducto(@RequestBody ProductoCreateDTO productoCreateDTO) {
        return productoService.registrarProducto(productoCreateDTO);
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<Map<String, Object>> actualizarProducto(@PathVariable("idProducto") Long idProducto, @RequestBody ProductoUpdateDTO productoUpdateDTO) {
        return productoService.actualizarProducto(idProducto, productoUpdateDTO);
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Map<String, Object>> eliminarProducto(@PathVariable("idProducto") Long idProducto) {
        return productoService.eliminarProductos(idProducto);
    }
}

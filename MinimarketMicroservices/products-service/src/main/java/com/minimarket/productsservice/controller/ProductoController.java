package com.minimarket.productsservice.controller;

import com.minimarket.productsservice.dtos.ProductoCreateDTO;
import com.minimarket.productsservice.dtos.ProductoUpdateDTO;
import com.minimarket.productsservice.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarProductos() {
        return service.listarProductos();
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<Map<String, Object>> buscarProductoPorId(@PathVariable("idProducto") Long idProducto) {
        return service.obtenerProductoPorId(idProducto);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregarProducto(@RequestBody ProductoCreateDTO productoCreateDTO) {
        return service.registrarProducto(productoCreateDTO);
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<Map<String, Object>> actualizarProducto(@PathVariable("idProducto") Long idProducto, @RequestBody ProductoUpdateDTO productoUpdateDTO) {
        return service.actualizarProducto(idProducto, productoUpdateDTO);
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Map<String, Object>> eliminarProducto(@PathVariable("idProducto") Long idProducto) {
        return service.eliminarProductos(idProducto);
    }
}

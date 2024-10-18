package com.minimarket.productsservice.service;

import com.minimarket.productsservice.dtos.ProductoCreateDTO;
import com.minimarket.productsservice.dtos.ProductoUpdateDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ProductoService {
    public ResponseEntity<Map<String, Object>> listarProductos();
    public ResponseEntity<Map<String, Object>> obtenerProductoPorId(Long id);
    public ResponseEntity<Map<String, Object>> registrarProducto(ProductoCreateDTO productoCreateDTO);
    public ResponseEntity<Map<String, Object>> actualizarProducto(Long id, ProductoUpdateDTO productoUpdateDTO);
    public ResponseEntity<Map<String, Object>> eliminarProductos(long id);
}

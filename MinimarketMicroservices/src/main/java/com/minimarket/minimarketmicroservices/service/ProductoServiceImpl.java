package com.minimarket.minimarketmicroservices.service;

import com.minimarket.minimarketmicroservices.dtos.Producto.ProductoCreateDTO;
import com.minimarket.minimarketmicroservices.dtos.Producto.ProductoUpdateDTO;
import com.minimarket.minimarketmicroservices.feignclient.ProductsClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class ProductoServiceImpl {

    private final ProductsClient productsClient;

    public ResponseEntity<Map<String, Object>> listarProductos() {
        return productsClient.listarProductos();
    }

    public ResponseEntity<Map<String, Object>> obtenerProductoPorId(Long id) {
        return productsClient.buscarProductoPorId(id);
    }

    public ResponseEntity<Map<String, Object>> registrarProducto(ProductoCreateDTO productoCreateDTO) {
        return productsClient.agregarProducto(productoCreateDTO);
    }

    public ResponseEntity<Map<String, Object>> actualizarProducto(Long id, ProductoUpdateDTO productoUpdateDTO) {
        return productsClient.actualizarProducto(id, productoUpdateDTO);
    }

    public ResponseEntity<Map<String, Object>> eliminarProductos(long id) {
        return productsClient.eliminarProducto(id);
    }
}

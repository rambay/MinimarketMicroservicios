package com.minimarket.productsservice.serviceImpl;

import com.minimarket.productsservice.dtos.ProductoCreateDTO;
import com.minimarket.productsservice.dtos.ProductoDTO;
import com.minimarket.productsservice.dtos.ProductoUpdateDTO;
import com.minimarket.productsservice.mappers.ProductoMapper;
import com.minimarket.productsservice.model.Producto;
import com.minimarket.productsservice.repository.ProductoRepository;
import com.minimarket.productsservice.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private ProductoRepository repository;

    @Override
    public ResponseEntity<Map<String, Object>> listarProductos() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Producto> listaProductos = repository.findAll();

        if (!listaProductos.isEmpty()) {
            List<ProductoDTO> productos = ProductoMapper.instancia.listaProductoToListaProductoDTO(listaProductos);
            respuesta.put("productos", productos);
            respuesta.put("mensaje", "Lista de productos");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "No existen registros");
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> obtenerProductoPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Producto> productoOpt = repository.findById(id);

        if (productoOpt.isPresent()) {
            ProductoDTO producto = ProductoMapper.instancia.productoToProductoDTO(productoOpt.get());
            respuesta.put("producto", producto);
            respuesta.put("mensaje", "Producto encontrado");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "Producto no encontrado");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> registrarProducto(ProductoCreateDTO productoCreateDTO) {
        Map<String, Object> respuesta = new HashMap<>();
        Producto nuevoProducto = ProductoMapper.instancia.productoCreateDTOToProducto(productoCreateDTO);
        Producto productoGuardado = repository.save(nuevoProducto);
        ProductoDTO producto = ProductoMapper.instancia.productoToProductoDTO(productoGuardado);

        respuesta.put("producto", producto);
        respuesta.put("mensaje", "Producto registrado exitosamente");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> actualizarProducto(Long id, ProductoUpdateDTO productoUpdateDTO) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Producto> productoExistente = repository.findById(id);

        if (productoExistente.isPresent()) {
            Producto productoActualizado = ProductoMapper.instancia.productoUpdateDTOToProducto(productoUpdateDTO);
            productoActualizado.setIdProducto(id);
            Producto productoGuardado = repository.save(productoActualizado);
            ProductoDTO producto = ProductoMapper.instancia.productoToProductoDTO(productoGuardado);

            respuesta.put("producto", producto);
            respuesta.put("mensaje", "Producto actualizado exitosamente");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "El producto no existe");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarProductos(long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Producto> productoExistente = repository.findById(id);

        if (productoExistente.isPresent()) {
            repository.deleteById(id);
            respuesta.put("mensaje", "Producto eliminado exitosamente");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "El producto no existe");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }
}

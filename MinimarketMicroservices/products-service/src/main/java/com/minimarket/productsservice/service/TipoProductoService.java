package com.minimarket.productsservice.service;

import com.minimarket.productsservice.dtos.TipoProductoCreateDTO;
import com.minimarket.productsservice.dtos.TipoProductoUpdateDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface TipoProductoService {
    public ResponseEntity<Map<String,Object>> listarTiposproductos();
    public ResponseEntity<Map<String,Object>> obtenerTipoProductoPorId(Long id);
    public ResponseEntity<Map<String,Object>> registrarTiposproductos(TipoProductoCreateDTO tipoProductoCreateDTO);
    public ResponseEntity<Map<String,Object>> actualizarTiposproductos(Long id, TipoProductoUpdateDTO tipoProductoUpdateDTO);
    public ResponseEntity<Map<String,Object>> eliminarTipoproductos(long id);
}

package com.minimarket.supplierservice.service;

import com.minimarket.supplierservice.dtos.ProveedorCreateDTO;
import com.minimarket.supplierservice.dtos.ProveedorUpdateDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ProveedorService {
    public ResponseEntity<Map<String, Object>> listarProveedores();
    public ResponseEntity<Map<String, Object>> obtenerProveedorPorId(Long id);
    public ResponseEntity<Map<String, Object>> registrarProveedor(ProveedorCreateDTO proveedorCreateDTO);
    public ResponseEntity<Map<String, Object>> actualizarProveedor(Long id, ProveedorUpdateDTO proveedorUpdateDTO);
    public ResponseEntity<Map<String, Object>> eliminarProveedor(long id);
}

package com.minimarket.minimarketmicroservices.service;

import com.minimarket.minimarketmicroservices.dtos.Proveedor.ProveedorCreateDTO;
import com.minimarket.minimarketmicroservices.dtos.Proveedor.ProveedorUpdateDTO;
import com.minimarket.minimarketmicroservices.feignclient.SupplierClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class ProveedorServiceImpl {

    private final SupplierClient supplierClient;

    public ResponseEntity<Map<String, Object>> listarProveedores() {
        return supplierClient.listarProveedores();
    }

    public ResponseEntity<Map<String, Object>> obtenerProveedorPorId(Long id) {
        return supplierClient.buscarProveedorPorId(id);
    }

    public ResponseEntity<Map<String, Object>> registrarProveedor(ProveedorCreateDTO proveedorCreateDTO) {
        return supplierClient.agregarProveedor(proveedorCreateDTO);
    }

    public ResponseEntity<Map<String, Object>> actualizarProveedor(Long id, ProveedorUpdateDTO proveedorUpdateDTO) {
        return supplierClient.actualizarProveedor(id, proveedorUpdateDTO);
    }

    public ResponseEntity<Map<String, Object>> eliminarProveedor(long id) {
        return supplierClient.eliminarProveedor(id);
    }
}

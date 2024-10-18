package com.minimarket.minimarketmicroservices.feignclient;

import com.minimarket.minimarketmicroservices.dtos.Proveedor.ProveedorCreateDTO;
import com.minimarket.minimarketmicroservices.dtos.Proveedor.ProveedorUpdateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "supplier-service", url = "http://localhost:8091/proveedores")
public interface SupplierClient {
    @GetMapping
    ResponseEntity<Map<String, Object>> listarProveedores();

    @GetMapping("/{idProveedor}")
    ResponseEntity<Map<String, Object>> buscarProveedorPorId(@PathVariable("idProveedor") Long idProveedor);

    @PostMapping
    ResponseEntity<Map<String, Object>> agregarProveedor(@RequestBody ProveedorCreateDTO proveedorCreateDTO);

    @PutMapping("/{idProveedor}")
    ResponseEntity<Map<String, Object>> actualizarProveedor(@PathVariable("idProveedor") Long idProveedor, @RequestBody ProveedorUpdateDTO proveedorUpdateDTO);

    @DeleteMapping("/{idProveedor}")
    ResponseEntity<Map<String, Object>> eliminarProveedor(@PathVariable("idProveedor") Long idProveedor);
}

package com.minimarket.minimarketmicroservices.controller;

import com.minimarket.minimarketmicroservices.dtos.Proveedor.ProveedorCreateDTO;
import com.minimarket.minimarketmicroservices.dtos.Proveedor.ProveedorUpdateDTO;
import com.minimarket.minimarketmicroservices.service.ProveedorServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/proveedores")
@AllArgsConstructor
public class ProveedorController {

    private ProveedorServiceImpl proveedorService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarProveedores() {
        return proveedorService.listarProveedores();
    }

    @GetMapping("/{idProveedor}")
    public ResponseEntity<Map<String, Object>> buscarProveedorPorId(@PathVariable("idProveedor") Long idProveedor) {
        return proveedorService.obtenerProveedorPorId(idProveedor);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregarProveedor(@RequestBody ProveedorCreateDTO proveedorCreateDTO) {
        return proveedorService.registrarProveedor(proveedorCreateDTO);
    }

    @PutMapping("/{idProveedor}")
    public ResponseEntity<Map<String, Object>> actualizarProveedor(@PathVariable("idProveedor") Long idProveedor, @RequestBody ProveedorUpdateDTO proveedorUpdateDTO) {
        return proveedorService.actualizarProveedor(idProveedor, proveedorUpdateDTO);
    }

    @DeleteMapping("/{idProveedor}")
    public ResponseEntity<Map<String, Object>> eliminarProveedor(@PathVariable("idProveedor") Long idProveedor) {
        return proveedorService.eliminarProveedor(idProveedor);
    }
}

package com.minimarket.supplierservice.controller;

import com.minimarket.supplierservice.dtos.ProveedorCreateDTO;
import com.minimarket.supplierservice.dtos.ProveedorUpdateDTO;
import com.minimarket.supplierservice.service.ProveedorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/proveedores")
@AllArgsConstructor
public class ProveedorController {

    private final ProveedorService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarProveedores() {
        return service.listarProveedores();
    }

    @GetMapping("/{idProveedor}")
    public ResponseEntity<Map<String, Object>> buscarProveedorPorId(@PathVariable("idProveedor") Long idProveedor) {
        return service.obtenerProveedorPorId(idProveedor);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregarProveedor(@RequestBody ProveedorCreateDTO proveedorCreateDTO) {
        return service.registrarProveedor(proveedorCreateDTO);
    }

    @PutMapping("/{idProveedor}")
    public ResponseEntity<Map<String, Object>> actualizarProveedor(@PathVariable("idProveedor") Long idProveedor, @RequestBody ProveedorUpdateDTO proveedorUpdateDTO) {
        return service.actualizarProveedor(idProveedor, proveedorUpdateDTO);
    }

    @DeleteMapping("/{idProveedor}")
    public ResponseEntity<Map<String, Object>> eliminarProveedor(@PathVariable("idProveedor") Long idProveedor) {
        return service.eliminarProveedor(idProveedor);
    }
}

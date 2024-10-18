package com.minimarket.minimarketmicroservices.controller;

import com.minimarket.minimarketmicroservices.dtos.Producto.TipoProducto.TipoProductoCreateDTO;
import com.minimarket.minimarketmicroservices.dtos.Producto.TipoProducto.TipoProductoUpdateDTO;
import com.minimarket.minimarketmicroservices.service.TipoProductoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tiposproductos")
@AllArgsConstructor
public class TipoProductoController {

    private final TipoProductoServiceImpl tipoProductoService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarTipoProductos() {
        return tipoProductoService.listarTiposproductos();
    }

    @GetMapping("/{idtiproducto}")
    public ResponseEntity<Map<String, Object>> obtenerTipoProductoPorId(@PathVariable("idtiproducto") Long idtiproducto) {
        return tipoProductoService.buscarTipoproductoPorId(idtiproducto);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearTipoProducto(@RequestBody TipoProductoCreateDTO tipoProductoCreateDTO) {
        return tipoProductoService.crearTipoProducto(tipoProductoCreateDTO);
    }

    @PutMapping("/{idtiproducto}")
    public ResponseEntity<Map<String, Object>> actualizarTipoProducto(@PathVariable("idtiproducto") Long idtiproducto, @RequestBody TipoProductoUpdateDTO tipoProductoUpdateDTO) {
        return tipoProductoService.modificarTipoProducto(idtiproducto, tipoProductoUpdateDTO);
    }

    @DeleteMapping("/{idtiproducto}")
    public ResponseEntity<Map<String, Object>> eliminarTipoProducto(@PathVariable("idtiproducto") Long idtiproducto) {
        return tipoProductoService.eliminarTipoProducto(idtiproducto);
    }
}

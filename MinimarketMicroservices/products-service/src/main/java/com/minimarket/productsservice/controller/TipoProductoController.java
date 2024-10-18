package com.minimarket.productsservice.controller;

import com.minimarket.productsservice.dtos.TipoProductoCreateDTO;
import com.minimarket.productsservice.dtos.TipoProductoUpdateDTO;
import com.minimarket.productsservice.service.TipoProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tiposproductos")
@AllArgsConstructor
public class TipoProductoController {

    private final TipoProductoService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarTiposproductos() {
        return service.listarTiposproductos();
    }

    @GetMapping("/{idTipoproducto}")
        public ResponseEntity<Map<String, Object>> buscarTipoproductoPorId(@PathVariable("idTipoproducto") Long idTipoproducto) {
        return service.obtenerTipoProductoPorId(idTipoproducto);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearTipoProducto(@RequestBody TipoProductoCreateDTO tipoProductoCreateDTO) {
        return service.registrarTiposproductos(tipoProductoCreateDTO);
    }

    @PutMapping("/{idTipoproducto}")
    public ResponseEntity<Map<String, Object>> modificarTipoProducto(@PathVariable("idTipoproducto") Long idTipoproducto,@RequestBody TipoProductoUpdateDTO tipoProductoUpdateDTO) {
        return service.actualizarTiposproductos(idTipoproducto, tipoProductoUpdateDTO);
    }

    @DeleteMapping("/{idTipoproducto}")
    public ResponseEntity<Map<String, Object>> eliminarTipoProducto(@PathVariable("idTipoproducto") Long idTipoproducto) {
        return service.eliminarTipoproductos(idTipoproducto);
    }
}

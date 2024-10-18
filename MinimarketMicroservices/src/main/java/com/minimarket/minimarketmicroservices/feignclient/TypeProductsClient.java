package com.minimarket.minimarketmicroservices.feignclient;

import com.minimarket.minimarketmicroservices.dtos.Producto.TipoProducto.TipoProductoCreateDTO;
import com.minimarket.minimarketmicroservices.dtos.Producto.TipoProducto.TipoProductoUpdateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "type-products-service", url = "http://localhost:8090/tiposproductos")
public interface TypeProductsClient {

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarTiposproductos();

    @GetMapping("/{idTipoproducto}")
    public ResponseEntity<Map<String, Object>> buscarTipoproductoPorId(@PathVariable("idTipoproducto") Long idTipoproducto);

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearTipoProducto(@RequestBody TipoProductoCreateDTO tipoProductoCreateDTO);

    @PutMapping("/{idTipoproducto}")
    public ResponseEntity<Map<String, Object>> modificarTipoProducto(@PathVariable("idTipoproducto") Long idTipoproducto,@RequestBody TipoProductoUpdateDTO tipoProductoUpdateDTO);

    @DeleteMapping("/{idTipoproducto}")
    public ResponseEntity<Map<String, Object>> eliminarTipoProducto(@PathVariable("idTipoproducto") Long idTipoproducto);
}

package com.minimarket.minimarketmicroservices.service;

import com.minimarket.minimarketmicroservices.dtos.Producto.TipoProducto.TipoProductoCreateDTO;
import com.minimarket.minimarketmicroservices.dtos.Producto.TipoProducto.TipoProductoUpdateDTO;
import com.minimarket.minimarketmicroservices.feignclient.TypeProductsClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
@AllArgsConstructor
public class TipoProductoServiceImpl {

    private final TypeProductsClient typeProductsClient;

    public ResponseEntity<Map<String, Object>> listarTiposproductos() {
        return typeProductsClient.listarTiposproductos();
    }

    public ResponseEntity<Map<String, Object>> buscarTipoproductoPorId(@PathVariable("idTipoproducto") Long idTipoproducto) {
        return typeProductsClient.buscarTipoproductoPorId(idTipoproducto);
    }

    public ResponseEntity<Map<String, Object>> crearTipoProducto(@RequestBody TipoProductoCreateDTO tipoProductoCreateDTO) {
        return typeProductsClient.crearTipoProducto(tipoProductoCreateDTO);
    }

    public ResponseEntity<Map<String, Object>> modificarTipoProducto(@PathVariable("idTipoproducto") Long idTipoproducto,@RequestBody TipoProductoUpdateDTO tipoProductoUpdateDTO) {
        return typeProductsClient.modificarTipoProducto(idTipoproducto, tipoProductoUpdateDTO);
    }

    public ResponseEntity<Map<String, Object>> eliminarTipoProducto(@PathVariable("idTipoproducto") Long idTipoproducto) {
        return typeProductsClient.eliminarTipoProducto(idTipoproducto);
    }


}

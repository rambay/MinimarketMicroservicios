package com.minimarket.minimarketmicroservices.feignclient;

import com.minimarket.minimarketmicroservices.dtos.BoletaCabeceraCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "boletas-service", url = "http://localhost:8099/boletas")
public interface BoletaClient {


    @GetMapping
    public ResponseEntity<Map<String, Object>> listarBoletas();
    @GetMapping("/{idBoleta}")
    public ResponseEntity<Map<String, Object>> obtenerBoletaPorId(@PathVariable("idBoleta") long idBoleta);
    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarBoleta(@RequestBody BoletaCabeceraCreateDTO boletaCabeceraCreateDTO);
}

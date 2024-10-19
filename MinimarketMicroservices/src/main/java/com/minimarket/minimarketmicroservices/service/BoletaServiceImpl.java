package com.minimarket.minimarketmicroservices.service;

import com.minimarket.minimarketmicroservices.dtos.BoletaCabeceraCreateDTO;
import com.minimarket.minimarketmicroservices.feignclient.BoletaClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
@AllArgsConstructor
public class BoletaServiceImpl {

    private final BoletaClient boletaClient;

    public ResponseEntity<Map<String, Object>> listarBoletas() {
        return boletaClient.listarBoletas();
    }

    public ResponseEntity<Map<String, Object>> obtenerBoletaPorId(@PathVariable("idBoleta") long idBoleta) {
        return boletaClient.obtenerBoletaPorId(idBoleta);
    }

    public ResponseEntity<Map<String, Object>> registrarBoleta(@RequestBody BoletaCabeceraCreateDTO boletaCabeceraCreateDTO) {
        return boletaClient.registrarBoleta(boletaCabeceraCreateDTO);
    }
}

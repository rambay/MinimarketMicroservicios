package com.minimarket.minimarketmicroservices.controller;

import com.minimarket.minimarketmicroservices.dtos.BoletaCabeceraCreateDTO;
import com.minimarket.minimarketmicroservices.service.BoletaServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/boletas")
@AllArgsConstructor
public class BoletaController {

    private final BoletaServiceImpl service;

    @GetMapping
    public ResponseEntity<Map<String,Object>> listarBoletas() {
        return service.listarBoletas();
    }

    @GetMapping("/{idBoleta}")
    public ResponseEntity<Map<String, Object>> obtenerBoletaPorId(@PathVariable("idBoleta") Long idBoleta) {
        return service.obtenerBoletaPorId(idBoleta);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarBoleta(@RequestBody BoletaCabeceraCreateDTO boletaCabeceraCreateDTO) {
        return service.registrarBoleta(boletaCabeceraCreateDTO);
    }
}

package com.minimarket.boletaservice.controller;

import com.minimarket.boletaservice.dto.BoletaCabeceraCreateDTO;
import com.minimarket.boletaservice.model.BoletaCabecera;
import com.minimarket.boletaservice.repository.BoletaCabeceraRepository;
import com.minimarket.boletaservice.service.BoletaCabeceraService;
import com.minimarket.boletaservice.serviceImpl.BoletaCabeceraServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/boletas")
@AllArgsConstructor
public class BoletaController {

    private final BoletaCabeceraServiceImpl service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarBoletas() {
        return service.listarBoletas();
    }

    @GetMapping("/{idBoleta}")
    public ResponseEntity<Map<String, Object>> obtenerBoletaPorId(@PathVariable("idBoleta") long idBoleta) {
        return service.obtenerBoletaPorID(idBoleta);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarBoleta(@RequestBody BoletaCabeceraCreateDTO boletaCabeceraCreateDTO) {
        return service.registrarBoleta(boletaCabeceraCreateDTO);
    }
}

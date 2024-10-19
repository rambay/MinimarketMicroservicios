package com.minimarket.boletaservice.service;

import com.minimarket.boletaservice.dto.BoletaCabeceraCreateDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface BoletaCabeceraService {
    ResponseEntity<Map<String, Object>> listarBoletas();
    ResponseEntity<Map<String,Object>> obtenerBoletaPorID(Long idBoleta);
    ResponseEntity<Map<String,Object>> registrarBoleta(BoletaCabeceraCreateDTO boletaCabeceraCreateDTO);
}

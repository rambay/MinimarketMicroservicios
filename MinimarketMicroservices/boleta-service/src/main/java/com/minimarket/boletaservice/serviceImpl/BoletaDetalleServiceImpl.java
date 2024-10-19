package com.minimarket.boletaservice.serviceImpl;

import com.minimarket.boletaservice.dto.BoletaDetalleCreateDTO;
import com.minimarket.boletaservice.dto.BoletaDetalleDTO;
import com.minimarket.boletaservice.mapper.BoletaDetalleMapper;
import com.minimarket.boletaservice.model.BoletaDetalle;
import com.minimarket.boletaservice.repository.BoletaDetalleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class BoletaDetalleServiceImpl {

    private final BoletaDetalleRepository repository;

    public ResponseEntity<Map<String, Object>> registrarBoletaDetalle(BoletaDetalleCreateDTO boletaDetalleCreateDTO) {
        Map<String, Object> respuesta = new HashMap<>();

        try {
            BoletaDetalle boletaDetalle = BoletaDetalleMapper.instancia.boletaDetalleCreateDTOToBoletaDetalle(boletaDetalleCreateDTO);
            BoletaDetalle boletaGuardada = repository.save(boletaDetalle);
            BoletaDetalleDTO boletaDetalleRespuesta = BoletaDetalleMapper.instancia.boletaDetalleToBoletaDetalleDTO(boletaGuardada);

            respuesta.put("boletaDetalle", boletaDetalleRespuesta);
            respuesta.put("mensaje", "Boleta detalle registrada exitosamente");
            respuesta.put("status", HttpStatus.CREATED);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (Exception e) {
            respuesta.put("mensaje", "Error al registrar boleta detalle: " + e.getMessage());
            respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }
}

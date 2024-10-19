package com.minimarket.boletaservice.serviceImpl;

import com.minimarket.boletaservice.dto.*;
import com.minimarket.boletaservice.mapper.BoletaCabeceraMapper;
import com.minimarket.boletaservice.mapper.BoletaDetalleMapper;
import com.minimarket.boletaservice.model.BoletaCabecera;
import com.minimarket.boletaservice.model.BoletaDetalle;
import com.minimarket.boletaservice.repository.BoletaCabeceraRepository;
import com.minimarket.boletaservice.repository.BoletaDetalleRepository;
import com.minimarket.boletaservice.service.BoletaCabeceraService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoletaCabeceraServiceImpl implements BoletaCabeceraService {

    private final BoletaCabeceraRepository boletaCabeceraRepository;
    private final BoletaDetalleRepository boletaDetalleRepository;

    @Override
    public ResponseEntity<Map<String, Object>> listarBoletas() {
        HashMap<String, Object> response = new HashMap<>();
        List<BoletaCabeceraDTO> listaCabeceras = BoletaCabeceraMapper.instancia
                .listarBoletaCabeceraToListarBoletaCabeceraDTO(boletaCabeceraRepository.findAll());

        for (BoletaCabeceraDTO boletaCabeceraDTO : listaCabeceras) {
            List<BoletaDetalleDTO> boletaDetalleDTOs = BoletaDetalleMapper.instancia
                    .listarBoletaDetalleToListarBoletaDetalleDTO(boletaDetalleRepository.findByBoletaCabecera_Id(boletaCabeceraDTO.getId()));

            boletaCabeceraDTO.setBoletaDetalleDTO(boletaDetalleDTOs);
        }

        response.put("boletas", listaCabeceras);
        response.put("mensaje", "Registro Correcto");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> obtenerBoletaPorID(Long idBoleta) {
        HashMap<String, Object> response = new HashMap<>();
        Optional<BoletaCabecera> boleta = boletaCabeceraRepository.findById(idBoleta);

        if (boleta.isPresent()) {
            BoletaCabeceraDTO boletaCabeceraDTO = BoletaCabeceraMapper.instancia
                    .boletaCabeceraToBoletaCabeceraDTO(boleta.get());

            List<BoletaDetalleDTO> detalles = BoletaDetalleMapper.instancia
                    .listarBoletaDetalleToListarBoletaDetalleDTO(boletaDetalleRepository.findByBoletaCabecera_Id(boleta.get().getId()));
            boletaCabeceraDTO.setBoletaDetalleDTO(detalles);

            response.put("boleta", boletaCabeceraDTO);
            return ResponseEntity.ok(response);
        } else {
            response.put("mensaje", "Boleta no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> registrarBoleta(BoletaCabeceraCreateDTO boletaCabeceraCreateDTO) {
        HashMap<String, Object> response = new HashMap<>();

        BoletaCabecera boleta = BoletaCabeceraMapper.instancia.boletaCabeceraCreateDTOToBoletaCabecera(boletaCabeceraCreateDTO);
        BoletaCabecera respuestaEntity = boletaCabeceraRepository.save(boleta);

        for (BoletaDetalleCreateDTO bolDetalleCreateDTO : boletaCabeceraCreateDTO.getBoletaDetalleCreateDTO()) {
            BoletaDetalle bd = BoletaDetalleMapper.instancia.boletaDetalleCreateDTOToBoletaDetalle(bolDetalleCreateDTO);
            bd.setBoletaCabecera(respuestaEntity);
            boletaDetalleRepository.save(bd);
        }

        BoletaCabeceraDTO respuestaDTO = BoletaCabeceraMapper.instancia
                .boletaCabeceraToBoletaCabeceraDTO(respuestaEntity);

        respuestaDTO.setBoletaDetalleDTO(
                BoletaDetalleMapper.instancia.listarBoletaDetalleToListarBoletaDetalleDTO(
                        boletaDetalleRepository.findByBoletaCabecera_Id(respuestaEntity.getId()))
        );

        response.put("boleta", respuestaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}

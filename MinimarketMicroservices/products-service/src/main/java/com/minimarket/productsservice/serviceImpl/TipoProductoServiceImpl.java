package com.minimarket.productsservice.serviceImpl;

import com.minimarket.productsservice.dtos.TipoProductoCreateDTO;
import com.minimarket.productsservice.dtos.TipoProductoDTO;
import com.minimarket.productsservice.dtos.TipoProductoUpdateDTO;
import com.minimarket.productsservice.mappers.TipoProductoMapper;
import com.minimarket.productsservice.model.TipoProducto;
import com.minimarket.productsservice.repository.TipoProductoRepository;
import com.minimarket.productsservice.service.TipoProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TipoProductoServiceImpl implements TipoProductoService {

    private final TipoProductoRepository repository;

    @Override
    public ResponseEntity<Map<String, Object>> listarTiposproductos() {
        Map<String, Object> respuesta = new HashMap<>();
        List<TipoProducto> listaTiposproductos = repository.findAll();

        if (!listaTiposproductos.isEmpty()) {
            List<TipoProductoDTO> tiposproductos = TipoProductoMapper.instancia.listaTipoProductoToListaTipoProductoDTO(listaTiposproductos);
            respuesta.put("tipoproducto", tiposproductos);
            respuesta.put("mensaje", "Lista de tipos de productos");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "No existen registros");
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> obtenerTipoProductoPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<TipoProducto> tipoproductoOpt = repository.findById(id);

        if (tipoproductoOpt.isPresent()) {
            TipoProductoDTO tipoproducto = TipoProductoMapper.instancia.tipoProductoToTipoProductoDTO(tipoproductoOpt.get());
            respuesta.put("tipoproducto", tipoproducto);
            respuesta.put("mensaje", "Tipo producto encontrado");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "Tipo producto no encontrado");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> registrarTiposproductos(TipoProductoCreateDTO tipoProductoCreateDTO) {
        Map<String, Object> respuesta = new HashMap<>();
        TipoProducto nuevoTipoProducto = TipoProductoMapper.instancia.tipoProductoCreateDTOToTipoProducto(tipoProductoCreateDTO);
        TipoProducto tipoProductoGuardado = repository.save(nuevoTipoProducto);
        TipoProductoDTO tipoproducto = TipoProductoMapper.instancia.tipoProductoToTipoProductoDTO(tipoProductoGuardado);

        respuesta.put("tipoproducto", tipoproducto);
        respuesta.put("mensaje", "Tipo producto registrado exitosamente");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> actualizarTiposproductos(Long id, TipoProductoUpdateDTO tipoProductoUpdateDTO) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<TipoProducto> tipoProductoExistente = repository.findById(id);

        if (tipoProductoExistente.isPresent()) {
            TipoProducto tipoProductoActualizado = TipoProductoMapper.instancia.tipoProductoUpdateDTOToTipoProducto(tipoProductoUpdateDTO);
            tipoProductoActualizado.setId(id);
            TipoProducto tipoProductoGuardado = repository.save(tipoProductoActualizado);
            TipoProductoDTO tipoproducto = TipoProductoMapper.instancia.tipoProductoToTipoProductoDTO(tipoProductoGuardado);

            respuesta.put("tipoproducto", tipoproducto);
            respuesta.put("mensaje", "Tipo producto actualizado exitosamente");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "El tipo producto no existe");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarTipoproductos(long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<TipoProducto> tipoProductoExistente = repository.findById(id);

        if (tipoProductoExistente.isPresent()) {
            repository.deleteById(id);
            respuesta.put("mensaje", "Tipo producto eliminado exitosamente");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "El tipo producto no existe");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }
}

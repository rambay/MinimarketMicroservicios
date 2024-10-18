package com.minimarket.supplierservice.serviceImpl;

import com.minimarket.supplierservice.dtos.ProveedorCreateDTO;
import com.minimarket.supplierservice.dtos.ProveedorDTO;
import com.minimarket.supplierservice.dtos.ProveedorUpdateDTO;
import com.minimarket.supplierservice.mappers.ProveedorMapper;
import com.minimarket.supplierservice.model.Proveedor;
import com.minimarket.supplierservice.repository.ProveedorRepository;
import com.minimarket.supplierservice.service.ProveedorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

    private ProveedorRepository repository;

    @Override
    public ResponseEntity<Map<String, Object>> listarProveedores() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Proveedor> listaProveedores = repository.findAll();

        if (!listaProveedores.isEmpty()) {
            List<ProveedorDTO> proveedores = ProveedorMapper.instancia.listarProveedorToListarProveedorDTO(listaProveedores);
            respuesta.put("proveedores", proveedores);
            respuesta.put("mensaje", "Lista de proveedores");
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
    public ResponseEntity<Map<String, Object>> obtenerProveedorPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Proveedor> proveedorOpt = repository.findById(id);

        if (proveedorOpt.isPresent()) {
            ProveedorDTO proveedor = ProveedorMapper.instancia.proveedorToProveedorDTO(proveedorOpt.get());
            respuesta.put("proveedor", proveedor);
            respuesta.put("mensaje", "Proveedor encontrado");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "Proveedor no encontrado");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> registrarProveedor(ProveedorCreateDTO proveedorCreateDTO) {
        Map<String, Object> respuesta = new HashMap<>();
        Proveedor nuevoProveedor = ProveedorMapper.instancia.proveedorCreateDTOToProveedor(proveedorCreateDTO);
        Proveedor proveedorGuardado = repository.save(nuevoProveedor);
        ProveedorDTO proveedor = ProveedorMapper.instancia.proveedorToProveedorDTO(proveedorGuardado);

        respuesta.put("proveedor", proveedor);
        respuesta.put("mensaje", "Proveedor registrado exitosamente");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> actualizarProveedor(Long id, ProveedorUpdateDTO proveedorUpdateDTO) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Proveedor> proveedorExistente = repository.findById(id);

        if (proveedorExistente.isPresent()) {
            Proveedor proveedorActualizado = ProveedorMapper.instancia.proveedorUpdateDTOToProveedor(proveedorUpdateDTO);
            proveedorActualizado.setId(id);
            Proveedor proveedorGuardado = repository.save(proveedorActualizado);
            ProveedorDTO proveedor = ProveedorMapper.instancia.proveedorToProveedorDTO(proveedorGuardado);

            respuesta.put("proveedor", proveedor);
            respuesta.put("mensaje", "Proveedor actualizado exitosamente");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "El proveedor no existe");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarProveedor(long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Proveedor> proveedorExistente = repository.findById(id);

        if (proveedorExistente.isPresent()) {
            repository.deleteById(id);
            respuesta.put("mensaje", "Proveedor eliminado exitosamente");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "El proveedor no existe");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }
}

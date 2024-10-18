package com.minimarket.usersservice.serviceImpl;

import com.minimarket.usersservice.dtos.RolCreateDTO;
import com.minimarket.usersservice.dtos.RolDTO;
import com.minimarket.usersservice.dtos.RolUpdateDTO;
import com.minimarket.usersservice.mappers.RolMapper;
import com.minimarket.usersservice.model.Rol;
import com.minimarket.usersservice.repository.RolRepository;
import com.minimarket.usersservice.service.RolService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository repository;

    @Override
    public ResponseEntity<Map<String, Object>> listarRoles() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Rol> roles = repository.findAll();
        Set<Rol> rolesSet = new HashSet<>(roles);

        if (!roles.isEmpty()) {
            Set<RolDTO> rolDTOs = RolMapper.instancia.listaRolToSetRolDTO(roles);
            respuesta.put("roles", rolDTOs);
            respuesta.put("mensaje", "Lista de roles");
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
    public ResponseEntity<Map<String, Object>> buscarRolPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Rol> rolOpt = repository.findById(id);

        if (rolOpt.isPresent()) {
            RolDTO rolDTO = RolMapper.instancia.rolToRolDTO(rolOpt.get());
            respuesta.put("rol", rolDTO);
            respuesta.put("mensaje", "Rol encontrado");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "Rol no encontrado");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> registrarRol(RolCreateDTO rolCreateDTO) {
        Map<String, Object> respuesta = new HashMap<>();
        Rol nuevoRol = RolMapper.instancia.rolCreateDTOToRol(rolCreateDTO);
        Rol rolGuardado = repository.save(nuevoRol);
        RolDTO rolDTO = RolMapper.instancia.rolToRolDTO(rolGuardado);

        respuesta.put("rol", rolDTO);
        respuesta.put("mensaje", "Rol registrado exitosamente");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> actualizarRol(Long id, RolUpdateDTO rolUpdateDTO) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Rol> rolExistente = repository.findById(id);

        if (rolExistente.isPresent()) {
            Rol rolActualizado = RolMapper.instancia.rolUpdateDTOToRol(rolUpdateDTO);
            rolActualizado.setId(id);
            Rol rolGuardado = repository.save(rolActualizado);
            RolDTO rolDTO = RolMapper.instancia.rolToRolDTO(rolGuardado);

            respuesta.put("rol", rolDTO);
            respuesta.put("mensaje", "Rol actualizado exitosamente");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "El rol no existe");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarRol(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Rol> rolExistente = repository.findById(id);

        if (rolExistente.isPresent()) {
            repository.deleteById(id);
            respuesta.put("mensaje", "Rol eliminado exitosamente");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "El rol no existe");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }
}

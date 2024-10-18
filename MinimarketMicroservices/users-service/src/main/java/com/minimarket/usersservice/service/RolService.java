package com.minimarket.usersservice.service;

import com.minimarket.usersservice.dtos.RolCreateDTO;
import com.minimarket.usersservice.dtos.RolUpdateDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface RolService {
    public ResponseEntity<Map<String, Object>> listarRoles();
    public ResponseEntity<Map<String, Object>> buscarRolPorId(Long id);
    public ResponseEntity<Map<String, Object>> registrarRol(RolCreateDTO rolCreateDTO);
    public ResponseEntity<Map<String, Object>> actualizarRol(Long id, RolUpdateDTO rolUpdateDTO);
    public ResponseEntity<Map<String, Object>> eliminarRol(Long id);
}

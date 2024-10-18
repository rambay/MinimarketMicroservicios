package com.minimarket.usersservice.service;

import com.minimarket.usersservice.dtos.UsuarioCreateDTO;
import com.minimarket.usersservice.dtos.UsuarioUpdateDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UsuarioService {
    public ResponseEntity<Map<String, Object>> listarUsuarios();
    public ResponseEntity<Map<String, Object>> listarUsuariosPorId(Long id);
    public ResponseEntity<Map<String, Object>> agregarUsuarios(UsuarioCreateDTO usuarioCreateDTO);
    public ResponseEntity<Map<String, Object>> editarUsuarios(Long id, UsuarioUpdateDTO usuarioUpdateDTO);
    public ResponseEntity<Map<String, Object>> eliminarUsuarios(Long id);
}

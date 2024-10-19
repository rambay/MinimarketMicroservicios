package com.minimarket.minimarketmicroservices.service;

import com.minimarket.minimarketmicroservices.dtos.AuthRequest;
import com.minimarket.minimarketmicroservices.dtos.AuthResponse;
import com.minimarket.minimarketmicroservices.dtos.Usuario.UsuarioCreateDTO;
import com.minimarket.minimarketmicroservices.dtos.Usuario.UsuarioUpdateDTO;
import com.minimarket.minimarketmicroservices.feignclient.LoginFeign;
import com.minimarket.minimarketmicroservices.feignclient.UsersClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl {

    private final UsersClient usersClient;
    private final LoginFeign loginFeign;

    public AuthResponse login(AuthRequest authRequest) {
        ResponseEntity<AuthResponse> response = loginFeign.login(authRequest);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Error en el inicio de sesión");
        }
    }

    // Método para listar usuarios, acepta el token JWT como parámetro
    public ResponseEntity<Map<String, Object>> listarUsuarios(String token) {
        return usersClient.listarUsuarios(token);
    }

    // Método para listar usuario por ID, acepta el token JWT como parámetro
    public ResponseEntity<Map<String, Object>> listarUsuariosPorId(String token, Long id) {
        return usersClient.listarUsuariosPorId(token, id);
    }

    // Método para agregar usuarios, acepta el token JWT como parámetro
    public ResponseEntity<Map<String, Object>> agregarUsuarios(UsuarioCreateDTO usuarioCreateDTO) {
        return usersClient.agregarUsuarios(usuarioCreateDTO);
    }

    // Método para editar usuarios, acepta el token JWT como parámetro
    public ResponseEntity<Map<String, Object>> editarUsuarios(String token, Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
        return usersClient.editarUsuarios(token, id, usuarioUpdateDTO);
    }

    // Método para eliminar usuarios, acepta el token JWT como parámetro
    public ResponseEntity<Map<String, Object>> eliminarUsuarios(String token, Long id) {
        return usersClient.eliminarUsuarios(token, id);
    }
}

package com.minimarket.minimarketmicroservices.controller;

import com.minimarket.minimarketmicroservices.dtos.AuthRequest;
import com.minimarket.minimarketmicroservices.dtos.AuthResponse;
import com.minimarket.minimarketmicroservices.dtos.Usuario.UsuarioCreateDTO;
import com.minimarket.minimarketmicroservices.dtos.Usuario.UsuarioUpdateDTO;
import com.minimarket.minimarketmicroservices.service.UsuarioServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioServiceImpl usuarioService;

    // Método para listar todos los usuarios, se necesita el token JWT
    @GetMapping
    public ResponseEntity<Map<String,Object>> listarUsuarios(@RequestHeader("Authorization") String token) {
        return usuarioService.listarUsuarios(token);
    }

    // Método para listar usuario por ID, se necesita el token JWT
    @GetMapping("/{idUsuario}")
    public ResponseEntity<Map<String,Object>> listarUsuariosPorId(@RequestHeader("Authorization") String token, @PathVariable Long idUsuario) {
        return usuarioService.listarUsuariosPorId(token, idUsuario);
    }

    // Método para agregar un usuario, se necesita el token JWT
    @PostMapping
    public ResponseEntity<Map<String,Object>> agregarUsuarios(@RequestHeader("Authorization") String token, @RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        return usuarioService.agregarUsuarios(token, usuarioCreateDTO);
    }

    // Método para editar un usuario, se necesita el token JWT
    @PutMapping("/{idUsuario}")
    public ResponseEntity<Map<String,Object>> editarUsuarios(@RequestHeader("Authorization") String token, @PathVariable("idUsuario") Long idUsuario, @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
        return usuarioService.editarUsuarios(token, idUsuario, usuarioUpdateDTO);
    }

    // Método para eliminar un usuario, se necesita el token JWT
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Map<String,Object>> eliminarUsuarios(@RequestHeader("Authorization") String token, @PathVariable Long idUsuario) {
        return usuarioService.eliminarUsuarios(token, idUsuario);
    }
}

package com.minimarket.minimarketmicroservices.controller;

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

    @GetMapping
    public ResponseEntity<Map<String,Object>> listarUsuarios(@RequestHeader("Authorization") String token) {
        return usuarioService.listarUsuarios(token);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Map<String,Object>> listarUsuariosPorId(@RequestHeader("Authorization") String token, @PathVariable Long idUsuario) {
        return usuarioService.listarUsuariosPorId(token, idUsuario);
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> agregarUsuarios(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        return usuarioService.agregarUsuarios(usuarioCreateDTO);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Map<String,Object>> editarUsuarios(@RequestHeader("Authorization") String token, @PathVariable("idUsuario") Long idUsuario, @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
        return usuarioService.editarUsuarios(token, idUsuario, usuarioUpdateDTO);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Map<String,Object>> eliminarUsuarios(@RequestHeader("Authorization") String token, @PathVariable Long idUsuario) {
        return usuarioService.eliminarUsuarios(token, idUsuario);
    }
}

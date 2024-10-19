package com.minimarket.usersservice.controller;

import com.minimarket.usersservice.dtos.UsuarioCreateDTO;
import com.minimarket.usersservice.dtos.UsuarioUpdateDTO;
import com.minimarket.usersservice.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    public ResponseEntity<Map<String,Object>> listarUsuarios(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return service.listarUsuarios();
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Map<String,Object>> listarUsuariosPorId(@PathVariable Long idUsuario, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return service.listarUsuariosPorId(idUsuario);
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> agregarUsuarios(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        return service.agregarUsuarios(usuarioCreateDTO);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Map<String,Object>> editarUsuarios(@PathVariable("idUsuario") Long idUsuario,@RequestBody UsuarioUpdateDTO usuarioUpdateDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return service.editarUsuarios(idUsuario, usuarioUpdateDTO);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Map<String,Object>> eliminarUsuarios(@PathVariable Long idUsuario, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return service.eliminarUsuarios(idUsuario);
    }
}

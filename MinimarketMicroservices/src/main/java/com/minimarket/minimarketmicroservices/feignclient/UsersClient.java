package com.minimarket.minimarketmicroservices.feignclient;

import com.minimarket.minimarketmicroservices.dtos.Usuario.UsuarioCreateDTO;
import com.minimarket.minimarketmicroservices.dtos.Usuario.UsuarioUpdateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "users-service", url = "http://localhost:8092/usuarios")
public interface UsersClient {

    @GetMapping
    ResponseEntity<Map<String, Object>> listarUsuarios(@RequestHeader("Authorization") String token);

    @GetMapping("/{idUsuario}")
    ResponseEntity<Map<String,Object>> listarUsuariosPorId(@RequestHeader("Authorization") String token, @PathVariable Long idUsuario);

    @PostMapping
    ResponseEntity<Map<String,Object>> agregarUsuarios(@RequestBody UsuarioCreateDTO usuarioCreateDTO);

    @PutMapping("/{idUsuario}")
    ResponseEntity<Map<String,Object>> editarUsuarios(@RequestHeader("Authorization") String token, @PathVariable("idUsuario") Long idUsuario, @RequestBody UsuarioUpdateDTO usuarioUpdateDTO);

    @DeleteMapping("/{idUsuario}")
    ResponseEntity<Map<String,Object>> eliminarUsuarios(@RequestHeader("Authorization") String token, @PathVariable Long idUsuario);
}

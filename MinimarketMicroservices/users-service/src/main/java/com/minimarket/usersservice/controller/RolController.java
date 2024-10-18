package com.minimarket.usersservice.controller;

import com.minimarket.usersservice.dtos.RolCreateDTO;
import com.minimarket.usersservice.dtos.RolUpdateDTO;
import com.minimarket.usersservice.service.RolService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RolController {

    private final RolService rolService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarRoles() {
        return rolService.listarRoles();
    }

    @GetMapping("/{idRol}")
    public ResponseEntity<Map<String, Object>> buscarRolPorId(@PathVariable Long idRol) {
        return rolService.buscarRolPorId(idRol);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarRol(@RequestBody RolCreateDTO rolCreateDTO) {
        return rolService.registrarRol(rolCreateDTO);
    }

    @PutMapping("/{idRol}")
    public ResponseEntity<Map<String, Object>> actualizarRol(@PathVariable("idRol") Long idRol, @RequestBody RolUpdateDTO rolUpdateDTO) {
        return rolService.actualizarRol(idRol, rolUpdateDTO);
    }

    @DeleteMapping("/{idRol}")
    public ResponseEntity<Map<String, Object>> eliminarRol(@PathVariable Long idRol) {
        return rolService.eliminarRol(idRol);
    }
}

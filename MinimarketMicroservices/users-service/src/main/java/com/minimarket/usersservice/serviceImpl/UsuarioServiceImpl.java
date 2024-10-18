package com.minimarket.usersservice.serviceImpl;

import com.minimarket.usersservice.dtos.UsuarioCreateDTO;
import com.minimarket.usersservice.dtos.UsuarioDTO;
import com.minimarket.usersservice.dtos.UsuarioUpdateDTO;
import com.minimarket.usersservice.mappers.UsuarioMapper;
import com.minimarket.usersservice.model.Usuario;
import com.minimarket.usersservice.repository.UsuarioRepository;
import com.minimarket.usersservice.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    @Override
    public ResponseEntity<Map<String, Object>> listarUsuarios() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Usuario> usuarios = repository.findAll();

        if (!usuarios.isEmpty()) {
            List<UsuarioDTO> usuarioDTOs = UsuarioMapper.instancia.listaUsuarioToListaUsuarioDTO(usuarios);
            respuesta.put("usuarios", usuarioDTOs);
            respuesta.put("mensaje", "Lista de usuarios");
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
    public ResponseEntity<Map<String, Object>> listarUsuariosPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Usuario> usuario = repository.findById(id);

        if (usuario.isPresent()) {
            UsuarioDTO usuarioDTO = UsuarioMapper.instancia.usuarioToUsuarioDTO(usuario.get());
            respuesta.put("usuario", usuarioDTO);
            respuesta.put("mensaje", "Usuario encontrado");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "Usuario no encontrado");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> agregarUsuarios(UsuarioCreateDTO usuarioCreateDTO) {
        Map<String, Object> respuesta = new HashMap<>();
        Usuario nuevoUsuario = UsuarioMapper.instancia.usuarioCreateDTOToUsuario(usuarioCreateDTO);
        Usuario usuarioGuardado = repository.save(nuevoUsuario);
        UsuarioDTO usuarioDTO = UsuarioMapper.instancia.usuarioToUsuarioDTO(usuarioGuardado);

        respuesta.put("usuario", usuarioDTO);
        respuesta.put("mensaje", "Usuario agregado exitosamente");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarUsuarios(Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
        Map<String, Object> respuesta = new HashMap<>();

        Optional<Usuario> usuarioExistente = repository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuarioActualizado = UsuarioMapper.instancia.usuarioUpdateDTOToUsuario(usuarioUpdateDTO);
            usuarioActualizado.setId(id);
            Usuario usuarioGuardado = repository.save(usuarioActualizado);
            UsuarioDTO usuarioDTO = UsuarioMapper.instancia.usuarioToUsuarioDTO(usuarioGuardado);

            respuesta.put("usuario", usuarioDTO);
            respuesta.put("mensaje", "Usuario actualizado exitosamente");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());

            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "Usuario no encontrado");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarUsuarios(Long id) {
        Map<String, Object> respuesta = new HashMap<>();

        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isPresent()) {
            repository.deleteById(id);
            respuesta.put("mensaje", "Usuario eliminado exitosamente");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "Usuario no encontrado");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }
}

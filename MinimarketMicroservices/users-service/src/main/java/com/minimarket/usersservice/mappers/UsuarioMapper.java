package com.minimarket.usersservice.mappers;

import com.minimarket.usersservice.dtos.UsuarioCreateDTO;
import com.minimarket.usersservice.dtos.UsuarioDTO;
import com.minimarket.usersservice.dtos.UsuarioUpdateDTO;
import com.minimarket.usersservice.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UsuarioMapper {
    UsuarioMapper instancia = Mappers.getMapper(UsuarioMapper.class);

    List<UsuarioDTO> listaUsuarioToListaUsuarioDTO(List<Usuario> usuarios);
    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);
    Usuario usuarioCreateDTOToUsuario(UsuarioCreateDTO usuarioCreateDTO);
    Usuario usuarioUpdateDTOToUsuario(UsuarioUpdateDTO usuarioUpdateDTO);
}

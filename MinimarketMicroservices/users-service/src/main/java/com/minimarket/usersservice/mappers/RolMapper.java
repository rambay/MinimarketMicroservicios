package com.minimarket.usersservice.mappers;

import com.minimarket.usersservice.dtos.RolCreateDTO;
import com.minimarket.usersservice.dtos.RolDTO;
import com.minimarket.usersservice.dtos.RolUpdateDTO;
import com.minimarket.usersservice.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface RolMapper {
    RolMapper instancia = Mappers.getMapper(RolMapper.class);

    Set<RolDTO> listaRolToSetRolDTO(List<Rol> rol);
    RolDTO rolToRolDTO(Rol rol);
    Rol rolCreateDTOToRol(RolCreateDTO rolCreateDTO);
    Rol rolUpdateDTOToRol(RolUpdateDTO rolUpdateDTO);
}

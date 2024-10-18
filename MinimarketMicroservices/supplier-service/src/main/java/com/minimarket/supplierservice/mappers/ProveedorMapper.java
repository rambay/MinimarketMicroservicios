package com.minimarket.supplierservice.mappers;

import com.minimarket.supplierservice.dtos.ProveedorCreateDTO;
import com.minimarket.supplierservice.dtos.ProveedorDTO;
import com.minimarket.supplierservice.dtos.ProveedorUpdateDTO;
import com.minimarket.supplierservice.model.Proveedor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProveedorMapper {
    ProveedorMapper instancia = Mappers.getMapper(ProveedorMapper.class);

    List<ProveedorDTO> listarProveedorToListarProveedorDTO(List<Proveedor> proveedor);
    ProveedorDTO proveedorToProveedorDTO(Proveedor proveedor);
    Proveedor proveedorCreateDTOToProveedor(ProveedorCreateDTO proveedorCreateDTO);
    Proveedor proveedorUpdateDTOToProveedor(ProveedorUpdateDTO proveedorUpdateDTO);
}

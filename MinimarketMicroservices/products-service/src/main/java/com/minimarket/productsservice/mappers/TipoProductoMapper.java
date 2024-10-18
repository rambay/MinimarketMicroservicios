package com.minimarket.productsservice.mappers;

import com.minimarket.productsservice.dtos.TipoProductoCreateDTO;
import com.minimarket.productsservice.dtos.TipoProductoDTO;
import com.minimarket.productsservice.dtos.TipoProductoUpdateDTO;
import com.minimarket.productsservice.model.TipoProducto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TipoProductoMapper {
    TipoProductoMapper instancia = Mappers.getMapper(TipoProductoMapper.class);

    List<TipoProductoDTO> listaTipoProductoToListaTipoProductoDTO(List<TipoProducto> lista);
    TipoProductoDTO tipoProductoToTipoProductoDTO(TipoProducto tipoProducto);
    TipoProducto tipoProductoCreateDTOToTipoProducto(TipoProductoCreateDTO tipoProductoCreateDTO);
    TipoProducto tipoProductoUpdateDTOToTipoProducto(TipoProductoUpdateDTO tipoProductoUpdateDTO);
}

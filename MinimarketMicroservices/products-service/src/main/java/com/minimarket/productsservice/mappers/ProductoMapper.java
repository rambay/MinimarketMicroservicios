package com.minimarket.productsservice.mappers;

import com.minimarket.productsservice.dtos.ProductoCreateDTO;
import com.minimarket.productsservice.dtos.ProductoDTO;
import com.minimarket.productsservice.dtos.ProductoUpdateDTO;
import com.minimarket.productsservice.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductoMapper {
    ProductoMapper instancia = Mappers.getMapper(ProductoMapper.class);

    List<ProductoDTO> listaProductoToListaProductoDTO(List<Producto> lista);

    ProductoDTO productoToProductoDTO(Producto producto);

    Producto productoCreateDTOToProducto(ProductoCreateDTO productoCreateDTO);
    Producto productoUpdateDTOToProducto(ProductoUpdateDTO productoUpdateDTO);
}

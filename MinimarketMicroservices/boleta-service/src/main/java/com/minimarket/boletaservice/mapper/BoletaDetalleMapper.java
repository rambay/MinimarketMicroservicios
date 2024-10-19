package com.minimarket.boletaservice.mapper;

import com.minimarket.boletaservice.dto.BoletaDetalleCreateDTO;
import com.minimarket.boletaservice.dto.BoletaDetalleDTO;
import com.minimarket.boletaservice.model.BoletaDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BoletaDetalleMapper {
    BoletaDetalleMapper instancia = Mappers.getMapper(BoletaDetalleMapper.class);

    BoletaDetalleDTO boletaDetalleToBoletaDetalleDTO(BoletaDetalle boletaDetalle);
    List<BoletaDetalleDTO> listarBoletaDetalleToListarBoletaDetalleDTO(List<BoletaDetalle> boletaDetalle);
    BoletaDetalle boletaDetalleCreateDTOToBoletaDetalle(BoletaDetalleCreateDTO boletaDetalleCreateDTO);
}

package com.minimarket.boletaservice.mapper;

import com.minimarket.boletaservice.dto.*;
import com.minimarket.boletaservice.model.BoletaCabecera;
import com.minimarket.boletaservice.model.BoletaDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BoletaCabeceraMapper {
    BoletaCabeceraMapper instancia = Mappers.getMapper(BoletaCabeceraMapper.class);

    BoletaCabeceraDTO boletaCabeceraToBoletaCabeceraDTO(BoletaCabecera boletaCabecera);
    List<BoletaCabeceraDTO> listarBoletaCabeceraToListarBoletaCabeceraDTO(List<BoletaCabecera> boletaCabecera);
    BoletaCabecera boletaCabeceraCreateDTOToBoletaCabecera(BoletaCabeceraCreateDTO boletaCabeceraCreateDTO);
}

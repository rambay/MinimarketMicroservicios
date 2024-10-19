package com.minimarket.boletaservice.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BoletaCabeceraCreateDTO {
    private Long clienteId;
    private Date fechaEmision;
    private List<BoletaDetalleCreateDTO> boletaDetalleCreateDTO;
}
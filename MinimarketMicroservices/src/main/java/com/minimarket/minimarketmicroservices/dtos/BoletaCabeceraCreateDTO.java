package com.minimarket.minimarketmicroservices.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class BoletaCabeceraCreateDTO {
    private Long clienteId;
    private Date fechaEmision;
    private List<BoletaDetalleCreateDTO> boletaDetalleCreateDTO;
}

package com.minimarket.boletaservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_boleta_cabecera")
@Data
public class BoletaCabecera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clienteId;
    @Column(name = "fechaBoleta", nullable = false)
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaEmision;
    @OneToMany(mappedBy = "boletaCabecera")
    private List<BoletaDetalle> boletaDetalle;
}

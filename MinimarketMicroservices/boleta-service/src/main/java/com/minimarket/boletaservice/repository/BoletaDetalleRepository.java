package com.minimarket.boletaservice.repository;

import com.minimarket.boletaservice.model.BoletaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoletaDetalleRepository extends JpaRepository<BoletaDetalle, Long> {
    public List<BoletaDetalle> findByBoletaCabecera_Id(Long boletaId);
}

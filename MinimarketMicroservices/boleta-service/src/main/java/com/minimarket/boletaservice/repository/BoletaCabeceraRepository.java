package com.minimarket.boletaservice.repository;

import com.minimarket.boletaservice.model.BoletaCabecera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletaCabeceraRepository extends JpaRepository<BoletaCabecera, Long> {
}

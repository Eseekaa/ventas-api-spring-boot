package com.api.ventas_api_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.ventas_api_spring_boot.models.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
}

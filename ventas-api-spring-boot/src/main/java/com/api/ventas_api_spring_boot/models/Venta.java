package com.api.ventas_api_spring_boot.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenta;

    private String fecha;

    private Double monto;
}

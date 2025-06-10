package com.api.ventas_api_spring_boot.dto;

import lombok.Data;

@Data
public class VentaDto {
    private Integer ID_Venta;
    private String fecha;
    private Double monto;
}

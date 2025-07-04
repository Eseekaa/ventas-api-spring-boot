package com.api.ventas_api_spring_boot.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDto extends RepresentationModel <VentaDto> {
    private Integer ID_Venta;
    private String fecha;
    private Double monto;
}

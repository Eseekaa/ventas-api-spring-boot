package com.api.ventas_api_spring_boot.dto;

import java.time.LocalDate;

import org.springframework.hateoas.Link;

public class VentaDto {

    private Integer ID_Venta;
    private LocalDate fecha;
    private Double monto;

    public Integer getID_Venta() {
        return ID_Venta;
    }

    public void setID_Venta(Integer iD_Venta) {
        this.ID_Venta = iD_Venta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public void add(Link withSelfRel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
}

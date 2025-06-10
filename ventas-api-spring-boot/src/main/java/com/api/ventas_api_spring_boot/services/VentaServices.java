package com.api.ventas_api_spring_boot.services;

import com.api.ventas_api_spring_boot.models.Venta;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ventas_api_spring_boot.dto.VentaDto;
import com.api.ventas_api_spring_boot.repository.VentaRepository;

@Service

public class VentaServices {

    @Autowired

    private VentaRepository venRep;

    private Venta toEntity(VentaDto dto)

    { 
        Venta ven = new Venta();
        ven.setIdVenta(dto.getID_Venta());

        ven.setFecha(dto.getFecha());
        ven.setMonto(dto.getMonto());
        return ven;
    }

    private VentaDto toDto(Venta ven)

    {
        VentaDto dto = new VentaDto();
        dto.setID_Venta(ven.getIdVenta());

        dto.setFecha(ven.getFecha());
        dto.setMonto(ven.getMonto());
        return dto;
    }

    public VentaDto crearVen (VentaDto dto)

    {
        Venta ven = toEntity(dto);
        Venta savedVenta = venRep.save(ven);
        return toDto(savedVenta);
    }

    public List <VentaDto> listarVen()
    {
        return venRep.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public VentaDto buscarVen(Integer ID_Venta)
    {
        Venta ven = venRep.findById(ID_Venta).orElseThrow(() -> new RuntimeException("Venta no encontrada."));
        return toDto(ven);
    }

    public VentaDto actuaVen(Integer ID_Venta, VentaDto dto)
    {
        Venta venExi = venRep.findById(ID_Venta)
        .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + ID_Venta));

        return toDto(venRep.save(venExi));
    }

    public void eliminarVen(Integer ID_Venta)
    {
        venRep.deleteById(ID_Venta);
    }
}
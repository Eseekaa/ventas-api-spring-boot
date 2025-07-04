package com.api.ventas_api_spring_boot.services;

import com.api.ventas_api_spring_boot.dto.VentaDto;
import com.api.ventas_api_spring_boot.models.Venta;
import com.api.ventas_api_spring_boot.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VentaServices {

    @Autowired
    private VentaRepository ventaRepository;

    // Convertir de DTO a entidad
    private Venta toEntity(VentaDto dto) {
        Venta venta = new Venta();
        venta.setIdVenta(dto.getID_Venta());
        venta.setFecha(dto.getFecha());
        venta.setMonto(dto.getMonto());
        return venta;
    }

    // Convertir de entidad a DTO
    private VentaDto toDto(Venta venta) {
        VentaDto dto = new VentaDto();
        dto.setID_Venta(venta.getIdVenta());
        dto.setFecha(venta.getFecha());
        dto.setMonto(venta.getMonto());
        return dto;
    }

    // Crear
    public VentaDto crearVenta(VentaDto dto) {
        Venta nuevaVenta = ventaRepository.save(toEntity(dto));
        return toDto(nuevaVenta);
    }

    // Listar todas
    public List<VentaDto> listarVentas() {
        return ventaRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Buscar por ID
    public VentaDto buscarVenta(int id) {
        Optional<Venta> ventaOpt = ventaRepository.findById(id);
        if (ventaOpt.isEmpty()) {
            throw new RuntimeException("Venta no encontrada.");
        }
        return toDto(ventaOpt.get());
    }

    // Actualizar
    public VentaDto actualizarVenta(int id, VentaDto dto) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada."));
        venta.setFecha(dto.getFecha());
        venta.setMonto(dto.getMonto());
        Venta actualizada = ventaRepository.save(venta);
        return toDto(actualizada);
    }

    // Eliminar
    public void eliminarVenta(int id) {
        ventaRepository.deleteById(id);
    }

    public Object actuaVen(Integer iD_Venta, VentaDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actuaVen'");
    }
}

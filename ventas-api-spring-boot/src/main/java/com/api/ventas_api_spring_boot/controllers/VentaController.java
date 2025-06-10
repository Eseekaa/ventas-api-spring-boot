package com.api.ventas_api_spring_boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.api.ventas_api_spring_boot.dto.VentaDto;
import com.api.ventas_api_spring_boot.services.VentaServices;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaServices ventaSer;

    @PostMapping
    public ResponseEntity<VentaDto> crearVenta(@RequestBody VentaDto dto) {
        return ResponseEntity.ok(ventaSer.crearVen(dto));
    }

    @GetMapping
    public ResponseEntity<List<VentaDto>> listarVentas() {
        return ResponseEntity.ok(ventaSer.listarVen());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDto> obtener(@PathVariable Integer ID_Venta) {
        return ResponseEntity.ok(ventaSer.buscarVen(ID_Venta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDto> actualizar(@PathVariable Integer ID_Venta, @RequestBody VentaDto dto) {
        return ResponseEntity.ok(ventaSer.actuaVen(ID_Venta, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer ID_Venta) {
        ventaSer.eliminarVen(ID_Venta);
        return ResponseEntity.noContent().build();
    }
}

package com.api.ventas_api_spring_boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.api.ventas_api_spring_boot.dto.VentaDto;
import com.api.ventas_api_spring_boot.services.VentaServices;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaServices ventaSer;

    @PostMapping
    public ResponseEntity<VentaDto> crearVenta(@RequestBody VentaDto dto) {
        return ResponseEntity.ok(ventaSer.crearVenta(null));
    }

    @GetMapping
    public ResponseEntity<List<VentaDto>> listarVentas() {
        return ResponseEntity.ok(ventaSer.listarVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDto> obtener(@PathVariable Integer ID_Venta) {
        return ResponseEntity.ok(ventaSer.buscarVenta(ID_Venta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizar(@PathVariable Integer ID_Venta, @RequestBody VentaDto dto) {
        return ResponseEntity.ok(ventaSer.actuaVen(ID_Venta, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer ID_Venta) {
        ventaSer.eliminarVenta(ID_Venta);
        return ResponseEntity.noContent().build();
    }

    //METODO HATEOAS para buscar por ID
    @GetMapping("/hateoas/{id}")
    public VentaDto obtenerHATEOAS(@PathVariable Integer id) {
    VentaDto dto = ventaSer.buscarVenta(id);
    dto.add(linkTo(methodOn(VentaController.class).obtenerHATEOAS(id)).withSelfRel());
    dto.add(linkTo(methodOn(VentaController.class).obtenerTodosHATEOAS()).withRel("todos"));
    dto.add(linkTo(methodOn(VentaController.class).eliminar(id)).withRel("eliminar"));
    return dto;
    }

    //METODO HATEOAS para listar todos los productos utilizando HATEOAS
    @GetMapping("/hateoas")
    public List<VentaDto> obtenerTodosHATEOAS() {
    List<VentaDto> lista = ventaSer.listarVentas();
    for (VentaDto dto : lista) {
    dto.add(linkTo(methodOn(VentaController.class).obtenerHATEOAS(dto.getID_Venta())).withSelfRel());
    }
    return lista;
    }
}

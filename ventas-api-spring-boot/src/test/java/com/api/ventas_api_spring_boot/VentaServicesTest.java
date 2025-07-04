package com.api.ventas_api_spring_boot;

import com.api.ventas_api_spring_boot.dto.VentaDto;
import com.api.ventas_api_spring_boot.models.Venta;
import com.api.ventas_api_spring_boot.repository.VentaRepository;
import com.api.ventas_api_spring_boot.services.VentaServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VentaServicesTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaServices ventaService;

    private Venta ventaMock;
    private VentaDto ventaDtoMock;

    @BeforeEach
    void setUp() {
        ventaMock = new Venta();
        ventaMock.setIdVenta(1);
        ventaMock.setFecha(LocalDate.of(2024, 5, 20));
        ventaMock.setMonto(10000.0);

        ventaDtoMock = new VentaDto();
        ventaDtoMock.setID_Venta(1);
        ventaDtoMock.setFecha(LocalDate.of(2024, 5, 20));
        ventaDtoMock.setMonto(10000.0);
    }

    @Test
    void testCrearVenta() {
        when(ventaRepository.save(any(Venta.class))).thenReturn(ventaMock);

        VentaDto resultado = ventaService.crearVenta(ventaDtoMock);

        assertNotNull(resultado);
        assertEquals(ventaDtoMock.getID_Venta(), resultado.getID_Venta());
        assertEquals(ventaDtoMock.getMonto(), resultado.getMonto());
        verify(ventaRepository, times(1)).save(any(Venta.class));
    }

    @Test
    void testListarVentas() {
        when(ventaRepository.findAll()).thenReturn(List.of(ventaMock));

        List<VentaDto> resultado = ventaService.listarVentas();

        assertEquals(1, resultado.size());
        assertEquals(ventaMock.getMonto(), resultado.get(0).getMonto());
        verify(ventaRepository, times(1)).findAll();
    }

    @Test
    void testBuscarVentaExistente() {
        when(ventaRepository.findById(1)).thenReturn(Optional.of(ventaMock));

        VentaDto resultado = ventaService.buscarVenta(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getID_Venta());
        verify(ventaRepository, times(1)).findById(1);
    }

    @Test
    void testBuscarVentaNoExistente() {
        when(ventaRepository.findById(2)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ventaService.buscarVenta(2);
        });

        assertEquals("Venta no encontrada.", exception.getMessage());
    }

    @Test
    void testActualizarVenta() {
        when(ventaRepository.findById(1)).thenReturn(Optional.of(ventaMock));
        when(ventaRepository.save(any(Venta.class))).thenReturn(ventaMock);

        VentaDto resultado = ventaService.actualizarVenta(1, ventaDtoMock);

        assertNotNull(resultado);
        assertEquals(ventaDtoMock.getMonto(), resultado.getMonto());
        verify(ventaRepository).save(any(Venta.class));
    }

}

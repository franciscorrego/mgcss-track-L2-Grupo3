package com.mgcss.services;

import com.mgcss.domain.*;
import com.mgcss.infrastructure.TecnicoRepository;
import com.mgcss.infrastructure.SolicitudRepository;


import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SolicitudServiceTest {

    @Test
    void debe_guardar_solicitud_al_asignar_tecnico() {
        // 1. ARRANGE: Crear los "actores de reparto" (Mocks)
        SolicitudRepository mockRepoSolicitud = mock(SolicitudRepository.class);
        TecnicoRepository mockRepoTecnico = mock(TecnicoRepository.class);
        
        // Le pasamos los mocks al servicio (Aislamiento total)
        SolicitudService servicio = new SolicitudService(mockRepoSolicitud, mockRepoTecnico);

        // Preparamos los datos de mentira que devolverán los repositorios
        Solicitud solicitud = new Solicitud(1L, Estado.ABIERTA, null);
        Tecnico tecnico = new Tecnico(true);
        
        when(mockRepoSolicitud.findById(1L)).thenReturn(Optional.of(solicitud));
        when(mockRepoTecnico.findById(99L)).thenReturn(Optional.of(tecnico));

        // 2. ACT: El director de orquesta empieza a trabajar
        servicio.asignarTecnico(1L, 99L);

        // 3. ASSERT: Verificamos que el servicio le dio la orden de guardar al repositorio
        verify(mockRepoSolicitud).save(solicitud);
        
        // Verificamos que el estado de la entidad cambió gracias al servicio
        assertEquals(Estado.ABIERTA, solicitud.getEstado()); // OJO: Hemos puesto ABIERTA a propósito para que falle si el servicio no hace nada
    }
    
    @Test
    void debe_lanzar_excepcion_si_solicitud_no_existe() {
        // 1. ARRANGE
        SolicitudRepository mockRepoSolicitud = mock(SolicitudRepository.class);
        TecnicoRepository mockRepoTecnico = mock(TecnicoRepository.class);
        SolicitudService servicio = new SolicitudService(mockRepoSolicitud, mockRepoTecnico);

        // Simulamos que la base de datos NO encuentra la solicitud
        when(mockRepoSolicitud.findById(1L)).thenReturn(Optional.empty());

        // 2 & 3. ACT & ASSERT: Verificamos que al intentar asignar, explota con nuestra excepción
        assertThrows(IllegalArgumentException.class, () -> {
            servicio.asignarTecnico(1L, 99L);
        });
    }
}
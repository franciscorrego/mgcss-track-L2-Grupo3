package com.mgcss.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SolicitudTest {

    // --- REGLA 1: CERRAR SOLICITUD ---
    
    @Test
    void no_debe_permitir_cerrar_solicitud_si_no_esta_en_proceso() {
        Solicitud solicitud = new Solicitud(1L, Estado.ABIERTA, null); 
        assertThrows(IllegalStateException.class, solicitud::cerrar);
    }
    
    @Test
    void debe_poder_cerrar_solicitud_si_esta_en_proceso() {
        Solicitud solicitud = new Solicitud(1L, Estado.EN_PROCESO, null); 
        solicitud.cerrar();
        assertEquals(Estado.CERRADA, solicitud.getEstado()); // Camino feliz
    }

    // --- REGLA 2: TÉCNICO ACTIVO ---
    
    @Test
    void no_debe_permitir_asignar_tecnico_inactivo() {
        Solicitud solicitud = new Solicitud(1L, Estado.ABIERTA, null); 
        Tecnico tecnicoInactivo = new Tecnico(false);
        assertThrows(IllegalStateException.class, () -> {
            solicitud.asignarTecnico(tecnicoInactivo);
        });
    }

    @Test
    void debe_permitir_asignar_tecnico_activo() {
        // 1. ARRANGE
        Solicitud solicitud = new Solicitud(1L, Estado.ABIERTA, null); 
        Tecnico tecnicoActivo = new Tecnico(true);
        
        // 2. ACT
        solicitud.asignarTecnico(tecnicoActivo); 
        
        // 3. ASSERT (Comprobamos la regla de negocio real)
        assertEquals(Estado.EN_PROCESO, solicitud.getEstado());
    }

    // --- REGLA 3: NO TOCAR SOLICITUDES CERRADAS ---
    
    @Test
    void no_debe_permitir_asignar_tecnico_a_solicitud_cerrada() {
        Solicitud solicitud = new Solicitud(1L, Estado.CERRADA, null); 
        Tecnico tecnico = new Tecnico(true);
        assertThrows(IllegalStateException.class, () -> {
            solicitud.asignarTecnico(tecnico);
        });
    }
}
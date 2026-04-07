package com.mgcss.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SolicitudTest {

    @Test
    void no_debe_permitir_cerrar_solicitud_si_no_esta_en_proceso() {
        // Creamos una solicitud en estado ABIERTA
        Solicitud solicitud = new Solicitud(1L, Estado.ABIERTA, null); 

        // Intentamos cerrarla y verificamos que lanza la excepción esperada
        assertThrows(IllegalStateException.class, () -> {
            solicitud.cerrar();
        });
    }
    
    @Test
    void no_debe_permitir_asignar_tecnico_inactivo() {
        // Creamos la solicitud
        Solicitud solicitud = new Solicitud(1L, Estado.ABIERTA, null); 
        
        // Intentamos crear un técnico que esté inactivo (false)
        Tecnico tecnicoInactivo = new Tecnico(false);

        // Verificamos que al asignarlo, el dominio se protege lanzando error
        assertThrows(IllegalStateException.class, () -> {
            solicitud.asignarTecnico(tecnicoInactivo);
        });
    }
}

package com.mgcss.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SolicitudTest {

    @Test
    void no_debe_permitir_cerrar_solicitud_si_no_esta_en_proceso() {
        // Setup inicial: Creamos una solicitud en estado ABIERTA
        Solicitud solicitud = new Solicitud(1L, Estado.ABIERTA, null); 

        // Intentamos cerrarla y verificamos que lanza la excepción esperada
        assertThrows(IllegalStateException.class, () -> {
            solicitud.cerrar();
        });
    }
}

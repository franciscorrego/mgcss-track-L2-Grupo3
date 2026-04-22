package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import com.mgcss.domain.Estado;
import com.mgcss.infrastructure.persistence.JpaSolicitudRepository;
import com.mgcss.infrastructure.persistence.SolicitudEntity;

@DataJpaTest
@ActiveProfiles("test")
class SolicitudRepositoryIT {

    // 1. Esta clase sustituye al Main para el test
    @SpringBootConfiguration
    @EnableAutoConfiguration
    @EntityScan("com.mgcss.infrastructure.persistence") // Cambiar esto al paquete de  @Entity
    @EnableJpaRepositories("com.mgcss.infrastructure.persistence") // Cambiar esto al paquete de  Repos
    static class TestConfig { }

    @Autowired
    private JpaSolicitudRepository repository;

    @Test
    void debe_guardar_y_recuperar_entidad() {
        SolicitudEntity solicitud = new SolicitudEntity();
        solicitud.setEstado(Estado.ABIERTA);

        SolicitudEntity guardada = repository.save(solicitud);

        assertNotNull(guardada.getId());
        assertEquals(Estado.ABIERTA, repository.findById(guardada.getId()).get().getEstado());
    }
}
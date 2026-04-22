package integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.mgcss.domain.Estado;
import com.mgcss.infrastructure.persistence.JpaSolicitudRepository;
import com.mgcss.infrastructure.persistence.SolicitudEntity;

@DataJpaTest
@Tag("integration")
class SolicitudRepositoryIT {

    @Autowired
    private JpaSolicitudRepository repository;

    @Test
    void guarda_y_recupera_solicitud() {

        // Arrange
        SolicitudEntity entity = new SolicitudEntity();
        entity.setEstado(Estado.ABIERTA);

        // Act
        repository.save(entity);

        // Assert
        Optional<SolicitudEntity> result =
                repository.findById(entity.getId());

        assertTrue(result.isPresent());
    }
}

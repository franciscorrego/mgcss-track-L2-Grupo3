package com.mgcss.infrastructure;

import java.util.Optional;

import com.mgcss.domain.Solicitud;
import com.mgcss.infrastructure.persistence.SolicitudEntity;

public interface SolicitudRepository {
    Solicitud save(SolicitudEntity solicitud);
    Optional<SolicitudEntity> findById(Long id);
}
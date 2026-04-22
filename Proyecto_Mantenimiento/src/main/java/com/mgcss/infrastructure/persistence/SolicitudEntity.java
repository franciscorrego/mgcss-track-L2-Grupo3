package com.mgcss.infrastructure.persistence;

import java.time.LocalDateTime;

import com.mgcss.domain.Estado;
import com.mgcss.domain.Tecnico;

import jakarta.persistence.*;

@Entity
@Table(name = "solicitudes")
public class SolicitudEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Estado estado;

    private LocalDateTime fechaCreacion;

    public SolicitudEntity() {}

    public SolicitudEntity(Long id, Estado estado, LocalDateTime fechaCreacion) {
        this.id = id;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public void cerrar() {
        if (this.estado != Estado.EN_PROCESO) {
            throw new IllegalStateException("Solo solicitudes en proceso pueden cerrarse");
        }
        this.estado = Estado.CERRADA;
    }

    public void asignarTecnico(Tecnico tecnico) {
        if (this.estado == Estado.CERRADA) {
            throw new IllegalStateException("No se puede asignar un técnico a una solicitud cerrada");
        }

        if (!tecnico.isActivo()) {
            throw new IllegalStateException("Solo se puede asignar un técnico activo a una solicitud");
        }

        this.estado = Estado.EN_PROCESO;
    }
}
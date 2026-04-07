package com.mgcss.domain;

import java.time.LocalDateTime;

public class Solicitud {
    
    private Long id;
    private Estado estado; 
    private LocalDateTime fechaCreacion;

    public Solicitud() {
    }

    public Solicitud(Long id, Estado estado, LocalDateTime fechaCreacion) {
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
    
    //regla de negocio
    public void cerrar() {
        if (this.estado != Estado.EN_PROCESO) {
            throw new IllegalStateException("Solo solicitudes en proceso pueden cerrarse");
        }
        this.estado = Estado.CERRADA;
    }
    
    public void asignarTecnico(Tecnico tecnico) {
        if (!tecnico.isActivo()) {
            throw new IllegalStateException("Solo se puede asignar un técnico activo a una solicitud");
        }
    }
}
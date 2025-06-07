package com.gimnasio.gimnasio_backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rutinas_usuario")
public class RutinaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rutina_id")
    private Rutina rutina;

    @Column(name = "fecha_asignacion", nullable = false)
    private LocalDate fechaAsignacion;

    public RutinaUsuario() {}

    public RutinaUsuario(Usuario usuario, Rutina rutina, LocalDate fechaAsignacion) {
        this.usuario = usuario;
        this.rutina = rutina;
        this.fechaAsignacion = fechaAsignacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rutina getRutina() {
        return rutina;
    }

    public void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }
}

package com.gimnasio.gimnasio_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "maquinas")
public class Maquina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 255)
    private String imagenUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoMaquina estado;

    public enum EstadoMaquina {
        disponible, mantenimiento
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenUrl() {
    return imagenUrl;
    }
    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public EstadoMaquina getEstado() {
        return estado;
    }
    public void setEstado(EstadoMaquina estado) {
        this.estado = estado;
    }
}

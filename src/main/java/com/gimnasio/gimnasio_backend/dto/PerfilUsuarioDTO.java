package com.gimnasio.gimnasio_backend.dto;

public class PerfilUsuarioDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
    private String rol;
    private boolean membresiaActiva;

    public PerfilUsuarioDTO(Long id, String nombre, String correo, String telefono, String rol, boolean membresiaActiva) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.rol = rol;
        this.membresiaActiva = membresiaActiva;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public boolean isMembresiaActiva() { return membresiaActiva; }
    public void setMembresiaActiva(boolean membresiaActiva) { this.membresiaActiva = membresiaActiva; }
}

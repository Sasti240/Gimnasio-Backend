package com.gimnasio.gimnasio_backend.dto;

public class CambiarContrasenaRequest {
    private Long usuarioId;
    private String contrasenaActual;
    private String nuevaContrasena;

    public Long getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getContrasenaActual() {
        return contrasenaActual;
    }
    public void setContrasenaActual(String contrasenaActual) {
        this.contrasenaActual = contrasenaActual;
    }

    public String getNuevaContrasena() {
        return nuevaContrasena;
    }
    public void setNuevaContrasena(String nuevaContrasena) {
        this.nuevaContrasena = nuevaContrasena;
    }
}

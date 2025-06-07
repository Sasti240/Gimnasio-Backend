package com.gimnasio.gimnasio_backend.dto;

public class ConcurrenciaDTO {
    private long personasDentro;
    private long usuariosConMembresiaActiva;

    public ConcurrenciaDTO(long personasDentro, long usuariosConMembresiaActiva) {
        this.personasDentro = personasDentro;
        this.usuariosConMembresiaActiva = usuariosConMembresiaActiva;
    }

    public long getPersonasDentro() {
        return personasDentro;
    }

    public void setPersonasDentro(long personasDentro) {
        this.personasDentro = personasDentro;
    }

    public long getUsuariosConMembresiaActiva() {
        return usuariosConMembresiaActiva;
    }

    public void setUsuariosConMembresiaActiva(long usuariosConMembresiaActiva) {
        this.usuariosConMembresiaActiva = usuariosConMembresiaActiva;
    }
}

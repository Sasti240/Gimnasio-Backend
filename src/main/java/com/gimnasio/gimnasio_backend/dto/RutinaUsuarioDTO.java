package com.gimnasio.gimnasio_backend.dto;

public class RutinaUsuarioDTO {
    private Long usuarioId;
    private Long rutinaId;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getRutinaId() {
        return rutinaId;
    }

    public void setRutinaId(Long rutinaId) {
        this.rutinaId = rutinaId;
    }
}


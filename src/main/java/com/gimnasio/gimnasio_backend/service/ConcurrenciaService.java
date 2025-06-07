package com.gimnasio.gimnasio_backend.service;

import com.gimnasio.gimnasio_backend.dto.ConcurrenciaDTO;
import com.gimnasio.gimnasio_backend.repository.MembresiaRepository;
import com.gimnasio.gimnasio_backend.repository.RegistroAccesoRepository;
import org.springframework.stereotype.Service;

@Service
public class ConcurrenciaService {

    private final RegistroAccesoRepository registroAccesoRepository;
    private final MembresiaRepository membresiaRepository;

    public ConcurrenciaService(RegistroAccesoRepository registroAccesoRepository, MembresiaRepository membresiaRepository) {
        this.registroAccesoRepository = registroAccesoRepository;
        this.membresiaRepository = membresiaRepository;
    }

    public ConcurrenciaDTO obtenerConcurrenciaActual() {
        long personasDentro = registroAccesoRepository.countUsuariosDentro();
        long usuariosConMembresiaActiva = membresiaRepository.countMembresiasActivas();

        return new ConcurrenciaDTO(personasDentro, usuariosConMembresiaActiva);
    }
}

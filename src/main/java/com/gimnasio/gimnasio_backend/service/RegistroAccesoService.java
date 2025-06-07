package com.gimnasio.gimnasio_backend.service;

import com.gimnasio.gimnasio_backend.model.RegistroAcceso;
import com.gimnasio.gimnasio_backend.repository.RegistroAccesoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroAccesoService {

    private final RegistroAccesoRepository registroAccesoRepository;

    public RegistroAccesoService(RegistroAccesoRepository registroAccesoRepository) {
        this.registroAccesoRepository = registroAccesoRepository;
    }

    public RegistroAcceso crearRegistro(RegistroAcceso registroAcceso) {
        return registroAccesoRepository.save(registroAcceso);
    }

    public List<RegistroAcceso> obtenerTodos() {
        return registroAccesoRepository.findAll();
    }
}

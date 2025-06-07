package com.gimnasio.gimnasio_backend.service;

import com.gimnasio.gimnasio_backend.model.Rutina;
import com.gimnasio.gimnasio_backend.repository.RutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RutinaService {

    @Autowired
    private RutinaRepository rutinaRepository;

    public List<Rutina> listarTodas() {
        return rutinaRepository.findAll();
    }

    public Optional<Rutina> obtenerPorId(Long id) {
        return rutinaRepository.findById(id);
    }

    public Rutina crear(Rutina rutina) {
        return rutinaRepository.save(rutina);
    }

    public void eliminar(Long id) {
        rutinaRepository.deleteById(id);
    }
}

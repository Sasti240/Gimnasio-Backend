package com.gimnasio.gimnasio_backend.service;

import com.gimnasio.gimnasio_backend.model.Maquina;
import com.gimnasio.gimnasio_backend.repository.MaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaquinaService {

    @Autowired
    private MaquinaRepository maquinaRepository;

    public List<Maquina> obtenerTodas() {
        return maquinaRepository.findAll();
    }

    public Optional<Maquina> obtenerPorId(Long id) {
        return maquinaRepository.findById(id);
    }

    public Maquina crearMaquina(Maquina maquina) {
        return maquinaRepository.save(maquina);
    }

    public Maquina actualizarMaquina(Long id, Maquina maquinaActualizada) {
        return maquinaRepository.findById(id).map(maquina -> {
            maquina.setNombre(maquinaActualizada.getNombre());
            maquina.setDescripcion(maquinaActualizada.getDescripcion());
            maquina.setEstado(maquinaActualizada.getEstado());
            return maquinaRepository.save(maquina);
        }).orElse(null);
    }

    public boolean eliminarMaquina(Long id) {
        if (maquinaRepository.existsById(id)) {
            maquinaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

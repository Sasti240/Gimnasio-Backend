package com.gimnasio.gimnasio_backend.service;

import com.gimnasio.gimnasio_backend.dto.RutinaUsuarioDTO;
import com.gimnasio.gimnasio_backend.model.RutinaUsuario;
import com.gimnasio.gimnasio_backend.model.Usuario;
import com.gimnasio.gimnasio_backend.model.Rutina;
import com.gimnasio.gimnasio_backend.repository.RutinaUsuarioRepository;
import com.gimnasio.gimnasio_backend.repository.UsuarioRepository;
import com.gimnasio.gimnasio_backend.repository.RutinaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RutinaUsuarioService {

    @Autowired
    private RutinaUsuarioRepository rutinaUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RutinaRepository rutinaRepository;

    public List<RutinaUsuario> obtenerPorUsuario(Long usuarioId) {
        return rutinaUsuarioRepository.findByUsuario_Id(usuarioId);
    }

    public RutinaUsuario asignarRutina(RutinaUsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Rutina rutina = rutinaRepository.findById(dto.getRutinaId())
            .orElseThrow(() -> new IllegalArgumentException("Rutina no encontrada"));

        RutinaUsuario ru = new RutinaUsuario();
        ru.setUsuario(usuario);
        ru.setRutina(rutina);
        ru.setFechaAsignacion(LocalDate.now());

        return rutinaUsuarioRepository.save(ru);
    }

    public void eliminarRutinaAsignada(Long id) {
        Optional<RutinaUsuario> rutinaUsuario = rutinaUsuarioRepository.findById(id);
        if (rutinaUsuario.isEmpty()) {
            throw new IllegalStateException("La rutina asignada no existe");
        }
        rutinaUsuarioRepository.deleteById(id);
    }
}

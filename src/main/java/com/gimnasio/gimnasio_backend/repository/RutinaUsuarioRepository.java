package com.gimnasio.gimnasio_backend.repository;

import com.gimnasio.gimnasio_backend.model.RutinaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RutinaUsuarioRepository extends JpaRepository<RutinaUsuario, Long> {
    List<RutinaUsuario> findByUsuario_Id(Long usuarioId);
}

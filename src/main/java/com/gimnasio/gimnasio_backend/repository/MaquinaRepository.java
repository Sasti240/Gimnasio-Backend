package com.gimnasio.gimnasio_backend.repository;

import com.gimnasio.gimnasio_backend.model.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Long> {
}

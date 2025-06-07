package com.gimnasio.gimnasio_backend.repository;

import com.gimnasio.gimnasio_backend.model.RegistroAcceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroAccesoRepository extends JpaRepository<RegistroAcceso, Integer> {

    @Query(value = "SELECT COUNT(*) FROM ( " +
                   "SELECT usuario_id, SUM(CASE WHEN tipo = 'entrada' THEN 1 WHEN tipo = 'salida' THEN -1 ELSE 0 END) AS balance " +
                   "FROM registros_acceso GROUP BY usuario_id HAVING balance > 0) AS usuarios_dentro", nativeQuery = true)
    long countUsuariosDentro();

}


package com.gimnasio.gimnasio_backend.repository;

import com.gimnasio.gimnasio_backend.model.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MembresiaRepository extends JpaRepository<Membresia, Integer> {

    @Query("SELECT COUNT(m) FROM Membresia m WHERE m.estado = 'activa'")
    long countMembresiasActivas();

}
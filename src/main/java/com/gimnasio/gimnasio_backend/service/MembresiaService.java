package com.gimnasio.gimnasio_backend.service;

import com.gimnasio.gimnasio_backend.model.Membresia;
import com.gimnasio.gimnasio_backend.repository.MembresiaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembresiaService {

    @Autowired
    private MembresiaRepository membresiaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Membresia> listarTodas() {
        return membresiaRepository.findAll();
    }

    public Optional<Membresia> obtenerPorId(Integer id) {
        return membresiaRepository.findById(id);
    }

    public Membresia crear(Membresia membresia) {
        return membresiaRepository.save(membresia);
    }

    public Membresia actualizar(Integer id, Membresia membresiaActualizada) {
        Optional<Membresia> optional = membresiaRepository.findById(id);
        if (optional.isPresent()) {
            Membresia existente = optional.get();
            existente.setUsuarioId(membresiaActualizada.getUsuarioId());
            existente.setFechaInicio(membresiaActualizada.getFechaInicio());
            existente.setFechaFin(membresiaActualizada.getFechaFin());
            existente.setEstado(membresiaActualizada.getEstado());
            return membresiaRepository.save(existente);
        } else {
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (membresiaRepository.existsById(id)) {
            membresiaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Membresia> obtenerMembresiaActivaPorUsuarioId(Long usuarioId) {
        TypedQuery<Membresia> query = entityManager.createQuery(
            "SELECT m FROM Membresia m WHERE m.usuarioId = :usuarioId AND m.estado = 'activa'",
            Membresia.class
        );
        query.setParameter("usuarioId", usuarioId);
        query.setMaxResults(1);

        try {
            Membresia membresia = query.getSingleResult();
            return Optional.of(membresia);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
package com.gimnasio.gimnasio_backend.controller;

import com.gimnasio.gimnasio_backend.model.Membresia;
import com.gimnasio.gimnasio_backend.service.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membresias")
public class MembresiaController {

    @Autowired
    private MembresiaService membresiaService;

    @GetMapping
    public ResponseEntity<List<Membresia>> listarTodas() {
        return ResponseEntity.ok(membresiaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membresia> obtenerPorId(@PathVariable Integer id) {
        return membresiaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Membresia> crear(@RequestBody Membresia membresia) {
        return ResponseEntity.ok(membresiaService.crear(membresia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membresia> actualizar(@PathVariable Integer id, @RequestBody Membresia membresia) {
        Membresia actualizada = membresiaService.actualizar(id, membresia);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (membresiaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
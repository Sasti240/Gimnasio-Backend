package com.gimnasio.gimnasio_backend.controller;

import com.gimnasio.gimnasio_backend.model.Maquina;
import com.gimnasio.gimnasio_backend.service.MaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maquinas")
public class MaquinaController {

    @Autowired
    private MaquinaService maquinaService;

    @GetMapping
    public List<Maquina> obtenerTodas() {
        return maquinaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maquina> obtenerPorId(@PathVariable Long id) {
        Optional<Maquina> maquinaOpt = maquinaService.obtenerPorId(id);
        return maquinaOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Maquina> crearMaquina(@RequestBody Maquina maquina) {
        Maquina nuevaMaquina = maquinaService.crearMaquina(maquina);
        return ResponseEntity.ok(nuevaMaquina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maquina> actualizarMaquina(@PathVariable Long id, @RequestBody Maquina maquina) {
        Maquina actualizada = maquinaService.actualizarMaquina(id, maquina);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMaquina(@PathVariable Long id) {
        boolean eliminado = maquinaService.eliminarMaquina(id);
        if (eliminado) {
            return ResponseEntity.ok().body("Máquina eliminada correctamente");
        }
        return ResponseEntity.notFound().build();
    }
}

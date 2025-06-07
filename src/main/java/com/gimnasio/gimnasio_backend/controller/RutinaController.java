package com.gimnasio.gimnasio_backend.controller;

import com.gimnasio.gimnasio_backend.model.Rutina;
import com.gimnasio.gimnasio_backend.service.RutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutinas")
public class RutinaController {

    @Autowired
    private RutinaService rutinaService;

    @GetMapping
    public List<Rutina> listarTodas() {
        return rutinaService.listarTodas();
    }

    @PostMapping
    public Rutina crear(@RequestBody Rutina rutina) {
        return rutinaService.crear(rutina);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        rutinaService.eliminar(id);
    }
}

package com.gimnasio.gimnasio_backend.controller;

import com.gimnasio.gimnasio_backend.model.RegistroAcceso;
import com.gimnasio.gimnasio_backend.service.RegistroAccesoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
public class RegistroAccesoController {

    private final RegistroAccesoService registroAccesoService;

    public RegistroAccesoController(RegistroAccesoService registroAccesoService) {
        this.registroAccesoService = registroAccesoService;
    }

    @PostMapping
    public ResponseEntity<RegistroAcceso> crearRegistro(@RequestBody RegistroAcceso registroAcceso) {
        RegistroAcceso creado = registroAccesoService.crearRegistro(registroAcceso);
        return ResponseEntity.ok(creado);
    }

    @GetMapping
    public ResponseEntity<List<RegistroAcceso>> listarRegistros() {
        return ResponseEntity.ok(registroAccesoService.obtenerTodos());
    }
}

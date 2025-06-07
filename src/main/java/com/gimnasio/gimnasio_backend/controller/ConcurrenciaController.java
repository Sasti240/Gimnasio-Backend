package com.gimnasio.gimnasio_backend.controller;

import com.gimnasio.gimnasio_backend.dto.ConcurrenciaDTO;
import com.gimnasio.gimnasio_backend.service.ConcurrenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/concurrencia")
public class ConcurrenciaController {

    private final ConcurrenciaService concurrenciaService;

    public ConcurrenciaController(ConcurrenciaService concurrenciaService) {
        this.concurrenciaService = concurrenciaService;
    }

    @GetMapping
    public ResponseEntity<ConcurrenciaDTO> getConcurrencia() {
        ConcurrenciaDTO concurrencia = concurrenciaService.obtenerConcurrenciaActual();
        return ResponseEntity.ok(concurrencia);
    }
}

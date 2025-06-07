package com.gimnasio.gimnasio_backend.controller;

import com.gimnasio.gimnasio_backend.dto.RutinaUsuarioDTO;
import com.gimnasio.gimnasio_backend.model.RutinaUsuario;
import com.gimnasio.gimnasio_backend.service.RutinaUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/rutinas-usuario")
public class RutinaUsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(RutinaUsuarioController.class);

    @Autowired
    private RutinaUsuarioService rutinaUsuarioService;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<RutinaUsuario>> obtenerRutinasDeUsuario(@PathVariable Long usuarioId) {
        try {
            List<RutinaUsuario> lista = rutinaUsuarioService.obtenerPorUsuario(usuarioId);
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            logger.error("Error obteniendo rutinas del usuario {}: {}", usuarioId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> asignarRutina(@RequestBody RutinaUsuarioDTO dto) {
        try {
            logger.info("Intentando asignar rutina. UsuarioId: {}, RutinaId: {}", 
                       dto.getUsuarioId(), dto.getRutinaId());
            
            if (dto.getUsuarioId() == null || dto.getRutinaId() == null) {
                return ResponseEntity.badRequest().body("Usuario ID y Rutina ID son requeridos");
            }
            
            RutinaUsuario resultado = rutinaUsuarioService.asignarRutina(dto);
            logger.info("Rutina asignada exitosamente con ID: {}", resultado.getId());
            return ResponseEntity.ok(resultado);
            
        } catch (IllegalArgumentException e) {
            logger.error("Error de validación: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error interno asignando rutina: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRutinaAsignada(@PathVariable Long id) {
        try {
            rutinaUsuarioService.eliminarRutinaAsignada(id);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error eliminando rutina asignada {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor");
        }
    }
}
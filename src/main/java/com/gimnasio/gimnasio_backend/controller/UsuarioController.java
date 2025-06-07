package com.gimnasio.gimnasio_backend.controller;

import com.gimnasio.gimnasio_backend.dto.CambiarContrasenaRequest;
import com.gimnasio.gimnasio_backend.dto.LoginRequest;
import com.gimnasio.gimnasio_backend.dto.LoginResponse;
import com.gimnasio.gimnasio_backend.dto.PerfilUsuarioDTO;
import com.gimnasio.gimnasio_backend.model.Usuario;
import com.gimnasio.gimnasio_backend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = usuarioService.obtenerPorId(id);
        return usuarioOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/perfil")
    public ResponseEntity<PerfilUsuarioDTO> obtenerPerfil(@PathVariable Long id) {
        return usuarioService.obtenerPerfilConMembresiaActiva(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario) {
        if (usuarioService.correoExiste(usuario.getCorreo())) {
            return ResponseEntity.badRequest().body("El correo ya está registrado");
        }

        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        Usuario actualizado = usuarioService.actualizarUsuario(id, usuario);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = usuarioService.eliminarUsuario(id);
        if (eliminado) {
            return ResponseEntity.ok().body("Usuario eliminado correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioService.login(loginRequest.getCorreo(), loginRequest.getContrasena());
        if (usuarioOpt.isPresent()) {
            Usuario u = usuarioOpt.get();
            LoginResponse response = new LoginResponse(
                    u.getId(),
                    u.getNombre(),
                    u.getCorreo(),
                    u.getTelefono(),
                    u.getRol().name()
            );
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas.");
        }
    }

    @PutMapping("/{id}/cambiar-contrasena")
    public ResponseEntity<String> cambiarContrasena(
            @PathVariable Long id,
            @RequestBody CambiarContrasenaRequest request) {
        if (!id.equals(request.getUsuarioId())) {
            return ResponseEntity.badRequest().body("El ID del usuario no coincide");
        }
        boolean actualizado = usuarioService.cambiarContrasena(
                id,
                request.getContrasenaActual(),
                request.getNuevaContrasena()
        );
        if (actualizado) {
            return ResponseEntity.ok("Contraseña actualizada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contraseña actual incorrecta o usuario no encontrado");
        }
    }
}




package com.gimnasio.gimnasio_backend.service;

import com.gimnasio.gimnasio_backend.dto.PerfilUsuarioDTO;
import com.gimnasio.gimnasio_backend.model.Usuario;
import com.gimnasio.gimnasio_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MembresiaService membresiaService;

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> obtenerPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public boolean correoExiste(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setCorreo(usuarioActualizado.getCorreo());
            usuario.setContrasena(usuarioActualizado.getContrasena());
            usuario.setTelefono(usuarioActualizado.getTelefono());
            usuario.setRol(usuarioActualizado.getRol());
            return usuarioRepository.save(usuario);
        }).orElse(null);
    }

    public boolean eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Usuario> login(String correo, String contrasena) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getContrasena().equals(contrasena)) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    public Optional<PerfilUsuarioDTO> obtenerPerfilConMembresiaActiva(Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) return Optional.empty();

        Usuario usuario = usuarioOpt.get();

        boolean tieneMembresiaActiva = membresiaService
                .obtenerMembresiaActivaPorUsuarioId(id)
                .isPresent();

        PerfilUsuarioDTO dto = new PerfilUsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getTelefono(),
                usuario.getRol().name(),
                tieneMembresiaActiva
        );

        return Optional.of(dto);
    }

    public boolean cambiarContrasena(Long id, String contrasenaActual, String nuevaContrasena) {
    Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
    if (usuarioOpt.isPresent()) {
        Usuario usuario = usuarioOpt.get();
        if (usuario.getContrasena().equals(contrasenaActual)) {
            usuario.setContrasena(nuevaContrasena);
            usuarioRepository.save(usuario);
            return true;
        }
    }
    return false;
}
}

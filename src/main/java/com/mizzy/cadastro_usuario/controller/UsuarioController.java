package com.mizzy.cadastro_usuario.controller;

import com.mizzy.cadastro_usuario.services.UsuarioService;
import com.mizzy.cadastro_usuario.services.dto.UsuarioRequestDTO;
import com.mizzy.cadastro_usuario.services.dto.UsuarioResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;

    }
    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody UsuarioRequestDTO usuario) {
        usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarUsuarioPorEmail(@RequestParam String email) {
        usuarioService.deletarUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarUsuarioPorId(
            @PathVariable Integer id,
            @RequestBody UsuarioRequestDTO usuario) {

        usuarioService.atualizarUsuarioPorId(id, usuario);
        return ResponseEntity.ok().build();
    }
}
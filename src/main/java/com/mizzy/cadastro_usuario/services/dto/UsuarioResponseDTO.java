package com.mizzy.cadastro_usuario.services.dto;

public record UsuarioResponseDTO(
        Integer id,
        String nome,
        String email
) {}
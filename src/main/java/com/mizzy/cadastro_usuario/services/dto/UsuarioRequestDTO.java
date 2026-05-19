package com.mizzy.cadastro_usuario.services.dto;

public record UsuarioRequestDTO(
        String nome,
        String email,
        String senha
) {}

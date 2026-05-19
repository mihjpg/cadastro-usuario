package com.mizzy.cadastro_usuario.services;

import com.mizzy.cadastro_usuario.services.dto.UsuarioRequestDTO;
import com.mizzy.cadastro_usuario.services.dto.UsuarioResponseDTO;
import com.mizzy.cadastro_usuario.infrastructure.entitys.Usuario;
import com.mizzy.cadastro_usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    /// salvar
    public UsuarioResponseDTO salverUsuario(UsuarioRequestDTO dto) {

        Usuario usuario = Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senha(dto.senha())
                .build();
        Usuario salvo = repository.saveAndFlush(usuario);

        return  new UsuarioResponseDTO(salvo.getId(), salvo.getNome(), salvo.getEmail());
    }

    /// buscar por email
    public Usuario buscarUsuarioPorEmail(String email){
        return repository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Usuário não encontrado"
                        )
                );
    }

    /// deletar
    public void deletarUsuarioPorEmail(String email){

        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Usuário não encontrado"
                        )
                );

        repository.delete(usuario);
    }

    ///  atualizar por id
    public void atualizarUsuarioPorId(Integer id, Usuario usuario){

        Usuario usuarioEntity = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Usuário não encontrado"
                        )
                );

        Usuario usuarioAtualizado = Usuario.builder()
                .id(usuarioEntity.getId())
                .nome(usuario.getNome() != null ? usuario.getNome() : usuarioEntity.getNome())
                .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioEntity.getEmail())
                .senha(usuario.getSenha() != null ? usuario.getSenha() : usuarioEntity.getSenha())
                .build();

        repository.saveAndFlush(usuarioAtualizado);
    }
}
package com.mizzy.cadastro_usuario.business;

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
    public void salvarUsuario(Usuario usuario){
        repository.saveAndFlush(usuario);
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
                .build();

        repository.saveAndFlush(usuarioAtualizado);
    }
}

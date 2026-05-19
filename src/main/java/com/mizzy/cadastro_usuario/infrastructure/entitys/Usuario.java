package com.mizzy.cadastro_usuario.infrastructure.entitys;

import jakarta.persistence.*;

@Table(name = "usuario")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "nome")
    private String nome;

    @Column(name = "senha")
    private String senha;

    // @NoArgsConstructor
    public Usuario() {}

    // @AllArgsConstructor
    public Usuario(Integer id, String email, String nome, String senha) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    // @Getter
    public Integer getId() { return id; }
    public String getEmail() { return email; }
    public String getNome() { return nome; }
    public String getSenha() { return senha; }

    // @Setter
    public void setId(Integer id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setNome(String nome) { this.nome = nome; }
    public void setSenha(String senha) { this.senha = senha; }

    // @Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Integer id;
        private String email;
        private String nome;
        private String senha;

        public Builder id(Integer id) { this.id = id; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder nome(String nome) { this.nome = nome; return this; }
        public Builder senha(String senha) { this.senha = senha; return this; }

        public Usuario build() { return new Usuario(id, email, nome, senha); }
    }
}
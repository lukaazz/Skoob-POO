package modelo.usuario;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private String nome;
    private String email;
    private String senha;

    private static final long serialVersionUID = 1L;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public boolean senhaCerta(String senhaEntrada) {
        return senha.equals(senhaEntrada);
    }
}
package modelo.usuario;

import java.util.ArrayList;
import modelo.livro.*;

public abstract class Usuario {
    private String nome;
    private String email;
    private String senha;
    private Estante estante;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.estante = new Estante();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public ArrayList<Livro> getEstante() {
        return estante.getLivros();
    }

    public void adicionarLivroEstante(Livro livro) {
        getEstante().add(livro);
    }

    public void removerLivroEstante(Livro livro) {
        getEstante().remove(livro);
    }
}
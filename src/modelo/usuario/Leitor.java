package modelo.usuario;

import excecoes.LivroJaAdicionadoException;
import excecoes.LivroNaoAdicionadoException;
import java.util.Map;
import modelo.livro.Livro;

public class Leitor extends Usuario {
    private final Estante estante;

    private static final long serialVersionUID = 1L;

    public Leitor(String nome, String email, String senha) {
        super(nome, email, senha);
        this.estante = new Estante();
    }

    public Map<Livro, StatusLeitura> getEstante() {
        return estante.getLivros();
    }

    public void adicionarLivroEstante(Livro livro, StatusLeitura status) throws LivroJaAdicionadoException {
        estante.adicionarLivro(livro, status);
    }

    public void removerLivroEstante(Livro livro) throws LivroNaoAdicionadoException {
        estante.removerLivro(livro);
    }
}
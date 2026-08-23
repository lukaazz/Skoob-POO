package modelo.usuario;

import controle.GerenciadorArquivos;
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

    public void adicionarLivroEstante(Livro livro, StatusLeitura status, GerenciadorArquivos gerenciadorArquivos) {
        estante.adicionarLivro(livro, status);
        gerenciadorArquivos.salvarLeitores();
    }

    public void removerLivroEstante(Livro livro, GerenciadorArquivos gerenciadorArquivos) {
        estante.removerLivro(livro);
        gerenciadorArquivos.salvarLeitores();
    }
}
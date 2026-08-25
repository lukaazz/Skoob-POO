package modelo.usuario;

import excecoes.LivroJaAdicionadoException;
import excecoes.LivroNaoAdicionadoException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import modelo.livro.*;

public class Estante implements Serializable {
    /* mapa: chave eh status de leitura e o objeto (unico) eh o livro */
    private Map<Livro, StatusLeitura> livros;

    private static final long serialVersionUID = 1L;

    public Estante() {
        this.livros = new HashMap<>();
    }

    public void adicionarLivro(Livro livro, StatusLeitura status) throws LivroJaAdicionadoException {

        if(livros.containsKey(livro)) {
            throw new LivroJaAdicionadoException(livro.getTitulo());
        } else {
            livros.put(livro, status);
        }
    }

    public void removerLivro(Livro livro) throws LivroNaoAdicionadoException {

        if(!livros.containsKey(livro)) {
            throw new LivroNaoAdicionadoException();
        } else {
            livros.remove(livro);
        }
    }

    public Map<Livro, StatusLeitura> getLivros() {
        return livros;
    }

    public void mudarStatusLeitura(Livro livro, StatusLeitura novoStatus) throws LivroNaoAdicionadoException {

            if(livros.containsKey(livro)) {
                //vai substituir o status antigo pelo novo (o objeto nao muda, apenas o valor associado a ele)
                livros.put(livro, novoStatus);
            } else {
                throw new LivroNaoAdicionadoException();
            }
    }

    public boolean contemLivro(Livro livro) {
        return livros.containsKey(livro);
    }
}
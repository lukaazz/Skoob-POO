package modelo.usuario;

import java.util.ArrayList;
import modelo.livro.*;

public class Estante {
    private ArrayList<Livro> livros;

    public Estante() {
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {

        if (livros.contains(livro)) {
            throw new LivroJaGuargado(livro);
        } else {
            livros.add(livro);
        }
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro);
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }
}
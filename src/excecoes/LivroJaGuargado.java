package excecoes;

import modelo.livro.Livro;

public class LivroJaGuargado extends Exception {
    public LivroJaGuargado(Livro livro) {
        super("Livro" + livro.getTitulo() + "ja esta guardado na estante." );
    }
}
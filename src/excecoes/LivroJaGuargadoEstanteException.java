package excecoes;

import modelo.livro.Livro;

public class LivroJaGuargadoEstanteException extends Exception {
    public LivroJaGuargadoEstanteException(Livro livro) {
        super("Livro" + livro.getTitulo() + "ja esta guardado na estante." );
    }
}
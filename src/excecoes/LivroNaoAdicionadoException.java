package excecoes;

public class LivroNaoAdicionadoException extends Exception {
    public LivroNaoAdicionadoException(String titulo) {
        super("O livro \"" + titulo + "\" nao esta presente na estante.");
    }
}

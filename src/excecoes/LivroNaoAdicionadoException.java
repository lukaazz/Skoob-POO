package excecoes;

public class LivroNaoAdicionadoException extends Exception {
    public LivroNaoAdicionadoException() {
        super("O livro nao esta presente na estante.");
    }
}

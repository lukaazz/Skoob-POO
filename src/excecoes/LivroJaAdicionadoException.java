package excecoes;

public class LivroJaAdicionadoException extends Exception {
    public LivroJaAdicionadoException(String titulo) {
        super("O livro \"" + titulo + "\" já foi adicionado à estante.");
    }
}
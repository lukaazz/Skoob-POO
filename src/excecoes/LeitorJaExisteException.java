package excecoes;

public class LeitorJaExisteException extends Exception {
    public LeitorJaExisteException() {
        super("Leitor ja existe!");
    }
}
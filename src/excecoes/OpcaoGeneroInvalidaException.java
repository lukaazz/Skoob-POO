package excecoes;

public class OpcaoGeneroInvalidaException extends Exception {
    public OpcaoGeneroInvalidaException() {
        super("Essa opção não é um gênero válido!");
    }
}
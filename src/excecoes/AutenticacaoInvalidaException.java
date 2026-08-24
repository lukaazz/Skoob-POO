package excecoes;

public class AutenticacaoInvalidaException extends Exception{
    public AutenticacaoInvalidaException() {
        super("Autenticação inválida");
    }
}

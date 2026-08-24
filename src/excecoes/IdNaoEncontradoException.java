package excecoes;

public class IdNaoEncontradoException extends Exception {
    public IdNaoEncontradoException() {
        super("Id não encontrado no catálogo!");
    }
}
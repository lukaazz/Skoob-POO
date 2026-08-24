package excecoes;

public class LivroJaCadastradoCatalogoException extends Exception{
    public LivroJaCadastradoCatalogoException() {
        super("Livro já cadastrado no catálogo!");
    }
}

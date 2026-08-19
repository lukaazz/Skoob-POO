public class LivroJaGuargado extends RuntimeException {
    public LivroJaGuargado(Livro livro) {
        super("Livro" + livro.getTitulo() + "ja esta guardado na estante." );
    }
}
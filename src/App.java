import modelo.livro.*;

public class App {

    public static void main(String[] args)
     {
        
        Biblioteca biblioteca = new Biblioteca();

        Livro novoCadastro = biblioteca.cadastrarLivro();

        System.out.println(novoCadastro.getAutor());
    }
}
// PARA COMPILAR: javac -d bin -sourcepath src src/App.java
// PARA EXECUTAR: java -cp bin App

import modelo.livro.*;

public class App {

    public static void main(String[] args)
     {
        
        Biblioteca biblioteca = new Biblioteca();

        Livro novoCadastro = biblioteca.cadastrarLivro();
        biblioteca.exibirLivro(novoCadastro);
        
    }
}
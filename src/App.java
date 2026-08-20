// PARA COMPILAR: javac -d bin -sourcepath src src/App.java
// PARA EXECUTAR: java -cp bin App

import java.util.Scanner;
import modelo.livro.*;

public class App {

    public static void main(String[] args)
     {
        Scanner sc = new Scanner(System.in);
        
        Cadastro cadastro = new Cadastro();
        Biblioteca biblioteca = new Biblioteca();

        Livro novoCadastro = cadastro.cadastrarLivro(sc);
        biblioteca.exibirLivro(novoCadastro);
    }
}
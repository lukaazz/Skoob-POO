package modelo.livro;

import java.util.HashMap;
import java.util.Map;
import modelo.FuncoesFormatacao;


public class Biblioteca {

    private Map<String, Livro> catalogo;


    public Biblioteca() {
        this.catalogo = new HashMap<>();
    }

    
    public void adicionarLivro(Livro livro) {
        catalogo.put(livro.getTitulo(), livro);
    }


    public void removerLivro(Livro livro) {
        catalogo.remove(livro.getTitulo());
    }


    public void exibirTodosLivros() {

        for (Livro livro : catalogo.values()) {
            exibirLivro(livro);
        }
    }

    public void removerLivro(String titulo) {
        catalogo.remove(titulo);
    }


    public void exibirLivro(Livro livro) {

        FuncoesFormatacao.inserirDivisor();

        System.out.printf(" TÍTULO  : %s%n", livro.getTitulo());
        System.out.printf(" AUTOR   : %s%n", livro.getAutor());

        FuncoesFormatacao.inserirLinha();

        System.out.printf(" SINOPSE : %s%n", livro.getSinopse());

        FuncoesFormatacao.inserirDivisor();
    }

}

package modelo.livro;

import java.util.HashMap;
import java.util.Map;
import modelo.FuncoesFormatacao;


public class Biblioteca {

    private final Map<Integer, Livro> catalogo; // final impede que catalogo seja reinstanciado em um metod que não seja o contrutor
    private int proximoId = 1;
 
    
    public Biblioteca() {
        this.catalogo = new HashMap<>();
    }


    public void adicionarLivro(Livro livro) {
        livro.setId(proximoId);
        catalogo.put(livro.getId(), livro);
        this.proximoId++;
    }


    public Livro buscarLivro(int id) {
        return catalogo.get(id);
    }


    public void removerLivro(Livro livro) {
        catalogo.remove(livro.getId());
    }


    public void exibirLivro(Livro livro) {

        FuncoesFormatacao.inserirDivisor();

        System.out.printf(" TÍTULO  : %s%n", livro.getTitulo());
        System.out.printf(" AUTOR   : %s%n", livro.getAutor());

        FuncoesFormatacao.inserirLinha();

        System.out.printf(" SINOPSE : %s%n", livro.getSinopse());

        FuncoesFormatacao.inserirDivisor();
    }


    public void exibirTodosLivros() {

        for (Livro livro : catalogo.values()) {
            exibirLivro(livro);
        }
    }

}

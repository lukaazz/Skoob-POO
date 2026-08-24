package modelo.livro;

import java.util.HashMap;
import java.util.Map;
import modelo.FuncoesFormatacao;


public class Biblioteca {

    private final Map<Integer, Livro> catalogo; // final impede que catalogo seja reinstanciado em um metodo que não seja o construtor
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


    public void removerLivro(int id) {
        catalogo.remove(id);
    }


    public void exibirFichaLivro(Livro livro) {

        FuncoesFormatacao.inserirDivisor();

        System.out.printf(" TÍTULO    : %s%n", livro.getTitulo());
        System.out.printf(" AUTOR     : %s%n", livro.getAutor());
        System.out.printf(" GÊNERO(S) : %s%n", livro.getGeneros());

        FuncoesFormatacao.inserirLinha();

        System.out.printf(" SINOPSE : %s%n", livro.getSinopse());

        FuncoesFormatacao.inserirDivisor();

        if(livro instanceof LivroFisico livroFisico) {
            System.out.printf(" PÁGINAS : %s%n", livroFisico.getQuantidadePaginas());

        } else if (livro instanceof AudioBook audioBook) {
            System.out.printf(" TEMPO : %s%n", audioBook.getTempo());

        } else if (livro instanceof Ebook ebook) {
            System.out.printf(" PALAVRAS : %s%n", ebook.getQuantidadePalavras());
        }

        FuncoesFormatacao.inserirDivisor();
    }


    public void exibirResumoLivro(Livro livro) {

        FuncoesFormatacao.inserirDivisor();

        System.out.printf(" TÍTULO  : %s%n", livro.getTitulo());
        System.out.printf(" AUTOR   : %s%n", livro.getAutor());

        FuncoesFormatacao.inserirLinha();

        System.out.printf(" SINOPSE : %s%n", livro.getSinopse());

        FuncoesFormatacao.inserirDivisor();
    }


    public void exibirTodosLivros() {

        for (Livro livro : catalogo.values()) {
            exibirResumoLivro(livro);
        }
    }

}

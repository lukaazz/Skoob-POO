package modelo.livro;

import java.util.HashMap;
import java.util.Map;


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

        if(livro instanceof LivroFisico livroFisico) {
            System.out.print(livroFisico.toString());

        } else if (livro instanceof AudioBook audioBook) {
            System.out.print(audioBook.toString());

        } else if (livro instanceof Ebook ebook) {
            System.out.print(ebook.toString());
        }

    }


    public void exibirResumoLivro(Livro livro) {

        System.out.print(livro.resumo());
    }


    public void exibirTodosLivros() {

        for (Livro livro : catalogo.values()) {
            exibirResumoLivro(livro);
        }
    }

}

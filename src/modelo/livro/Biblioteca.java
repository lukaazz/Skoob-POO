package modelo.livro;

import excecoes.IdNaoEncontradoException;
import excecoes.LivroJaCadastradoCatalogoException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// a
public class Biblioteca implements Serializable {

    private static final long serialVersionUID = 1L;

    
    private final Map<Integer, Livro> catalogo; // final impede que catalogo seja reinstanciado em um metodo que não seja o construtor
    private int proximoId = 1;

    public Biblioteca() {
        this.catalogo = new HashMap<>();
    }

    public void adicionarLivro(Livro livro) throws LivroJaCadastradoCatalogoException {
        boolean jaTemNoCatalogo = false;

        for (Livro livroCadastrado : catalogo.values()) {
            if (livro.comparaDadosIguais(livroCadastrado)) {
                jaTemNoCatalogo = true;
                break;
            }
        }

        if (jaTemNoCatalogo) {
            throw new LivroJaCadastradoCatalogoException();
        }

        livro.setId(proximoId);
        catalogo.put(livro.getId(), livro);
        this.proximoId++;
    }

    public Livro buscarLivro(int id) throws IdNaoEncontradoException {
        if (!catalogo.containsKey(id)) {
            throw new IdNaoEncontradoException();
        }
        return catalogo.get(id);
    }

    public void removerLivro(int id) throws IdNaoEncontradoException {
        buscarLivro(id);
        catalogo.remove(id);
    }

    public void exibirFichaLivro(int id) throws IdNaoEncontradoException {
        Livro livro = buscarLivro(id);
        System.out.print(livro.toString());
    }

    public void exibirResumoLivro(int id) throws IdNaoEncontradoException {
        Livro livro = buscarLivro(id);
        System.out.print(livro.resumo());
    }

    public void exibirTodosLivros() throws IdNaoEncontradoException {

        for (Livro livro : catalogo.values()) {
            exibirResumoLivro(livro.getId());
        }
    }

   
// Retorna a referência do HashMap do catálogo
    public Map<Integer, Livro> getCatalogo() {
        return this.catalogo;
    }

// Permite atualizar o catálogo ao carregar os dados salvos do arquivo
    public void carregarCatalogo(Map<Integer, Livro> novosLivros) {
        this.catalogo.clear();
        this.catalogo.putAll(novosLivros);

        // Atualiza o próximo ID para não sobrescrever IDs existentes
        this.proximoId = novosLivros.keySet().stream().mapToInt(Integer::intValue).max().orElse(0) + 1;
    }

}

package modelo.livro;

import java.util.Set;

public class Ebook extends Livro {

    private String quantidadePalavras;

    public Ebook(String titulo, String autor, String sinopse, Set<Genero> generos, String quantidadePalavras) {
        super(titulo, autor, sinopse, generos);
        this.quantidadePalavras = quantidadePalavras;
    }

    public void setQuantidadePalavras(String quantidadePalavras) {
        this.quantidadePalavras = quantidadePalavras;
    }

    public String getQuantidadePalavras() {
        return quantidadePalavras;
    }

}

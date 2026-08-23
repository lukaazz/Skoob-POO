package modelo.livro;

import java.util.Set;

public class LivroFisico extends Livro {

    private String quantidadePaginas;

    public LivroFisico(String titulo, String autor, String sinopse, Set<Genero> generos, String quantidadePaginas) {
        super(titulo, autor, sinopse, generos);
        this.quantidadePaginas = quantidadePaginas;
    }

    public void setQuantidadePaginas(String quantidadePaginas) {
        this.quantidadePaginas = quantidadePaginas;
    }

    public String getQuantidadePaginas() {
        return quantidadePaginas;
    }

}

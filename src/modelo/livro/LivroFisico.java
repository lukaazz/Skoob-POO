package modelo.livro;

public class LivroFisico extends Livro {

    private String quantidadePaginas;

    public LivroFisico(String titulo, String autor, String sinopse, String quantidadePaginas) {
        super(titulo, autor, sinopse);
        this.quantidadePaginas = quantidadePaginas;
    }

    public void setQuantidadePaginas(String quantidadePaginas) {
        this.quantidadePaginas = quantidadePaginas;
    }

    public String getQuantidadePaginas() {
        return quantidadePaginas;
    }

}

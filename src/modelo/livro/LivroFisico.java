package livro;

public class LivroFisico extends Livro {

    private String quantidadePaginas;

    public LivroFisico(String titulo, String autor, String sinopse, int id, String quantidadePaginas) {
        super(titulo, autor, sinopse, id);
        this.quantidadePaginas = quantidadePaginas;
    }

    public void setQuantidadePaginas(String quantidadePaginas) {
        this.quantidadePaginas = quantidadePaginas;
    }

    public String getQuantidadePaginas() {
        return quantidadePaginas;
    }

}

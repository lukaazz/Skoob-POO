package livro;

public class Ebook extends Livro {

    private String quantidadePalavras;

    public Ebook(String titulo, String autor, String sinopse, int id, String quantidadePalavras) {
        super(titulo, autor, sinopse, id);
        this.quantidadePalavras = quantidadePalavras;
    }

    public void setQuantidadePalavras(String quantidadePalavras) {
        this.quantidadePalavras = quantidadePalavras;
    }

    public String getQuantidadePalavras() {
        return quantidadePalavras;
    }

}

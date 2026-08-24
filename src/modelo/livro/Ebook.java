package modelo.livro;

import java.util.Set;
import modelo.FuncoesFormatacao;

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

    @Override
    public String toString() {
        
        String resultado = super.toString();
        resultado += String.format(" PALAVRAS : %s%n", getQuantidadePalavras());
        resultado += FuncoesFormatacao.inserirDivisor();

        return resultado;
    }

}

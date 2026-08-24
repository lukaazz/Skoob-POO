package modelo.livro;

import java.util.Set;
import modelo.FuncoesFormatacao;

public class AudioBook extends Livro {

    private String tempo;

    public AudioBook(String titulo, String autor, String sinopse, Set<Genero> generos, String tempo) {
        super(titulo, autor, sinopse, generos);
        this.tempo = tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getTempo() {
        return tempo;
    }

    @Override
    public String toString() {
        
        String resultado = super.toString();
        resultado += String.format(" TEMPO : %s%n", getTempo());
        resultado += FuncoesFormatacao.inserirDivisor();

        return resultado;
    }

}

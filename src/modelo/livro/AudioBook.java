package modelo.livro;

public class AudioBook extends Livro {

    private String tempo;

    public AudioBook(String titulo, String autor, String sinopse, String tempo) {
        super(titulo, autor, sinopse);
        this.tempo = tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getTempo() {
        return tempo;
    }

}

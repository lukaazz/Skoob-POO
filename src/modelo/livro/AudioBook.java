package modelo.livro;

public class AudioBook extends Livro {

    private String tempo;

    public AudioBook(String titulo, String autor, String sinopse, int id, String tempo) {
        super(titulo, autor, sinopse, id);
        this.tempo = tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getTempo() {
        return tempo;
    }

}
//lucas giovani cu lixo feio
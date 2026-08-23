package modelo.livro;

public abstract class Livro {

    protected String titulo;
    protected String autor;
    protected String sinopse;
    protected int id;   

    public Livro(String titulo, String autor, String sinopse) {
        this.titulo = titulo;
        this.autor = autor;
        this.sinopse = sinopse;
    }


    // toString -> sobrescrever nas filhas (polimorfismo)


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

}

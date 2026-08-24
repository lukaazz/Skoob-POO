package modelo.livro;

import java.util.Set;

public abstract class Livro {

    protected String titulo;
    protected String autor;
    protected String sinopse;
    protected int id;   
    protected Set<Genero> generos;

    public Livro(String titulo, String autor, String sinopse, Set<Genero> generos) {
        this.titulo = titulo;
        this.autor = autor;
        this.sinopse = sinopse;
        this.generos = generos;

    }


    // toString -> sobrescrever nas filhas (polimorfismo)


    public String getGeneros() {

        Genero[] todosGeneros = new Genero[this.generos.size()];

        this.generos.toArray(todosGeneros);

        StringBuilder listaGeneros = new StringBuilder();
        
        for(int i = 0; i < todosGeneros.length; i++) {
            
                if(i == todosGeneros.length - 1) {
                    listaGeneros.append(todosGeneros[i] + ".");
                } else {
                    listaGeneros.append(todosGeneros[i] + ", ");
                }
            }
        return listaGeneros.toString();
    }

    

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

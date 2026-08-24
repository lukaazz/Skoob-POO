package modelo.livro;

import excecoes.NotaInvalidaException;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import modelo.FuncoesFormatacao;

public abstract class Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String titulo;
    protected String autor;
    protected String sinopse;
    protected int id;
    protected Set<Genero> generos;
    protected double nota;
    protected int quantidadeAvaliacoes;


    public Livro(String titulo, String autor, String sinopse, Set<Genero> generos) {
        this.titulo = titulo;
        this.autor = autor;
        this.sinopse = sinopse;
        this.generos = generos;

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

    public String getGeneros() {

        Genero[] todosGeneros = new Genero[this.generos.size()];

        this.generos.toArray(todosGeneros);

        StringBuilder listaGeneros = new StringBuilder();

        for (int i = 0; i < todosGeneros.length; i++) {
            listaGeneros.append(todosGeneros[i]);
            if (i == todosGeneros.length - 1) {
                listaGeneros.append(".");
            } else {
                listaGeneros.append(", ");
            }
        }
        return listaGeneros.toString();
    }

    public int getQuantidadeAvaliacoes() {
        return quantidadeAvaliacoes;
    }

    public double getNota() {
        return nota;
    }

    // ver como nota tá funcionando com a luisa
    public void recalcularNota(double novaNota) throws NotaInvalidaException {

        if (novaNota >= 0 && novaNota <= 5) {
            this.quantidadeAvaliacoes++;
            this.nota = (this.nota * (this.quantidadeAvaliacoes - 1) + novaNota) / this.quantidadeAvaliacoes;
        } else {
            throw new NotaInvalidaException();
        }
    }

    public String resumo() {

        String resultado = "";

        resultado += FuncoesFormatacao.inserirDivisor();
        resultado += String.format(" TÍTULO    : %s%n", getTitulo());
        resultado += String.format(" AUTOR     : %s%n", getAutor());
        resultado += FuncoesFormatacao.inserirLinha();
        resultado += String.format(" SINOPSE   : %s%n", getSinopse());

        return resultado;
    }

    @Override
    public String toString() {

        String resultado = resumo();

        resultado += String.format(" GÊNERO(S) : %s%n", getGeneros());
        resultado += String.format(" NOTA      : %s%n", getNota());

        return resultado;
    }

    public boolean comparaDadosIguais(Livro livro2) {
        return (this.autor.equals(livro2.getAutor())  && this.titulo.equals(livro2.getTitulo()));    
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Livro outro = (Livro) obj;
        return this.id == outro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

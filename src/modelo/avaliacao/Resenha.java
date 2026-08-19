package modelo.avaliacao;

import excecoes.*;
import java.time.LocalDate;
import modelo.livro.*;
import modelo.usuario.*;


public class Resenha{
    private final Usuario usuario;
    private final String texto;
    private final int nota;
    private final LocalDate data;
    private final Livro livro;

    public Resenha(Usuario usuario, String texto, int nota, LocalDate data, Livro livro) throws AvaliacaoInvalidaException{
        if(nota>5 || nota<0) {
            throw new AvaliacaoInvalidaException("Nota inválida");
        }
        this.usuario = usuario;
        this.texto = texto;
        this.nota = nota;
        this.data = data;
        this.livro = livro;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public String getTexto(){
        return texto;
    }

    public int getNota(){
        return nota;
    }

    public LocalDate getData(){
        return data;
    }

    public Livro getLivro(){
        return livro;
    }
}   

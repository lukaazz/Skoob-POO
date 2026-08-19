package modelo.avaliacao;

import excecoes.*;
import modelo.usuario.Usuario;

public interface Avaliavel {
    void avaliar(Usuario autor, int nota, String texto) throws LivroJaAvaliadoException, AvaliacaoInvalidaException;
    double calcularMediaNotas();
}

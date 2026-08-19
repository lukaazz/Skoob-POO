package avaliacao;

import excecoes.*;
import usuario.Usuario;

public interface Avaliavel {
    void avaliar(Usuario autor, int nota, String texto) throws LivroJaAvaliadoException, AvaliacaoInvalidaException;
    double calcularMediaNotas();
}

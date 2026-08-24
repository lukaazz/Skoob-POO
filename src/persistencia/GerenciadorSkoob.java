package persistencia;
import modelo.livro.Livro;
import modelo.usuario.Leitor;
import modelo.usuario.Usuario;
import modelo.usuario.Administrador;
import modelo.avaliacao.Resenha;

import java.util.*;

import excecoes.AutenticacaoInvalidaException;

public class GerenciadorSkoob {
    private static GerenciadorSkoob instancia;

    private Map<String, Livro> catalogo;
    private Map<String, Leitor> leitores;
    private Map<String, Administrador> admins;
    private List<Resenha> resenhas;

    private GerenciadorSkoob(){
        catalogo = new HashMap<>();
        leitores = new HashMap<>();
        admins = new HashMap<>();
        resenhas = new ArrayList<>();
    }

    public static GerenciadorSkoob getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorSkoob();
        }
        return instancia;
    }

    public Usuario autenticar(String email, String senha) {

        try {
            if(leitores.containsKey(email) || admins.containsKey(email)) {

                if((admins.get(email).senhaCerta(senha))) {
                    System.out.println("Logando...");
                    return admins.get(email);

                } else if ((leitores.get(email).senhaCerta(email))) {
                    System.out.println("Logando...");
                    return leitores.get(email);

                } else {
                    throw new AutenticacaoInvalidaException();
                }
            } else {
                throw new AutenticacaoInvalidaException();
            }

        } catch (AutenticacaoInvalidaException e) {
            e.getMessage();
        }

    
    }
}

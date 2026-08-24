package persistencia;

import excecoes.AdministradorJaExisteException;
import excecoes.AutenticacaoInvalidaException;
import excecoes.LeitorJaExisteException;
import java.util.*;
import modelo.avaliacao.Resenha;
import modelo.livro.Livro;
import modelo.usuario.Administrador;
import modelo.usuario.Leitor;
import modelo.usuario.Usuario;

public class GerenciadorSkoob {
    private static GerenciadorSkoob instancia;

    private Map<String, Livro> catalogo;
    private Map<String, Leitor> leitores;
    private Map<String, Administrador> admins;
    private List<Resenha> resenhas;

    private GerenciadorSkoob() {
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

    public Usuario autenticar(String email, String senha) throws AutenticacaoInvalidaException {

        if (leitores.containsKey(email)) {
            if (leitores.get(email).senhaCerta(senha)) {
                System.out.println("Logando...");
                return leitores.get(email);
            } else {
                throw new AutenticacaoInvalidaException();
            }

        } else if (admins.containsKey(email)) {
            if (admins.get(email).senhaCerta(senha)) {
                System.out.println("Logando...");
                return admins.get(email);
            } else {
                throw new AutenticacaoInvalidaException();
            }

        } else {
            throw new AutenticacaoInvalidaException();
        }
    }

    public void cadastrarLeitor(Leitor leitor) throws LeitorJaExisteException {

        if(leitores.containsKey(leitor.getEmail())) {
            throw new LeitorJaExisteException();
        } else {
            leitores.put(leitor.getEmail(), leitor);
        }
    }

    public void cadastrarAdministrador(Administrador admin) throws AdministradorJaExisteException {
        if(admins.containsKey(admin.getEmail())) {
            throw new AdministradorJaExisteException();
        } else {
            admins.put(admin.getEmail(), admin);
        }
    }

    public Livro buscarLivro(String isbn) {
        return catalogo.get(isbn);
    }
}

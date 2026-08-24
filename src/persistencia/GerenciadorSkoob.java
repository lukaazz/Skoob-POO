package persistencia;

import modelo.livro.Livro;
import modelo.usuario.Leitor;
import modelo.usuario.Usuario;
import modelo.usuario.Administrador;
import modelo.avaliacao.Resenha;

import java.util.*;

import excecoes.AdministradorJaExisteException;
import excecoes.AutenticacaoInvalidaException;
import excecoes.LeitorJaExisteException;

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
        // TODO: checa se já existe no mapa "leitores" pelo email do leitor
        // TODO: se existir, throw
        // TODO: se não existir, adiciona no mapa
    }

    public void cadastrarAdministrador(Administrador admin) throws AdministradorJaExisteException {
        // (mesma lógica, só que pro mapa "admins")
    }

    public Livro buscarLivro(String isbn) {
        return catalogo.get(isbn);
    }
}

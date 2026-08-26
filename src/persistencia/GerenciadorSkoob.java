package persistencia;

import controle.GerenciadorArquivos;
import excecoes.AdministradorJaExisteException;
import excecoes.AutenticacaoInvalidaException;
import excecoes.IdNaoEncontradoException;
import excecoes.LeitorJaExisteException;
import excecoes.LivroJaCadastradoCatalogoException;
import modelo.FuncoesFormatacao;
import modelo.livro.Biblioteca;
import modelo.livro.Livro;
import modelo.usuario.Administrador;
import modelo.usuario.Leitor;
import modelo.usuario.Usuario;

public class GerenciadorSkoob {

    private static GerenciadorSkoob instancia;

    private final GerenciadorArquivos arquivos;
    private final Biblioteca biblioteca;

    private GerenciadorSkoob() {
        this.arquivos = new GerenciadorArquivos();
        this.biblioteca = arquivos.getBiblioteca();
    }

    public static GerenciadorSkoob getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorSkoob();
        }
        return instancia;
    }

    public Usuario autenticar(String email, String senha) throws AutenticacaoInvalidaException {
        Usuario usuario = arquivos.autenticar(email, senha);
        System.out.print(FuncoesFormatacao.exibirMensagem("Logando..."));
        return usuario;
    }

    public void cadastrarLeitor(Leitor leitor) throws LeitorJaExisteException {
        arquivos.adicionarLeitor(leitor);   
    }

    public void cadastrarAdministrador(Administrador admin) throws AdministradorJaExisteException {
        arquivos.adicionarAdministrador(admin);
    }

    public void cadastrarLivro(Livro livro) throws LivroJaCadastradoCatalogoException {
        biblioteca.adicionarLivro(livro);
        arquivos.salvarCatalogo();
    }

    public Livro buscarLivro(int id) throws IdNaoEncontradoException {
        return biblioteca.buscarLivro(id);
    }

    public void removerLivro(int id) throws IdNaoEncontradoException {
        biblioteca.removerLivro(id);
        arquivos.salvarCatalogo();
    }

    // Lista o resumo de todos os livros cadastrados no catálogo
    public void exibirTodosLivros() {
        biblioteca.exibirTodosLivros();
    }

    // Exibe a ficha completa (todos os dados) de um livro específico
    public void exibirFichaLivro(int id) throws IdNaoEncontradoException {
        biblioteca.exibirFichaLivro(id);
    }

}
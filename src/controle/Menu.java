package controle;

import java.util.Scanner;

import excecoes.AutenticacaoInvalidaException;
import modelo.usuario.Administrador;
import modelo.usuario.Leitor;
import modelo.usuario.Usuario;
import persistencia.GerenciadorSkoob;

public class Menu {

    private final Scanner sc;
    GerenciadorSkoob gerenciador = GerenciadorSkoob.getInstancia();

    public Menu(Scanner sc) {
        this.sc = sc;
    }

    public void iniciar() {
        int op = 0;
        while (op != 3) {
            System.out.println("MENU\n1 - Login\n2 - Cadastrar\n3 - Sair\n");
            op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    cadastrarUsuario();
                    break;
                case 3:
                    break;
            }
        }
    }

    private void fazerLogin() {
        System.out.println("Inserir email: ");
        String email = sc.nextLine();
        System.out.println("Inserir senha: ");
        String senha = sc.nextLine();

        try {
            Usuario usuarioLogado = gerenciador.autenticar(email, senha);
            if (usuarioLogado instanceof Leitor) {
                exibirMenuLeitor((Leitor) usuarioLogado);
            }
            if (usuarioLogado instanceof Administrador) {
                exibirMenuAdmin((Administrador) usuarioLogado);
            }

        } catch (AutenticacaoInvalidaException e) {
            System.out.println("Login ou senha inválidos.");
        }
    }

    private void cadastrarUsuario() {
        System.out.println("1 - Leitor | 2 - Administrador\n");
        int op = sc.nextInt();
        sc.nextLine();
        System.out.println("Insira nome: ");
        String nome = sc.nextLine();
        System.out.println("Insira email: ");
        String email = sc.nextLine();
        System.out.println("Insira senha: ");
        String senha = sc.nextLine();
        if(op == 1){
            Leitor l = new Leitor(nome, email, senha);
        }
        else if(op == 2){
            Administrador a = new Administrador(nome, email, senha);
        }
    }

    // Opções exclusivas do leitor: resenha, adicionar na estante, mudar status
    private void exibirMenuLeitor(Leitor leitor) {
        // TODO: loop com as opções do leitor
        // TODO: cada opção chama um método específico (ex: fazerResenha(), etc.)
    }

    // Opções exclusivas do admin: adicionar/remover livro do catálogo
    private void exibirMenuAdmin(Administrador admin) {
        // TODO: loop com as opções do admin
    }
}
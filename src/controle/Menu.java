package controle;

import java.time.LocalDate;
import java.util.Scanner;

import excecoes.*;
import modelo.livro.Livro;
import modelo.usuario.Administrador;
import modelo.usuario.Leitor;
import modelo.usuario.StatusLeitura;
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
        /*-------------------- ARRUMAR -------------------- */
        System.out.println("1 - Leitor | 2 - Administrador\n");
        int op = sc.nextInt();
        sc.nextLine();
        System.out.println("Insira nome: ");
        String nome = sc.nextLine();
        System.out.println("Insira email: ");
        String email = sc.nextLine();
        System.out.println("Insira senha: ");
        String senha = sc.nextLine();
        if (op == 1) {
            Leitor l = new Leitor(nome, email, senha);
        } else if (op == 2) {
            Administrador a = new Administrador(nome, email, senha);
        }
    }

    private void exibirMenuLeitor(Leitor leitor) {
        int op = 0;
        int op2 = 0;

        while (op != 5) {
            System.out.println(
                    "1 - Adicionar livro a estante\n2 - Remover livro da estante\n3 - Mudar status livro\n4 - Fazer resenha\n5 - Sair\n");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Inserir ID do livro: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consumir quebra de linha

                    Livro livro = gerenciador.buscarLivro(id);
                    if (livro == null) {
                        System.out.println("Livro não encontrado no catálogo.");
                    } else {

                        System.out.println("Escolha o status de leitura: 1-Quero ler | 2-Lendo | 3-Lido | 4-Abandonado");
                        op2 = sc.nextInt();

                        try {
                            switch (op2) {
                                case 1:
                                    leitor.adicionarLivroEstante(livro, StatusLeitura.QUERO_LER);
                                    System.out.println("Livro adicionado à estante!");
                                    break;
                                case 2:
                                    leitor.adicionarLivroEstante(livro, StatusLeitura.LENDO);
                                    System.out.println("Livro adicionado à estante!");
                                    break;
                                case 3:
                                    leitor.adicionarLivroEstante(livro, StatusLeitura.LIDO);
                                    System.out.println("Livro adicionado à estante!");
                                    break;
                                case 4:
                                    leitor.adicionarLivroEstante(livro, StatusLeitura.ABANDONADO);
                                    System.out.println("Livro adicionado à estante!");
                                    break;
                                default:
                                    System.out.println("Opcao invalida. Livro nao adicionado.");
                            }

                        } catch (LivroJaAdicionadoException e) {
                            System.out.println("Livro ja esta presente na estante.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Inserir ID do livro: ");
                    int idR = sc.nextInt();
                    sc.nextLine(); // consumir quebra de linha
                    Livro livroR = gerenciador.buscarLivro(idR);

                    if (livroR == null) {
                        System.out.println("Livro não encontrado no catálogo.");
                    } else {
                        leitor.removerLivroEstante(livroR);
                        System.out.println("Livro removido da estante!");
                    }
                    break;
                case 3:
                    System.out.println("Inserir ID do livro: ");
                    int idMS = sc.nextInt();
                    sc.nextLine(); // consumir quebra de linha

                    try {
                        Livro livroMS = gerenciador.buscarLivro(idMS);

                        System.out.println("Novo status: 1-Quero ler | 2-Lendo | 3-Lido | 4-Abandonado");
                        int opStatus = Integer.parseInt(sc.nextLine());

                        StatusLeitura status;
                        switch (opStatus) {
                            case 1:
                                status = StatusLeitura.QUERO_LER;
                                break;
                            case 2:
                                status = StatusLeitura.LENDO;
                                break;
                            case 3:
                                status = StatusLeitura.LIDO;
                                break;
                            case 4:
                                status = StatusLeitura.ABANDONADO;
                                break;
                            default:
                                status = StatusLeitura.QUERO_LER;
                                break;
                        }
                        leitor.mudarStatusLeitura(livroMS, status);
                        System.out.println("Status modificado!");

                    } catch (LivroNaoAdicionadoException e) {
                        e.getMessage();
                    }
                    break;

                case 4:
                    System.out.println("Inserir ID do livro: ");
                    int idAv = Integer.parseInt(sc.nextLine());

                    try {
                        Livro livroAv = gerenciador.buscarLivro(idAv);

                        System.out.println("Digite sua resenha: ");
                        String texto = sc.nextLine();

                        System.out.println("Digite a nota (0 a 5): ");
                        int nota = Integer.parseInt(sc.nextLine());

                        LocalDate data = LocalDate.now();

                        livroAv.avaliar(nota, texto); //tem que fazer o avaliar no livro
                        System.out.println("Resenha registrada!");

                    } catch (LivroNaoAdicionadoException e || AvaliacaoInvalidaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    break;
            }
        }
    }

    // Opções exclusivas do admin: adicionar/remover livro do catálogo
    private void exibirMenuAdmin(Administrador admin) {
        // TODO: loop com as opções do admin
    }
}
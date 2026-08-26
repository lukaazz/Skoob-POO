package controle;

import excecoes.*;
import java.time.LocalDate;
import java.util.Scanner;
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

            try {
                Leitor l = new Leitor(nome, email, senha);
                gerenciador.cadastrarLeitor(l);
                System.out.println("Leitor cadastrado com sucesso.");

            } catch (LeitorJaExisteException e) {
                System.out.println("Leitor já cadastrado!");
            }

        } else if (op == 2) {

            try {
                Administrador a = new Administrador(nome, email, senha);
                gerenciador.cadastrarAdministrador(a);
                System.out.println("Administrador cadastrado com sucesso.");

            } catch (AdministradorJaExisteException e) {
                System.out.println("Administrador já cadastrado!");
            }

        }
    }

    private void exibirMenuLeitor(Leitor leitor) {
        int op = 0;
        int op2 = 0;

        while (op != 5) {
            System.out.println(
                    "1 - Adicionar livro a estante\n2 - Remover livro da estante\n3 - Mudar status livro\n4 - Fazer resenha\n5 - Sair\n");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.println("Inserir ID do livro: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consumir quebra de linha

                    try {
                        Livro livro = gerenciador.buscarLivro(id);

                        System.out.println("Escolha o status de leitura: 1-Quero ler | 2-Lendo | 3-Lido | 4-Abandonado");
                        op2 = sc.nextInt();

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

                    } catch (IdNaoEncontradoException | LivroJaAdicionadoException e) {
                        System.out.println("Livro ja esta presente na estante.");
                    }

                    break;
                case 2:
                    System.out.println("Inserir ID do livro: ");
                    int idR = sc.nextInt();
                    sc.nextLine(); // consumir quebra de linha

                    try {
                        Livro livroR = gerenciador.buscarLivro(idR);

                        leitor.removerLivroEstante(livroR);
                        System.out.println("Livro removido da estante!");

                    } catch (LivroNaoAdicionadoException | IdNaoEncontradoException e) {
                        System.out.println(e.getMessage());
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
                        leitor.getEstante().mudarStatusLeitura(livroMS, status);
                        System.out.println("Status modificado!");

                    } catch (LivroNaoAdicionadoException | IdNaoEncontradoException e) {
                        System.out.println(e.getMessage());
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

                        leitor.resenharLivro(livroAv, texto, nota);
                        System.out.println("Resenha registrada!");

                    } catch (IdNaoEncontradoException | LivroNaoAdicionadoException | AvaliacaoInvalidaException e) {
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
        int op = 0;

        while (op != 3) {
            System.out.println("\n--- MENU DO ADMINISTRADOR ---");
            System.out.println("1 - Adicionar livro ao catálogo");
            System.out.println("2 - Remover livro do catálogo");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            op = sc.nextInt();
            sc.nextLine(); // consumir quebra de linha

            switch (op) {
                case 1:
                    try {
                        // Utiliza a classe Cadastro para receber os inputs e construir o Livro
                        modelo.livro.Cadastro menuCadastro = new modelo.livro.Cadastro();
                        Livro novoLivro = menuCadastro.cadastrarLivro(sc);

                        // Chama o gerenciador para adicionar o livro e salvar no arquivo .dat
                        gerenciador.cadastrarLivro(novoLivro);
                        System.out.println("Livro adicionado ao catálogo com sucesso!");

                    } catch (LivroJaCadastradoCatalogoException e) {
                        System.out.println("Erro: Este livro já está cadastrado no catálogo.");
                    } catch (Exception e) {
                        System.out.println("Erro durante o cadastro: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Inserir ID do livro a ser removido: ");
                    int idRemover = sc.nextInt();
                    sc.nextLine(); // consumir quebra de linha

                    try {
                        // Chama o gerenciador para remover o livro da biblioteca e atualizar o arquivo .dat
                        gerenciador.removerLivro(idRemover);
                        System.out.println("Livro removido do catálogo com sucesso!");

                    } catch (IdNaoEncontradoException e) {
                        System.out.println("Erro: Não foi encontrado um livro com este ID no catálogo.");
                    }
                    break;

                case 3:
                    System.out.println("Encerrando a sessão do administrador...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}

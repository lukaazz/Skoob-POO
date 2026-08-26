package controle;

import excecoes.*;
import java.time.LocalDate;
import java.util.Scanner;
import modelo.FuncoesFormatacao;
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
            System.out.print(FuncoesFormatacao.exibirMenu("MENU PRINCIPAL", "Login", "Cadastrar", "Sair"));
            System.out.print(FuncoesFormatacao.solicitarCampo("Escolha uma opção"));
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
                    System.out.print(FuncoesFormatacao.exibirMensagem("Encerrando o programa..."));
                    break;
                default:
                    System.out.print(FuncoesFormatacao.exibirErro("Opção inválida."));
                    break;
            }
        }
    }

    private void fazerLogin() {
        System.out.print(FuncoesFormatacao.exibirCabecalho("LOGIN"));
        System.out.println();
        System.out.print(FuncoesFormatacao.solicitarCampo("Email"));
        String email = sc.nextLine();
        System.out.print(FuncoesFormatacao.solicitarCampo("Senha"));
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
            System.out.print(FuncoesFormatacao.exibirErro("Login ou senha inválidos."));
        }
    }

    private void cadastrarUsuario() {
        System.out.print(FuncoesFormatacao.exibirMenu("CADASTRO DE USUÁRIO", "Leitor", "Administrador"));
        System.out.print(FuncoesFormatacao.solicitarCampo("Escolha uma opção"));
        int op = sc.nextInt();
        sc.nextLine();
        System.out.print(FuncoesFormatacao.solicitarCampo("Nome"));
        String nome = sc.nextLine();
        System.out.print(FuncoesFormatacao.solicitarCampo("Email"));
        String email = sc.nextLine();
        System.out.print(FuncoesFormatacao.solicitarCampo("Senha"));
        String senha = sc.nextLine();

        if (op == 1) {

            try {
                Leitor l = new Leitor(nome, email, senha);
                gerenciador.cadastrarLeitor(l);
                System.out.print(FuncoesFormatacao.exibirMensagem("Leitor cadastrado com sucesso."));

            } catch (LeitorJaExisteException e) {
                System.out.print(FuncoesFormatacao.exibirErro("Leitor já cadastrado!"));
            }

        } else if (op == 2) {

            try {
                Administrador a = new Administrador(nome, email, senha);
                gerenciador.cadastrarAdministrador(a);
                System.out.print(FuncoesFormatacao.exibirMensagem("Administrador cadastrado com sucesso."));

            } catch (AdministradorJaExisteException e) {
                System.out.print(FuncoesFormatacao.exibirErro("Administrador já cadastrado!"));
            }

        } else {
            System.out.print(FuncoesFormatacao.exibirErro("Opção inválida."));
        }
    }

    private void exibirMenuLeitor(Leitor leitor) {
        int op = 0;
        int op2 = 0;

        while (op != 5) {
            System.out.print(FuncoesFormatacao.exibirMenu("MENU DO LEITOR",
                    "Adicionar livro à estante",
                    "Remover livro da estante",
                    "Mudar status do livro",
                    "Fazer resenha",
                    "Sair"));
            System.out.print(FuncoesFormatacao.solicitarCampo("Escolha uma opção"));
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    gerenciador.exibirTodosLivros();
                    System.out.print(FuncoesFormatacao.solicitarCampo("ID do livro"));
                    int id = sc.nextInt();
                    sc.nextLine(); // consumir quebra de linha

                    try {
                        Livro livro = gerenciador.buscarLivro(id);

                        System.out.print(FuncoesFormatacao.exibirMenu("STATUS DE LEITURA",
                                "Quero ler", "Lendo", "Lido", "Abandonado"));
                        System.out.print(FuncoesFormatacao.solicitarCampo("Escolha uma opção"));
                        op2 = sc.nextInt();
                        sc.nextLine();

                        switch (op2) {
                            case 1:
                                leitor.adicionarLivroEstante(livro, StatusLeitura.QUERO_LER);
                                System.out.print(FuncoesFormatacao.exibirMensagem("Livro adicionado à estante!"));
                                break;
                            case 2:
                                leitor.adicionarLivroEstante(livro, StatusLeitura.LENDO);
                                System.out.print(FuncoesFormatacao.exibirMensagem("Livro adicionado à estante!"));
                                break;
                            case 3:
                                leitor.adicionarLivroEstante(livro, StatusLeitura.LIDO);
                                System.out.print(FuncoesFormatacao.exibirMensagem("Livro adicionado à estante!"));
                                break;
                            case 4:
                                leitor.adicionarLivroEstante(livro, StatusLeitura.ABANDONADO);
                                System.out.print(FuncoesFormatacao.exibirMensagem("Livro adicionado à estante!"));
                                break;
                            default:
                                System.out.print(FuncoesFormatacao.exibirErro("Opção inválida. Livro não adicionado."));
                        }

                    } catch (IdNaoEncontradoException | LivroJaAdicionadoException e) {
                        System.out.print(FuncoesFormatacao.exibirErro("Livro já está presente na estante."));
                    }

                    break;
                case 2:
                    gerenciador.exibirTodosLivros();
                    System.out.print(FuncoesFormatacao.solicitarCampo("ID do livro"));
                    int idR = sc.nextInt();
                    sc.nextLine(); // consumir quebra de linha

                    try {
                        Livro livroR = gerenciador.buscarLivro(idR);

                        leitor.removerLivroEstante(livroR);
                        System.out.print(FuncoesFormatacao.exibirMensagem("Livro removido da estante!"));

                    } catch (LivroNaoAdicionadoException | IdNaoEncontradoException e) {
                        System.out.print(FuncoesFormatacao.exibirErro(e.getMessage()));
                    }

                    break;
                case 3:
                    gerenciador.exibirTodosLivros();
                    System.out.print(FuncoesFormatacao.solicitarCampo("ID do livro"));
                    int idMS = sc.nextInt();
                    sc.nextLine(); // consumir quebra de linha

                    try {
                        Livro livroMS = gerenciador.buscarLivro(idMS);

                        System.out.print(FuncoesFormatacao.exibirMenu("NOVO STATUS",
                                "Quero ler", "Lendo", "Lido", "Abandonado"));
                        System.out.print(FuncoesFormatacao.solicitarCampo("Escolha uma opção"));
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
                        System.out.print(FuncoesFormatacao.exibirMensagem("Status modificado!"));

                    } catch (LivroNaoAdicionadoException | IdNaoEncontradoException e) {
                        System.out.print(FuncoesFormatacao.exibirErro(e.getMessage()));
                    }
                    break;

                case 4:
                    gerenciador.exibirTodosLivros();
                    System.out.print(FuncoesFormatacao.solicitarCampo("ID do livro"));
                    int idAv = Integer.parseInt(sc.nextLine());

                    try {
                        Livro livroAv = gerenciador.buscarLivro(idAv);

                        System.out.print(FuncoesFormatacao.solicitarCampo("Sua resenha"));
                        String texto = sc.nextLine();

                        System.out.print(FuncoesFormatacao.solicitarCampo("Nota (0 a 5)"));
                        int nota = Integer.parseInt(sc.nextLine());

                        LocalDate data = LocalDate.now();

                        leitor.resenharLivro(livroAv, texto, nota);
                        System.out.print(FuncoesFormatacao.exibirMensagem("Resenha registrada!"));

                    } catch (IdNaoEncontradoException | LivroNaoAdicionadoException | AvaliacaoInvalidaException e) {
                        System.out.print(FuncoesFormatacao.exibirErro(e.getMessage()));
                    }
                    break;
                case 5:
                    System.out.print(FuncoesFormatacao.exibirMensagem("Encerrando a sessão do leitor..."));
                    break;
                default:
                    System.out.print(FuncoesFormatacao.exibirErro("Opção inválida."));
                    break;
            }
        }
    }

    // Opções exclusivas do admin: adicionar/remover livro do catálogo
    private void exibirMenuAdmin(Administrador admin) {
        int op = 0;

        while (op != 3) {
            System.out.print(FuncoesFormatacao.exibirMenu("MENU DO ADMINISTRADOR",
                    "Adicionar livro ao catálogo",
                    "Remover livro do catálogo",
                    "Sair"));
            System.out.print(FuncoesFormatacao.solicitarCampo("Escolha uma opção"));

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
                        System.out.print(FuncoesFormatacao.exibirMensagem("Livro adicionado ao catálogo com sucesso!"));

                    } catch (LivroJaCadastradoCatalogoException e) {
                        System.out.print(FuncoesFormatacao.exibirErro("Este livro já está cadastrado no catálogo."));
                    } catch (Exception e) {
                        System.out.print(FuncoesFormatacao.exibirErro("Erro durante o cadastro: " + e.getMessage()));
                    }
                    break;

                case 2:
                    gerenciador.exibirTodosLivros();
                    System.out.print(FuncoesFormatacao.solicitarCampo("ID do livro a remover"));
                    int idRemover = sc.nextInt();
                    sc.nextLine(); // consumir quebra de linha

                    try {
                        // Chama o gerenciador para remover o livro da biblioteca e atualizar o arquivo .dat
                        gerenciador.removerLivro(idRemover);
                        System.out.print(FuncoesFormatacao.exibirMensagem("Livro removido do catálogo com sucesso!"));

                    } catch (IdNaoEncontradoException e) {
                        System.out.print(FuncoesFormatacao.exibirErro("Não foi encontrado um livro com este ID no catálogo."));
                    }
                    break;

                case 3:
                    System.out.print(FuncoesFormatacao.exibirMensagem("Encerrando a sessão do administrador..."));
                    break;

                default:
                    System.out.print(FuncoesFormatacao.exibirErro("Opção inválida."));
                    break;
            }
        }
    }
}
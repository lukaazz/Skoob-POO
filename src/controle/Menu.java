package controle;

import java.util.Scanner;

public class Menu {

    private final Scanner sc;
    private final GerenciadorArquivos gerenciador;

    public Menu(Scanner sc) {
        this.sc = sc;
    }

    // Loop principal: 1-Login, 2-Cadastrar, 3-Sair
    public void iniciar() {
        int op;
        while(op != 3){
            System.out.println("MENU\n1 - Login\n2 - Cadastrar\n3 - Sair\n");
            op = sc.nextInt();
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

    // Autentica e direciona pro menu certo (leitor ou admin)
    private void fazerLogin() {
        // TODO: chamar o método de autenticação do GerenciadorSkoob
        // TODO: se deu certo, usar instanceof pra decidir:
        //         exibirMenuLeitor(...) ou exibirMenuAdmin(...)
        // TODO: se falhou, avisar o usuário (pensa: exception ou mensagem simples?)
        System.out.println("Inserir senha: ");
        String senha = sc.nextLine();

    }

    // Cria um novo usuário (leitor ou admin?)
    private void cadastrarUsuario() {
        // TODO: pedir os dados necessários pelo sc
        // TODO: perguntar se o cadastro é de LeitorComum ou Administrador
        //       (ou será que só existe cadastro de leitor, e admin é fixo?
        //        pensa nisso — decisão de design sua)
        // TODO: registrar no GerenciadorSkoob
    }

    // Opções exclusivas do leitor: resenha, adicionar na estante, mudar status
    private void exibirMenuLeitor(/* tipo do parâmetro? */ leitor) {
        // TODO: loop com as opções do leitor
        // TODO: cada opção chama um método específico (ex: fazerResenha(), etc.)
    }

    // Opções exclusivas do admin: adicionar/remover livro do catálogo
    private void exibirMenuAdmin(/* tipo do parâmetro? */ admin) {
        // TODO: loop com as opções do admin
    }
}
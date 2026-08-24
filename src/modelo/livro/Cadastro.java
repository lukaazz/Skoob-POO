package modelo.livro;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import modelo.FuncoesFormatacao;


public class Cadastro {

    public Livro cadastrarLivro(Scanner sc) {

        Livro livro;

        Genero[] todosGeneros = Genero.values();
        Set<Genero> generosEscolhidos = new HashSet<>();
        boolean continuar = true;

        System.out.print(FuncoesFormatacao.exibirCabecalho("CADASTRO DE NOVO LIVRO"));

        System.out.print(FuncoesFormatacao.solicitarCampo("Título"));
        String titulo = sc.nextLine();

        System.out.print(FuncoesFormatacao.solicitarCampo("Autor"));
        String autor = sc.nextLine();

        System.out.print(FuncoesFormatacao.solicitarCampo("Sinopse"));
        String sinopse = sc.nextLine();

        System.out.println("\nQuais são os gêneros do livro?");

        

        while(continuar) {

            System.out.println("\n");

            for(int i = 0; i < todosGeneros.length; i++) {
                System.out.println((i + 1) + ". " + todosGeneros[i]);
            }

            System.out.printf(">:");

            int opcao_genero = sc.nextInt();
            sc.nextLine();

            Genero escolhido = todosGeneros[opcao_genero - 1];
            generosEscolhidos.add(escolhido);


            System.out.println("\n");
            System.out.print(FuncoesFormatacao.solicitarCampo("Deseja adicionar outro gênero? (s/n)"));
            
            String opcao_continuar = sc.nextLine();

            if(opcao_continuar.equals("s")) {
                continuar = true;
            } else if (opcao_continuar.equals("n")) {
                continuar = false;
            }
            // tratamento de exceção
        }

        System.out.print(FuncoesFormatacao.exibirSubtitulo("TIPO DE LIVRO"));
        System.out.println(" 1. Livro Físico");
        System.out.println(" 2. Ebook");
        System.out.println(" 3. AudioBook");
        System.out.print(FuncoesFormatacao.inserirLinha());

        System.out.print(FuncoesFormatacao.solicitarCampo("Escolha uma opção"));
        int op = sc.nextInt();
        sc.nextLine();

        System.out.print(FuncoesFormatacao.inserirLinha());

        switch (op) {
            case 1 -> {
                System.out.print(FuncoesFormatacao.solicitarCampo("Quantidade de páginas"));
                String quantidadePaginas = sc.nextLine();
                livro = new LivroFisico(titulo, autor, sinopse, generosEscolhidos, quantidadePaginas);
            }

            case 2 -> {
                System.out.print(FuncoesFormatacao.solicitarCampo("Quantidade de palavras"));
                String quantidadePalavras = sc.nextLine();
                livro = new Ebook(titulo, autor, sinopse, generosEscolhidos, quantidadePalavras);
            }

            case 3 -> {
                System.out.print(FuncoesFormatacao.solicitarCampo("Duração em minutos"));
                String duracaoMinutos = sc.nextLine();
                livro = new AudioBook(titulo, autor, sinopse, generosEscolhidos, duracaoMinutos);
            }

            default -> {
                System.out.print(FuncoesFormatacao.inserirDivisor());
                throw new IllegalArgumentException("Opção inválida: " + op);
            }
        }

        System.out.print(FuncoesFormatacao.inserirDivisor());
        System.out.println(" Livro cadastrado com sucesso!");
        System.out.print(FuncoesFormatacao.inserirDivisor());

        return livro;
    }
    
}

package modelo.livro;

import java.util.Scanner;
import modelo.FuncoesFormatacao;


public class Cadastro {

    public Livro cadastrarLivro(Scanner sc) {

        Livro livro;

        FuncoesFormatacao.exibirCabecalho("CADASTRO DE NOVO LIVRO");

        FuncoesFormatacao.solicitarCampo("Título");
        String titulo = sc.nextLine();

        FuncoesFormatacao.solicitarCampo("Autor");
        String autor = sc.nextLine();

        FuncoesFormatacao.solicitarCampo("Sinopse");
        String sinopse = sc.nextLine();

        FuncoesFormatacao.exibirSubtitulo("TIPO DE LIVRO");
        System.out.println(" 1. Livro Físico");
        System.out.println(" 2. Ebook");
        System.out.println(" 3. AudioBook");
        FuncoesFormatacao.inserirLinha();

        FuncoesFormatacao.solicitarCampo("Escolha uma opção");
        int op = sc.nextInt();
        sc.nextLine();

        FuncoesFormatacao.inserirLinha();

        switch (op) {
            case 1 -> {
                FuncoesFormatacao.solicitarCampo("Quantidade de páginas");
                String quantidadePaginas = sc.nextLine();
                livro = new LivroFisico(titulo, autor, sinopse, quantidadePaginas);
            }

            case 2 -> {
                FuncoesFormatacao.solicitarCampo("Quantidade de palavras");
                String quantidadePalavras = sc.nextLine();
                livro = new Ebook(titulo, autor, sinopse, quantidadePalavras);
            }

            case 3 -> {
                FuncoesFormatacao.solicitarCampo("Duração em minutos");
                String duracaoMinutos = sc.nextLine();
                livro = new AudioBook(titulo, autor, sinopse, duracaoMinutos);
            }

            default -> {
                FuncoesFormatacao.inserirDivisor();
                throw new IllegalArgumentException("Opção inválida: " + op);
            }
        }

        FuncoesFormatacao.inserirDivisor();
        System.out.println(" Livro cadastrado com sucesso!");
        FuncoesFormatacao.inserirDivisor();

        return livro;
    }
    
}

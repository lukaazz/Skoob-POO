package livro;

import java.util.Scanner;

public class Biblioteca {



    public Livro cadastrarLivro() {

        Livro livro;
        Scanner sc = new Scanner(System.in);

        System.out.println("Escreva as informações do livro.\nTítulo\n>:");
        String titulo = sc.nextLine();

        System.out.println("Autor\n>:");
        String autor = sc.nextLine();

        System.out.println("Sinopse\n>:");
        String sinopse = sc.nextLine();

        System.out.println("Qual tipo de livro é? \n1. Livro Físico\n2. Ebook\n3. AudioBook");
        int op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1 -> {
                System.out.println("Quantidade de páginas\n>:");
                String quantidadePaginas = sc.nextLine();
                livro = new LivroFisico(titulo, autor, sinopse, 1, quantidadePaginas);
            }

            case 2 -> {
                System.out.println("Quantidade de palavras\n>:");
                String quantidadePalavras = sc.nextLine();
                livro = new Ebook(titulo, autor, sinopse,  1, quantidadePalavras);
            }

            case 3 -> {
                System.out.println("Duração em minutos\n>:");
                String duracaoMinutos = sc.nextLine();
                livro = new AudioBook(titulo, autor, sinopse, 1, duracaoMinutos);
            }

            default -> {
                sc.close();
                throw new AssertionError();
            }
        }

        sc.close();

        return livro;
    }

}
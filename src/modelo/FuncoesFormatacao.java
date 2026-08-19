package modelo;

public class FuncoesFormatacao {

    private FuncoesFormatacao() {}

    public static void inserirLinha() {
        String divisor = "-".repeat(60);
        System.out.println("\n" + divisor);
    }

    public static void inserirDivisor() {
        String divisor = "=".repeat(60);
        System.out.println("\n" + divisor);
    }

    public static void exibirCabecalho(String titulo) {
        inserirDivisor();
        System.out.println(" " + titulo.toUpperCase());
        String divisor = "=".repeat(60);
        System.out.println(divisor);
    }

    public static void exibirSubtitulo(String subtitulo) {
        inserirLinha();
        System.out.println(" " + subtitulo);
        String divisor = "-".repeat(60);
        System.out.println(divisor);
    }

    public static void solicitarCampo(String rotulo) {
        System.out.printf(" %-24s > ", rotulo);
    }
    
}

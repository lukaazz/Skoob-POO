package modelo;

public class FuncoesFormatacao {

    private FuncoesFormatacao() {}

    public static String inserirLinha() {
        String divisor = "-".repeat(60);
        return "\n" + divisor + "\n";
    }

    public static String inserirDivisor() {
        String divisor = "=".repeat(60);
        return "\n" + divisor + "\n";
    }

    public static String exibirCabecalho(String titulo) {
        String divisor = "=".repeat(60);
        return inserirDivisor() + titulo.toUpperCase() + "\n" + divisor;
    }

    public static String exibirSubtitulo(String subtitulo) {
        String divisor = "-".repeat(60);
        return inserirLinha() + subtitulo + "\n" + divisor;
    }

    public static String solicitarCampo(String rotulo) {
        return String.format(" %-24s > ", rotulo);
    }
}
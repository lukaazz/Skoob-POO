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

    // Formata uma única opção numerada de menu (ex: " 1 - Login")
    public static String exibirOpcaoMenu(int numero, String texto) {
        return String.format(" %d - %s%n", numero, texto);
    }

    // Monta um menu completo: cabeçalho + opções numeradas + linha de fechamento
    public static String exibirMenu(String titulo, String... opcoes) {
        StringBuilder resultado = new StringBuilder();

        resultado.append(exibirCabecalho(titulo)).append("\n");

        for (int i = 0; i < opcoes.length; i++) {
            resultado.append(exibirOpcaoMenu(i + 1, opcoes[i]));
        }

        resultado.append(inserirLinha());

        return resultado.toString();
    }

    // Formata uma mensagem de sucesso/confirmação
    public static String exibirMensagem(String mensagem) {
        return inserirDivisor() + " " + mensagem + inserirDivisor();
    }

    // Formata uma mensagem de erro
    public static String exibirErro(String mensagem) {
        return inserirLinha() + " ERRO: " + mensagem + inserirLinha();
    }
}
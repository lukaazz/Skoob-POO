package modelo.usuario;

public class Administrador extends Usuario {
    private static final long serialVersionUID = 1L;

    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
    }
}
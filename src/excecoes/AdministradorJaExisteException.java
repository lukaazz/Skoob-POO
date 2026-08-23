package excecoes;

public class AdministradorJaExisteException extends Exception {
    public AdministradorJaExisteException() {
        super("Administrador ja existe!");
    }
    
}

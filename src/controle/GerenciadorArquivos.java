// add outros arquivos
package controle;

import excecoes.AdministradorJaExisteException;
import excecoes.AutenticacaoInvalidaException;
import excecoes.LeitorJaExisteException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import modelo.livro.Biblioteca;
import modelo.livro.Livro;
import modelo.usuario.Administrador;
import modelo.usuario.Leitor;
import modelo.usuario.Usuario;

public class GerenciadorArquivos {

    private Path caminhoLeitores = Path.of("src/persistencia/leitores.dat");
    private Path caminhoAdministradores = Path.of("src/persistencia/administradores.dat");

    private Path caminhoBiblioteca = Path.of("src/persistencia/biblioteca.dat");
    private Biblioteca biblioteca;

    private Map<String, Leitor> leitores;
    private Map<String, Administrador> administradores;

    public GerenciadorArquivos() {

        if (!caminhoLeitores.toFile().exists()) {

            try {
                caminhoLeitores.toFile().createNewFile();
                leitores = new HashMap<>();

            } catch (IOException e) {
                System.out.println("Erro ao criar os arquivos");
            }

        } else {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoLeitores.toFile()))) {
                leitores = (Map<String, Leitor>) ois.readObject();

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao ler os leitores");
                leitores = new HashMap<>();
            }
        }

        if (!caminhoAdministradores.toFile().exists()) {

            try {
                caminhoAdministradores.toFile().createNewFile();
                administradores = new HashMap<>();

            } catch (IOException e) {
                System.out.println("Erro ao criar os arquivos");
            }

        } else {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoAdministradores.toFile()))) {

                administradores = (Map<String, Administrador>) ois.readObject();

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao ler os administradores");
                administradores = new HashMap<>();
            }
        }

        this.biblioteca = new Biblioteca();

        if (!caminhoBiblioteca.toFile().exists()) {
            try {
                caminhoBiblioteca.toFile().createNewFile();
                salvarCatalogo();
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo da biblioteca");
            }
        } else {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoBiblioteca.toFile()))) {

                @SuppressWarnings("unchecked")
                Map<Integer, Livro> catalogoCarregado = (Map<Integer, Livro>) ois.readObject();

                this.biblioteca.carregarCatalogo(catalogoCarregado);

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao ler o catálogo da biblioteca");
            }
        }
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void adicionarLeitor(Leitor leitor) throws LeitorJaExisteException {

        if (leitores.containsKey(leitor.getEmail())) {
            throw new LeitorJaExisteException();
        } else {
            leitores.put(leitor.getEmail(), leitor);
            salvarLeitores();
        }
    }

    public void adicionarAdministrador(Administrador administrador) throws AdministradorJaExisteException {

        if (administradores.containsKey(administrador.getEmail())) {
            throw new AdministradorJaExisteException();
        } else {
            administradores.put(administrador.getEmail(), administrador);
            salvarAdministradores();
        }
    }

    public void salvarLeitores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoLeitores.toFile()))) {
            oos.writeObject(leitores);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os leitores");
        }
    }

    public void salvarAdministradores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoAdministradores.toFile()))) {
            oos.writeObject(administradores);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os administradores");
        }
    }

    public final void salvarCatalogo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoBiblioteca.toFile()))) {
            // Grava apenas o HashMap do catálogo no arquivo
            oos.writeObject(this.biblioteca.getCatalogo());
        } catch (IOException e) {
            System.out.println("Erro ao salvar o catálogo da biblioteca");
        }
    }

    public Usuario autenticar(String email, String senha) throws AutenticacaoInvalidaException {

        if (this.leitores.containsKey(email)) {
            if (this.leitores.get(email).senhaCerta(senha)) {
                return this.leitores.get(email);
            } else {
                throw new AutenticacaoInvalidaException();
            }

        } else if (this.administradores.containsKey(email)) {
            if (this.administradores.get(email).senhaCerta(senha)) {
                return this.administradores.get(email);
            } else {
                throw new AutenticacaoInvalidaException();
            }

        } else {
            throw new AutenticacaoInvalidaException();
        }
    }

}

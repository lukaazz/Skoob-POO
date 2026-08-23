// add outros arquivos

package controle;

import excecoes.AdministradorJaExisteException;
import excecoes.LeitorJaExisteException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import modelo.usuario.Administrador;
import modelo.usuario.Leitor;


public class GerenciadorArquivos {
    private Path caminhoLeitores = Path.of("src/persistencia/leitores.dat");
    private Path caminhoAdministradores = Path.of("src/persistencia/administradores.dat");

    private Map<String, Leitor> leitores;
    private Map<String, Administrador> administradores;

    public GerenciadorArquivos() {

        if(!caminhoLeitores.toFile().exists()) {

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

        if(!caminhoAdministradores.toFile().exists()) {

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
    }

    public void adicionarLeitor(Leitor leitor) {

        try {
            if(leitores.containsKey(leitor.getEmail())) {
                throw new LeitorJaExisteException();
            } else {
                leitores.put(leitor.getEmail(), leitor);

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoLeitores.toFile()))) {
                    oos.writeObject(leitores);
                } catch (IOException e) {
                    System.out.println("Erro ao salvar os leitores");
                }
            }
        } catch (LeitorJaExisteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adicionarAdministrador(Administrador administrador) {
        try {
            if(administradores.containsKey(administrador.getEmail())) {
                throw new AdministradorJaExisteException();
            } else {
                administradores.put(administrador.getEmail(), administrador);

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoAdministradores.toFile()))) {
                    oos.writeObject(administradores);
                } catch (IOException e) {
                    System.out.println("Erro ao salvar os administradores");
                }
            }
        } catch (AdministradorJaExisteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void salvarLeitores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoLeitores.toFile()))) {
            oos.writeObject(leitores);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os leitores");
        }
    }

}
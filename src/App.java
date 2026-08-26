// PARA COMPILAR: javac -d bin -sourcepath src src/App.java
// PARA EXECUTAR: java -cp bin App

import controle.Menu;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Menu menu = new Menu(sc);
        menu.iniciar();

        sc.close();
    }
}

import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        Modelo thisModelo = new Modelo();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe um numero");
        boolean primo = thisModelo.primo(scanner.nextInt());
        if (primo == true) {
            System.out.println("Seu numero é primo");
        } else {
            System.out.println("Seu numero não é primo");
        }
    }
}

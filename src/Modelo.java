public class Modelo {
    public boolean primo(int numero){
        int Divisor = 0;
        for (int i = 1; i <= numero; i++) {
            if (numero % i == 0) {
                Divisor = Divisor += 1;
            }
        }
        if (Divisor == 2) {
            return true;
        } else {
            return false;
        }
    }
}

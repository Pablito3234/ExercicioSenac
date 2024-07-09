package Cadastro;

public class Calculos {

    public int sortear(int min, int max) {
        int intervalo = max - min + 1;
        int numRandom = (int) (Math.random() * intervalo) + min;
        return numRandom;
    }

    public int[] arrayResults(int size) {
        int[] vet = new int[size];
        for (int i = 0; i < vet.length; i++) {
            vet[i] = sortear(0, 100);
        }
        return vet;
    }

    public int calcularDiferença(int[] results, int num) {
        int menorDiferenca = Integer.MAX_VALUE;
        for (int i : results) {
            int diferenca = Math.abs(i - num);
            if (diferenca < menorDiferenca) {
                menorDiferenca = diferenca;
            }
        }
        return menorDiferenca;
    }
    
     public int numeroProximo(int[] vetor, int numero) {
        int maisProximo = vetor[0];
        int menorDiferenca = Math.abs(vetor[0] - numero);

        for (int i = 1; i < vetor.length; i++) {
            int diferencaAtual = Math.abs(vetor[i] - numero);

            if (diferencaAtual < menorDiferenca) {
                menorDiferenca = diferencaAtual;
                maisProximo = vetor[i];
            }
        }
        return maisProximo;
    }
    
    
}

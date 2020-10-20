package br.ufv.dpi.testandojava;

public class Funcoes {
    public boolean ehPrimo(int num){

        if (num <= 1)
            return false;

        int temp = 2;
        boolean Primo = true;

        while (temp < num) {
            if (num % temp == 0) {
                Primo = false;
                break;
            }
            temp++;
        }

        if (Primo) {
            return true;
        }else {
            return false;
        }

    }


    public int fibo(int n) {
        int F = 0;     // atual
        int ant = 0;   // anterior

        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                F = 1;       //atual
                ant = 0;     //anterior
            } else {
                F += ant;              //atual
                ant = F - ant;         //anterior
            }
        }
        return F;
    }


    public String classificaFibo(int v) {
        if(v < 1)
            return "Entrada inválida";
        int fib = fibo(v);
        boolean classifica = ehPrimo(fib);
        if(classifica)
            return "O "+ v +"º número da sequencia é Primo";
        else
            return "O "+ v +"º número da sequencia não é Primo";

    }
}

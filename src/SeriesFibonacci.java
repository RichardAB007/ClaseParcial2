import java.util.Scanner;

public class SeriesFibonacci {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el valor de n: ");
        int n = sc.nextInt();

        double suma = 0;
        int fib1 = 0, fib2 = 1, fibActual = 0;

        int grupo = 1;
        int contadorGrupo = 0;
        int esPotencia = 1;  // 1 = potencia, 0 = raiz
        int signo = 1;

        System.out.println("\nTerminos de la serie:");

        for (int i = 1; i <= n; i++) {
            // Obtener Fibonacci actual
            if (i == 1) {
                fibActual = 0;
            } else if (i == 2) {
                fibActual = 1;
            } else {
                fibActual = fib1 + fib2;
                fib1 = fib2;
                fib2 = fibActual;
            }

            double termino;

            if (esPotencia == 1) {
                // Obtener el i-esimo numero primo
                int contadorPrimos = 0;
                int numero = 1;
                int exponente = 2;

                while (contadorPrimos < i) {
                    numero++;
                    // Verificar si numero es primo
                    int esPrimo = 1;
                    for (int j = 2; j <= Math.sqrt(numero); j++) {
                        if (numero % j == 0) {
                            esPrimo = 0;
                        }
                    }
                    if (esPrimo == 1) {
                        contadorPrimos++;
                        exponente = numero;
                    }
                }

                termino = signo * (Math.pow(fibActual, exponente) / i);
                System.out.println("Termino " + i + ": " + signo + " * " + fibActual + "^" + exponente + "/" + i + " = " + termino);
            } else {
                termino = signo * Math.sqrt((double) fibActual / i);
                System.out.println("Termino " + i + ": " + signo + " * sqrt(" + fibActual + "/" + i + ") = " + termino);
            }

            suma = suma + termino;

            contadorGrupo++;
            if (contadorGrupo >= grupo) {
                contadorGrupo = 0;
                grupo++;
                if (esPotencia == 1) {
                    esPotencia = 0;
                    signo = -1;
                } else {
                    esPotencia = 1;
                    signo = 1;
                }
            }
        }

        System.out.println("\n========================================");
        System.out.println("SUMA TOTAL S = " + suma);
        System.out.println("========================================");

        sc.close();
    }
}

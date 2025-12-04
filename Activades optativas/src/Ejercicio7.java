public class Ejercicio7 {
    public static void main(String[] args) {
        // Arreglo de números enteros
        int[] numeros = {45, 12, 78, 23, 56, 89, 34, 67, 90, 11, 44, 88, 55, 22, 99};

        // Encontrar número mayor y menor
        int maximo = numeros[0];
        int minimo = numeros[0];
        for (int num : numeros) {
            if (num > maximo) maximo = num;
            if (num < minimo) minimo = num;
        }

        // Contar pares e impares
        int contPares = 0, contImpares = 0;
        for (int num : numeros) {
            if (num % 2 == 0) {
                contPares++;
            } else {
                contImpares++;
            }
        }

        // Calcular suma acumulada y promedio
        int sumaTotal = 0;
        for (int num : numeros) {
            sumaTotal += num;
        }
        double promedio = (double) sumaTotal / numeros.length;

        // Mostrar resultados
        System.out.println("=== EJERCICIO 7: ANÁLISIS DE NÚMEROS ===");
        System.out.println("Números: ");
        for (int num : numeros) {
            System.out.print(num + " ");
        }
        System.out.println("\n");

        System.out.println("Número mayor: " + maximo);
        System.out.println("Número menor: " + minimo);
        System.out.println("Cantidad de números pares: " + contPares);
        System.out.println("Cantidad de números impares: " + contImpares);
        System.out.println("Suma total: " + sumaTotal);
        System.out.println("Promedio: " + String.format("%.2f", promedio));
        System.out.println();
    }
}


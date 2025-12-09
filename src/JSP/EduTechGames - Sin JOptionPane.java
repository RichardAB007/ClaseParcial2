import java.util.Scanner;

public class EduTechGames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ==========================================
        // PARTE 1: REGISTRO DE DATOS
        // ==========================================

        int cantidadPuntajes = 0;

        // Solicitar cantidad de puntajes (debe estar entre 5 y 15)
        while (cantidadPuntajes < 5 || cantidadPuntajes > 15) {
            System.out.print("Ingrese la cantidad de puntajes a registrar (5-15): ");
            cantidadPuntajes = scanner.nextInt();

            if (cantidadPuntajes < 5 || cantidadPuntajes > 15) {
                System.out.println("Error: El valor debe estar entre 5 y 15. Intente de nuevo.");
            }
        }

        // Crear arreglo con la cantidad indicada
        int[] puntajes = new int[cantidadPuntajes];

        // Solicitar cada puntaje (debe estar entre 0 y 100)
        for (int i = 0; i < cantidadPuntajes; i++) {
            int puntaje = -1;

            while (puntaje < 0 || puntaje > 100) {
                System.out.print("Ingrese el puntaje #" + (i + 1) + " (0-100): ");
                puntaje = scanner.nextInt();

                if (puntaje < 0 || puntaje > 100) {
                    System.out.println("Error: El puntaje debe estar entre 0 y 100. Intente de nuevo.");
                }
            }

            puntajes[i] = puntaje;
        }

        // ==========================================
        // PARTE 2: PROCESAMIENTO DE DATOS
        // ==========================================

        // Calcular puntaje más alto
        int puntajeAlto = puntajes[0];
        for (int i = 1; i < cantidadPuntajes; i++) {
            if (puntajes[i] > puntajeAlto) {
                puntajeAlto = puntajes[i];
            }
        }

        // Calcular puntaje más bajo
        int puntajeBajo = puntajes[0];
        for (int i = 1; i < cantidadPuntajes; i++) {
            if (puntajes[i] < puntajeBajo) {
                puntajeBajo = puntajes[i];
            }
        }

        // Calcular promedio general
        int suma = 0;
        for (int i = 0; i < cantidadPuntajes; i++) {
            suma = suma + puntajes[i];
        }
        double promedio = (double) suma / cantidadPuntajes;

        // Contar puntajes mayores o iguales a 90
        int cantidadMayores90 = 0;
        for (int i = 0; i < cantidadPuntajes; i++) {
            if (puntajes[i] >= 90) {
                cantidadMayores90 = cantidadMayores90 + 1;
            }
        }

        // Mostrar resultados del análisis
        System.out.println("\n========== ANALISIS ESTADISTICO ==========");
        System.out.println("Puntaje mas alto: " + puntajeAlto);
        System.out.println("Puntaje mas bajo: " + puntajeBajo);
        System.out.println("Promedio general: " + promedio);
        System.out.println("Puntajes >= 90: " + cantidadMayores90);
        System.out.println("===========================================\n");

        // ==========================================
        // PARTE 3: MENU INTERACTIVO
        // ==========================================

        int opcion = 0;

        while (opcion != 4) {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Ver todos los puntajes");
            System.out.println("2. Ver puntajes aprobados (>= 60)");
            System.out.println("3. Ver puntajes reprobados (< 60)");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            if (opcion == 1) {
                // Mostrar todos los puntajes
                System.out.println("\n--- TODOS LOS PUNTAJES ---");
                for (int i = 0; i < cantidadPuntajes; i++) {
                    System.out.println("Puntaje #" + (i + 1) + ": " + puntajes[i]);
                }

            } else if (opcion == 2) {
                // Mostrar puntajes aprobados (>= 60)
                System.out.println("\n--- PUNTAJES APROBADOS (>= 60) ---");
                int contadorAprobados = 0;
                for (int i = 0; i < cantidadPuntajes; i++) {
                    if (puntajes[i] >= 60) {
                        System.out.println("Puntaje #" + (i + 1) + ": " + puntajes[i]);
                        contadorAprobados = contadorAprobados + 1;
                    }
                }
                if (contadorAprobados == 0) {
                    System.out.println("No hay puntajes aprobados.");
                }

            } else if (opcion == 3) {
                // Mostrar puntajes reprobados (< 60)
                System.out.println("\n--- PUNTAJES REPROBADOS (< 60) ---");
                int contadorReprobados = 0;
                for (int i = 0; i < cantidadPuntajes; i++) {
                    if (puntajes[i] < 60) {
                        System.out.println("Puntaje #" + (i + 1) + ": " + puntajes[i]);
                        contadorReprobados = contadorReprobados + 1;
                    }
                }
                if (contadorReprobados == 0) {
                    System.out.println("No hay puntajes reprobados.");
                }

            } else if (opcion == 4) {
                System.out.println("\nGracias por usar EduTech Games. Hasta luego!");

            } else {
                System.out.println("Opcion invalida. Por favor seleccione del 1 al 4.");
            }
        }

        scanner.close();
    }
}
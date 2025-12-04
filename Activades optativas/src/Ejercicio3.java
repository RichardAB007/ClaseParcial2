public class Ejercicio3 {
    public static void main(String[] args) {
        // Arreglos de nombres y notas
        String[] nombres = {"Juan", "María", "Pedro", "Ana", "Carlos", "Laura", "Diego", "Sofia"};
        double[] notas = {3.5, 4.2, 2.8, 3.9, 2.5, 4.5, 3.1, 3.7};

        // Calcular promedio general del curso
        double sumaNotas = 0;
        for (double nota : notas) {
            sumaNotas += nota;
        }
        double promedioGeneral = sumaNotas / notas.length;

        // Identificar estudiante con mejor rendimiento
        double maxNota = notas[0];
        int mejorEstudiante = 0;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] > maxNota) {
                maxNota = notas[i];
                mejorEstudiante = i;
            }
        }

        // Generar lista de estudiantes con nota inferior a 3.0
        System.out.println("=== EJERCICIO 3: GESTIÓN DE ESTUDIANTES ===");
        System.out.println("Estudiantes y sus notas:");
        for (int i = 0; i < nombres.length; i++) {
            System.out.println(nombres[i] + ": " + notas[i]);
        }

        System.out.println("\nPromedio general del curso: " + String.format("%.2f", promedioGeneral));
        System.out.println("Estudiante con mejor rendimiento: " + nombres[mejorEstudiante] + " (" + maxNota + ")");

        System.out.println("\nEstudiantes con nota inferior a 3.0:");
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] < 3.0) {
                System.out.println("  - " + nombres[i] + ": " + notas[i]);
            }
        }
        System.out.println();
    }
}


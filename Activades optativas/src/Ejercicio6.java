public class Ejercicio6 {
    public static void main(String[] args) {
        // Arreglo de horas trabajadas durante 14 días
        double[] horasTrabajadas = {8.5, 7.2, 8.0, 9.1, 7.8, 8.3, 6.5,
                8.8, 9.2, 7.5, 8.1, 9.0, 7.9, 8.4};

        // Calcular total de horas trabajadas
        double totalHoras = 0;
        for (double horas : horasTrabajadas) {
            totalHoras += horas;
        }

        // Contar días que superaron 8 horas
        int diasSobre8Horas = 0;
        for (double horas : horasTrabajadas) {
            if (horas > 8.0) {
                diasSobre8Horas++;
            }
        }

        // Identificar día con menor carga laboral
        double minHoras = horasTrabajadas[0];
        int diaMinHoras = 0;
        for (int i = 0; i < horasTrabajadas.length; i++) {
            if (horasTrabajadas[i] < minHoras) {
                minHoras = horasTrabajadas[i];
                diaMinHoras = i;
            }
        }

        // Mostrar resultados
        System.out.println("=== EJERCICIO 6: CONTROL DE HORAS TRABAJADAS ===");
        System.out.println("Horas trabajadas por día (14 días):");
        for (int i = 0; i < horasTrabajadas.length; i++) {
            System.out.println("Día " + (i + 1) + ": " + horasTrabajadas[i] + " horas");
        }

        System.out.println("\nTotal de horas trabajadas: " + String.format("%.2f", totalHoras) + " horas");
        System.out.println("Días que superaron 8 horas: " + diasSobre8Horas);
        System.out.println("Día con menor carga laboral: Día " + (diaMinHoras + 1) + " (" + minHoras + " horas)");
        System.out.println("Promedio de horas por día: " + String.format("%.2f", totalHoras / 14) + " horas");
        System.out.println();
    }
}


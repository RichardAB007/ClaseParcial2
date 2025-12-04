public class Ejercicio5 {
    public static void main(String[] args) {
        // Arreglo de calificaciones de 10 estudiantes (0-5)
        double[] calificaciones = {3.5, 4.2, 2.8, 3.9, 4.5, 2.5, 3.1, 4.0, 3.7, 2.9};

        // Calcular mediana
        double[] calificacionesOrdenadas = calificaciones.clone();
        double mediana;
        if (calificacionesOrdenadas.length % 2 == 0) {
            mediana = (calificacionesOrdenadas[calificacionesOrdenadas.length / 2 - 1] +
                    calificacionesOrdenadas[calificacionesOrdenadas.length / 2]) / 2;
        } else {
            mediana = calificacionesOrdenadas[calificacionesOrdenadas.length / 2];
        }

        // Calcular moda (valor más frecuente)
        double moda = 0;
        int maxFrecuencia = 0;
        for (double cal : calificaciones) {
            int frecuencia = 0;
            for (double c : calificaciones) {
                if (cal == c) frecuencia++;
            }
            if (frecuencia > maxFrecuencia) {
                maxFrecuencia = frecuencia;
                moda = cal;
            }
        }

        // Contar aprobados y reprobados
        int aprobados = 0, reprobados = 0;
        for (double cal : calificaciones) {
            if (cal >= 3.0) {
                aprobados++;
            } else {
                reprobados++;
            }
        }
        double porcentajeAprobados = (aprobados * 100.0) / calificaciones.length;
        double porcentajeReprobados = (reprobados * 100.0) / calificaciones.length;

        // Crear histograma
        System.out.println("=== EJERCICIO 5: ANÁLISIS DE CALIFICACIONES ===");
        System.out.println("Calificaciones: ");
        for (double cal : calificaciones) {
            System.out.print(cal + " ");
        }
        System.out.println("\n");

        System.out.println("Mediana: " + String.format("%.2f", mediana));
        System.out.println("Moda: " + String.format("%.2f", moda));
        System.out.println("Porcentaje de aprobados (≥ 3.0): " + String.format("%.1f", porcentajeAprobados) + "%");
        System.out.println("Porcentaje de reprobados (< 3.0): " + String.format("%.1f", porcentajeReprobados) + "%");

        System.out.println("\nHistograma textual:");
        for (double cal : calificacionesOrdenadas) {
            System.out.print(String.format("%.1f", cal) + ": ");
            for (int i = 0; i < (int)(cal * 2); i++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }
}


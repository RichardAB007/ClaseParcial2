public class Ejercicio1{
    public static void main(String[] args) {
        // Arreglo de temperaturas de 7 días
        double[] temperaturas = {28.5, 32.1, 25.3, 29.8, 31.2, 27.6, 30.4};
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

        // Calcular promedio
        double suma = 0;
        for (double temp : temperaturas) {
            suma += temp;
        }
        double promedio = suma / temperaturas.length;

        // Encontrar día más caliente y más frío
        double maxTemp = temperaturas[0];
        double minTemp = temperaturas[0];
        int diaCaliente = 0;
        int diaFrio = 0;

        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i] > maxTemp) {
                maxTemp = temperaturas[i];
                diaCaliente = i;
            }
            if (temperaturas[i] < minTemp) {
                minTemp = temperaturas[i];
                diaFrio = i;
            }
        }

        // Contar días sobre el promedio
        int diasSobrePromedio = 0;
        for (double temp : temperaturas) {
            if (temp > promedio) {
                diasSobrePromedio++;
            }
        }

        // Mostrar resultados
        System.out.println("=== EJERCICIO 1: TEMPERATURAS DIARIAS ===");
        System.out.println("Temperaturas de la semana:");
        for (int i = 0; i < temperaturas.length; i++) {
            System.out.println(dias[i] + ": " + temperaturas[i] + "°C");
        }
        System.out.println("\nPromedio semanal: " + String.format("%.2f", promedio) + "°C");
        System.out.println("Día más caliente: " + dias[diaCaliente] + " (" + maxTemp + "°C)");
        System.out.println("Día más frío: " + dias[diaFrio] + " (" + minTemp + "°C)");
        System.out.println("Días sobre el promedio: " + diasSobrePromedio);
        System.out.println();
    }
}

public class Ejercicio8 {
    public static void main(String[] args) {
        // Arreglos de datos de clientes
        String[] nombres = {"Carlos López", "María García", "Juan Martínez", "Ana Rodríguez",
                "Pedro Sánchez", "Laura Fernández", "Diego Ruiz", "Sofia Torres"};
        int[] edades = {28, 35, 42, 31, 26, 38, 45, 29};
        String[] ciudades = {"Bogotá", "Medellín", "Bogotá", "Cali", "Bogotá", "Barranquilla", "Medellín", "Bogotá"};

        // Encontrar cliente más joven y de mayor edad
        int indexMasJoven = 0, indexMasViejo = 0;
        int edadMinima = edades[0], edadMaxima = edades[0];

        for (int i = 0; i < edades.length; i++) {
            if (edades[i] < edadMinima) {
                edadMinima = edades[i];
                indexMasJoven = i;
            }
            if (edades[i] > edadMaxima) {
                edadMaxima = edades[i];
                indexMasViejo = i;
            }
        }

        // Calcular promedio de edades
        int sumaEdades = 0;
        for (int edad : edades) {
            sumaEdades += edad;
        }
        double promedioEdades = (double) sumaEdades / edades.length;

        // Mostrar resultados
        System.out.println("=== EJERCICIO 8: REGISTRO DE CLIENTES ===");
        System.out.println("Registro de clientes:");
        for (int i = 0; i < nombres.length; i++) {
            System.out.println((i + 1) + ". " + nombres[i] + ", " + edades[i] + " años, " + ciudades[i]);
        }

        System.out.println("\nCliente más joven: " + nombres[indexMasJoven] + " (" + edadMinima + " años)");
        System.out.println("Cliente de mayor edad: " + nombres[indexMasViejo] + " (" + edadMaxima + " años)");
        System.out.println("Promedio de edades: " + String.format("%.2f", promedioEdades) + " años");

        // Listar clientes de una ciudad específica
        String ciudadBusqueda = "Bogotá";
        System.out.println("\nClientes que viven en " + ciudadBusqueda + ":");
        for (int i = 0; i < ciudades.length; i++) {
            if (ciudades[i].equals(ciudadBusqueda)) {
                System.out.println("  - " + nombres[i] + ", " + edades[i] + " años");
            }
        }
        System.out.println();
    }
}

public class Ejercicio2 {
    public static void main(String[] args) {
        // Arreglo de ventas diarias (30 días)
        double[] ventas = {1500, 2300, 1800, 2100, 1900, 3200, 2800,
                2200, 1700, 2400, 3100, 2600, 2000, 1950,
                2700, 3300, 2900, 2100, 1800, 2500, 3400,
                2300, 2100, 1900, 2800, 3200, 2400, 2100,
                1800, 2600};

        // Calcular total de ventas
        double totalVentas = 0;
        for (double venta : ventas) {
            totalVentas += venta;
        }

        // Encontrar día con mayores ventas
        double maxVenta = ventas[0];
        int diaMaxVenta = 1;
        for (int i = 0; i < ventas.length; i++) {
            if (ventas[i] > maxVenta) {
                maxVenta = ventas[i];
                diaMaxVenta = i + 1;
            }
        }

        // Calcular promedio de fines de semana vs días laborales
        // Fines de semana: sábados y domingos (días 6, 7, 13, 14, 20, 21, 27, 28)
        double sumaCuadrilongos = 0, sumaFinesWeekend = 0;
        int contadorLaborales = 0, contadorFinesWeekend = 0;

        for (int i = 0; i < ventas.length; i++) {
            int diaDelMes = i + 1;
            int diaSemana = diaDelMes % 7; // 1-7

            if (diaSemana == 0 || diaSemana == 6) { // Sábado y domingo
                sumaFinesWeekend += ventas[i];
                contadorFinesWeekend++;
            } else {
                sumaCuadrilongos += ventas[i];
                contadorLaborales++;
            }
        }

        double promedioLaborales = sumaCuadrilongos / contadorLaborales;
        double promedioFinesWeekend = sumaFinesWeekend / contadorFinesWeekend;

        // Mostrar resultados
        System.out.println("=== EJERCICIO 2: REGISTRO DE VENTAS ===");
        System.out.println("Total de ventas del mes: $" + String.format("%.2f", totalVentas));
        System.out.println("Día con mayores ventas: Día " + diaMaxVenta + " ($" + String.format("%.2f", maxVenta) + ")");
        System.out.println("Promedio de ventas días laborales: $" + String.format("%.2f", promedioLaborales));
        System.out.println("Promedio de ventas fines de semana: $" + String.format("%.2f", promedioFinesWeekend));
        System.out.println();
    }
}


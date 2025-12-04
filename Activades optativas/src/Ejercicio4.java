public class Ejercicio4 {
public static void main(String[] args) {
    // Arreglos de nombres y cantidades de productos
    String[] productos = {"Laptop", "Mouse", "Teclado", "Monitor", "Webcam", "Auriculares", "Escritorio"};
    int[] cantidades = {5, 45, 28, 8, 15, 32, 12};

    // Identificar producto con mayor y menor stock
    int maxStock = cantidades[0];
    int minStock = cantidades[0];
    int indexMaxStock = 0;
    int indexMinStock = 0;

    for (int i = 0; i < cantidades.length; i++) {
        if (cantidades[i] > maxStock) {
            maxStock = cantidades[i];
            indexMaxStock = i;
        }
        if (cantidades[i] < minStock) {
            minStock = cantidades[i];
            indexMinStock = i;
        }
    }

    // Calcular total de unidades
    int totalUnidades = 0;
    for (int cantidad : cantidades) {
        totalUnidades += cantidad;
    }

    // Generar alerta de stock crítico
    System.out.println("=== EJERCICIO 4: CONTROL DE INVENTARIO ===");
    System.out.println("Inventario actual:");
    for (int i = 0; i < productos.length; i++) {
        System.out.println(productos[i] + ": " + cantidades[i] + " unidades");
    }

    System.out.println("\nProducto con mayor stock: " + productos[indexMaxStock] + " (" + maxStock + " unidades)");
    System.out.println("Producto con menor stock: " + productos[indexMinStock] + " (" + minStock + " unidades)");
    System.out.println("Total de unidades en inventario: " + totalUnidades);

    System.out.println("\nAlertas de stock crítico (< 10 unidades):");
    boolean hayAlerta = false;
    for (int i = 0; i < productos.length; i++) {
        if (cantidades[i] < 10) {
            System.out.println("  ⚠️  " + productos[i] + ": " + cantidades[i] + " unidades");
            hayAlerta = true;
        }
    }
    if (!hayAlerta) {
        System.out.println("  No hay productos con stock crítico");
    }
    System.out.println();
}
}

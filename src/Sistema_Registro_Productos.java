import java.util.Scanner;
public class Sistema_Registro_Productos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Variables para el límite y contadores
        int limiteProductos = 0;
        int cantidadRegistrada = 0;
        int opcionContinuar = 1;

        // Variables para totales del resumen
        float totalIVA = 0;
        float totalIMC = 0;
        float totalVentas = 0;

        // Variables para cada producto
        String nombreProducto;
        float precioBase = 0;
        int tieneIVA = 0;
        float ivaProducto = 0;
        float imcProducto = 0;
        float precioFinal = 0;

        // Constantes de impuestos
        float TASA_IVA = 0.12f;
        float TASA_IMC = 0.015f;

        System.out.println("==========================================");
        System.out.println("  SISTEMA DE REGISTRO DE PRODUCTOS");
        System.out.println("  Empresa Comercializadora 'El Buen Sabor'");
        System.out.println("==========================================\n");

        // 1. Solicitar límite máximo de productos
        System.out.print("Ingrese el limite maximo de productos para hoy: ");
        limiteProductos = sc.nextInt();

        // Validar que el límite sea positivo
        while (limiteProductos <= 0) {
            System.out.print("Error: El limite debe ser mayor a 0. Ingrese nuevamente: ");
            limiteProductos = sc.nextInt();
        }

        System.out.println("\nLimite establecido: " + limiteProductos + " productos\n");

        // 5. Bucle para ingreso de productos
        while (cantidadRegistrada < limiteProductos && opcionContinuar == 1) {

            System.out.println("------------------------------------------");
            System.out.println("PRODUCTO #" + (cantidadRegistrada + 1));
            System.out.println("------------------------------------------");

            // 3. Solicitar datos del producto
            sc.nextLine(); // Limpiar buffer

            // Nombre del producto
            System.out.print("Nombre del producto: ");
            nombreProducto = sc.nextLine();

            // Precio base con validación (no negativos)
            System.out.print("Precio base: $");
            precioBase = sc.nextFloat();

            // Validación: precio debe ser positivo
            while (precioBase <= 0) {
                System.out.print("Error: El precio debe ser positivo. Ingrese nuevamente: $");
                precioBase = sc.nextFloat();
            }

            // IVA con validación (solo 0 o 1)
            System.out.print("Tiene IVA? (1 = Si, 0 = No): ");
            tieneIVA = sc.nextInt();

            // Validación: solo acepta 0 o 1
            while (tieneIVA != 0 && tieneIVA != 1) {
                System.out.print("Error: Solo ingrese 0 o 1. Tiene IVA?: ");
                tieneIVA = sc.nextInt();
            }

            // 4. Calcular impuestos
            // Calcular IVA (12% solo si aplica)
            if (tieneIVA == 1) {
                ivaProducto = precioBase * TASA_IVA;
            } else {
                ivaProducto = 0;
            }

            // Calcular IMC (1.5% para todos)
            imcProducto = precioBase * TASA_IMC;

            // Calcular precio final
            precioFinal = precioBase + ivaProducto + imcProducto;

            // 6. Mostrar detalles del producto registrado
            System.out.println("\n>>> DETALLE DEL PRODUCTO <<<");
            System.out.println("Producto: " + nombreProducto);
            System.out.println("Precio base:    $" + precioBase);
            System.out.println("IVA aplicado (12%):   $" + ivaProducto);
            System.out.println("IMC aplicado (1,5%):   $" + imcProducto);
            System.out.println("PRECIO FINAL:   $" + precioFinal);
            System.out.println("-----------------------------\n");

            // Acumular totales para el resumen
            totalIVA = totalIVA + ivaProducto;
            totalIMC = totalIMC + imcProducto;
            totalVentas = totalVentas + precioFinal;

            // Incrementar contador
            cantidadRegistrada = cantidadRegistrada + 1;

            // 2. Verificar si se llegó al límite
            if (cantidadRegistrada >= limiteProductos) {
                System.out.println("*** Se alcanzo el limite maximo de productos ***\n");
            } else {
                // Preguntar si desea continuar
                System.out.print("Desea registrar otro producto? (1 = Si, 0 = No): ");
                opcionContinuar = sc.nextInt();
            }
        }

        // 7. Generar resumen del día
        System.out.println("\n==========================================");
        System.out.println("        RESUMEN DEL DIA");
        System.out.println("==========================================");
        System.out.println("Cantidad total de productos: " + cantidadRegistrada);
        System.out.println("Total recaudado en IVA:      $" + totalIVA);
        System.out.println("Total recaudado en IMC:      $" + totalIMC);
        System.out.println("MONTO TOTAL DE VENTAS:       $" + totalVentas);
        System.out.println("==========================================");
        System.out.println("\nGracias por usar el sistema!");

        sc.close();
    }
}

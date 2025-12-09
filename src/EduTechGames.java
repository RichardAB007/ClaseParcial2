import javax.swing.JOptionPane;

public class EduTechGames {

    public static void main(String[] args) {
        int cantidadPuntajes = 0;
        int validoCantidad = 0;

        // PARTE 1: Solicitar cantidad de puntajes con validación
        while (validoCantidad == 0) {
            String entrada = JOptionPane.showInputDialog(
                    "Bienvenido al Sistema de Análisis de Puntajes\n\n" +
                            "Ingrese la cantidad de puntajes a registrar (entre 5 y 15):"
            );

            // Validar que no sea nulo (usuario no canceló)
            if (entrada == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return;
            }

            int esNumero = 0;
            int numero = 0;

            // Verificar si es un número válido
            for (int i = 0; i < entrada.length(); i++) {
                if (entrada.charAt(i) < '0' || entrada.charAt(i) > '9') {
                    esNumero = -1;
                    break;
                }
            }

            if (esNumero != -1) {
                numero = Integer.parseInt(entrada);

                if (numero >= 5 && numero <= 15) {
                    cantidadPuntajes = numero;
                    validoCantidad = 1;
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Error: El valor debe estar entre 5 y 15.\nIntente nuevamente.",
                            "Entrada Inválida",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Error: Debe ingresar un número entero válido.\nIntente nuevamente.",
                        "Entrada Inválida",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        // Crear arreglo con la cantidad de puntajes
        int[] puntajes = new int[cantidadPuntajes];

        // PARTE 1: Solicitar cada puntaje individualmente con validación
        for (int i = 0; i < cantidadPuntajes; i++) {
            int puntajeValido = 0;

            while (puntajeValido == 0) {
                String entrada = JOptionPane.showInputDialog(
                        "Ingrese el puntaje #" + (i + 1) + " (entre 0 y 100):"
                );

                // Validar que no sea nulo
                if (entrada == null) {
                    JOptionPane.showMessageDialog(null, "Operación cancelada.");
                    return;
                }

                int esNumero = 0;
                int puntaje = 0;

                // Verificar si es un número válido
                for (int j = 0; j < entrada.length(); j++) {
                    if (entrada.charAt(j) < '0' || entrada.charAt(j) > '9') {
                        esNumero = -1;
                        break;
                    }
                }

                if (esNumero != -1) {
                    puntaje = Integer.parseInt(entrada);

                    if (puntaje >= 0 && puntaje <= 100) {
                        puntajes[i] = puntaje;
                        puntajeValido = 1;
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Error: El puntaje debe estar entre 0 y 100.\nIntente nuevamente.",
                                "Entrada Inválida",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Error: Debe ingresar un número entero válido.\nIntente nuevamente.",
                            "Entrada Inválida",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }

        // PARTE 2: Procesamiento de datos y cálculos estadísticos
        int puntajeMaximo = puntajes[0];
        int puntajeMinimo = puntajes[0];
        int sumaPuntajes = 0;
        int contadorAprobados = 0;

        for (int i = 0; i < cantidadPuntajes; i++) {
            // Encontrar máximo
            if (puntajes[i] > puntajeMaximo) {
                puntajeMaximo = puntajes[i];
            }

            // Encontrar mínimo
            if (puntajes[i] < puntajeMinimo) {
                puntajeMinimo = puntajes[i];
            }

            // Sumar todos los puntajes
            sumaPuntajes += puntajes[i];

            // Contar puntajes >= 90
            if (puntajes[i] >= 90) {
                contadorAprobados++;
            }
        }

        // Calcular promedio
        double promedio = (double) sumaPuntajes / cantidadPuntajes;

        // Mostrar resultados de análisis
        JOptionPane.showMessageDialog(
                null,
                "=== ANÁLISIS ESTADÍSTICO DE PUNTAJES ===\n\n" +
                        "Cantidad total de puntajes: " + cantidadPuntajes + "\n" +
                        "Puntaje más alto: " + puntajeMaximo + "\n" +
                        "Puntaje más bajo: " + puntajeMinimo + "\n" +
                        "Promedio general: " + String.format("%.2f", promedio) + "\n" +
                        "Cantidad de puntajes >= 90: " + contadorAprobados,
                "Resultados del Análisis",
                JOptionPane.INFORMATION_MESSAGE
        );

        // PARTE 3: Menú interactivo
        int menuActivo = 1;

        while (menuActivo == 1) {
            String opcion = JOptionPane.showInputDialog(
                    "=== MENÚ INTERACTIVO ===\n\n" +
                            "1. Ver todos los puntajes\n" +
                            "2. Ver puntajes aprobados (>= 60)\n" +
                            "3. Ver puntajes reprobados (< 60)\n" +
                            "4. Salir\n\n" +
                            "Seleccione una opción (1-4):"
            );

            // Validar si el usuario canceló
            if (opcion == null) {
                menuActivo = 0;
                continue;
            }

            int esNumero = 0;
            int seleccion = 0;

            // Verificar si es un número válido
            for (int j = 0; j < opcion.length(); j++) {
                if (opcion.charAt(j) < '0' || opcion.charAt(j) > '9') {
                    esNumero = -1;
                    break;
                }
            }

            if (esNumero != -1) {
                seleccion = Integer.parseInt(opcion);

                switch (seleccion) {
                    case 1:
                        // Ver todos los puntajes
                        StringBuilder todosPuntajes = new StringBuilder("=== TODOS LOS PUNTAJES ===\n\n");
                        for (int i = 0; i < cantidadPuntajes; i++) {
                            todosPuntajes.append("Puntaje ").append(i + 1).append(": ")
                                    .append(puntajes[i]).append("\n");
                        }
                        JOptionPane.showMessageDialog(
                                null,
                                todosPuntajes.toString(),
                                "Todos los Puntajes",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        break;

                    case 2:
                        // Ver puntajes aprobados (>= 60)
                        StringBuilder aprobados = new StringBuilder("=== PUNTAJES APROBADOS (>= 60) ===\n\n");
                        int contadorAprobadosMenu = 0;
                        for (int i = 0; i < cantidadPuntajes; i++) {
                            if (puntajes[i] >= 60) {
                                aprobados.append("Puntaje ").append(i + 1).append(": ")
                                        .append(puntajes[i]).append("\n");
                                contadorAprobadosMenu++;
                            }
                        }
                        if (contadorAprobadosMenu == 0) {
                            aprobados.append("No hay puntajes aprobados.");
                        } else {
                            aprobados.append("\nTotal: ").append(contadorAprobadosMenu);
                        }
                        JOptionPane.showMessageDialog(
                                null,
                                aprobados.toString(),
                                "Puntajes Aprobados",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        break;

                    case 3:
                        // Ver puntajes reprobados (< 60)
                        StringBuilder reprobados = new StringBuilder("=== PUNTAJES REPROBADOS (< 60) ===\n\n");
                        int contadorReprobadosMenu = 0;
                        for (int i = 0; i < cantidadPuntajes; i++) {
                            if (puntajes[i] < 60) {
                                reprobados.append("Puntaje ").append(i + 1).append(": ")
                                        .append(puntajes[i]).append("\n");
                                contadorReprobadosMenu++;
                            }
                        }
                        if (contadorReprobadosMenu == 0) {
                            reprobados.append("No hay puntajes reprobados.");
                        } else {
                            reprobados.append("\nTotal: ").append(contadorReprobadosMenu);
                        }
                        JOptionPane.showMessageDialog(
                                null,
                                reprobados.toString(),
                                "Puntajes Reprobados",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        break;

                    case 4:
                        // Salir
                        menuActivo = 0;
                        JOptionPane.showMessageDialog(
                                null,
                                "¡Gracias por usar el Sistema de Análisis de Puntajes!",
                                "Hasta Luego",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        break;

                    default:
                        JOptionPane.showMessageDialog(
                                null,
                                "Error: Debe seleccionar una opción entre 1 y 4.",
                                "Opción Inválida",
                                JOptionPane.ERROR_MESSAGE
                        );
                }
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Error: Debe ingresar un número entero válido.",
                        "Entrada Inválida",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
import javax.swing.JOptionPane;

public class SistemaInventario {
    public static void main(String[] args) {

        // ==================== VARIABLES DE CONFIGURACION ====================
        int limiteProductos = 0;
        int diaOperacion = 0;
        int productosRegistrados = 0;

        // ==================== VARIABLES DE TOTALES ====================
        float totalIVA = 0;
        float totalIMC = 0;
        float totalIRT = 0;
        float totalTCE = 0;
        float montoTotalVentas = 0;

        // ==================== CONTADORES POR CATEGORIA ====================
        int contadorCat1 = 0;
        int contadorCat2 = 0;
        int contadorCat3 = 0;
        int contadorCat4 = 0;

        // ==================== PRODUCTO CON MAYOR PRECIO FINAL ====================
        String nombreProductoMayorPrecio = "";
        float mayorPrecioFinal = 0;

        // ==================== CONSTANTES DE IMPUESTOS ====================
        float TASA_IVA = 0.12f;
        float TASA_IMC = 0.015f;
        float TASA_IRT = 0.037f;
        float TASA_TCE = 0.02f;

        // ==================== ARRAYS PARA RANKING ====================
        int MAX_PRODUCTOS = 100;
        String[] nombresProductos = new String[MAX_PRODUCTOS];
        float[] preciosFinales = new float[MAX_PRODUCTOS];
        int[] stocksProductos = new int[MAX_PRODUCTOS];

        // ==================== ENCABEZADO ====================
        JOptionPane.showMessageDialog(null,
                "===============================================\n" +
                        "   SISTEMA DE GESTION DE INVENTARIO AVANZADO\n" +
                        "              TecnoLogix Global\n" +
                        "===============================================\n\n" +
                        "        Version con Interfaz Grafica",
                "TecnoLogix Global",
                JOptionPane.INFORMATION_MESSAGE);

        // ==================== CONFIGURACION INICIAL DEL DIA ====================

        // Solicitar limite de productos con validacion
        int entradaValida = 0;
        while (entradaValida == 0) {
            String inputLimite = JOptionPane.showInputDialog(null,
                    "Ingrese el limite maximo de productos a registrar:",
                    "Configuracion Inicial",
                    JOptionPane.QUESTION_MESSAGE);

            if (inputLimite == null) {
                JOptionPane.showMessageDialog(null, "Operacion cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                limiteProductos = Integer.parseInt(inputLimite);
                if (limiteProductos > 0) {
                    entradaValida = 1;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Error: El limite debe ser mayor a 0.",
                            "Error de Validacion",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Error: Ingrese un numero entero valido.",
                        "Error de Validacion",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        // Solicitar dia de operacion con validacion
        entradaValida = 0;
        while (entradaValida == 0) {
            String inputDia = JOptionPane.showInputDialog(null,
                    "Seleccione el dia de operacion:\n\n" +
                            "  1 = Lunes\n" +
                            "  2 = Martes\n" +
                            "  3 = Miercoles\n" +
                            "  4 = Jueves\n" +
                            "  5 = Viernes\n" +
                            "  6 = Sabado\n" +
                            "  7 = Domingo\n\n" +
                            "Ingrese el numero del dia (1-7):",
                    "Dia de Operacion",
                    JOptionPane.QUESTION_MESSAGE);

            if (inputDia == null) {
                JOptionPane.showMessageDialog(null, "Operacion cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                diaOperacion = Integer.parseInt(inputDia);
                if (diaOperacion >= 1 && diaOperacion <= 7) {
                    entradaValida = 1;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Error: Ingrese un valor entre 1 y 7.",
                            "Error de Validacion",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Error: Ingrese un numero entero valido.",
                        "Error de Validacion",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        // Obtener nombre del dia
        String nombreDia = "";
        if (diaOperacion == 1) {
            nombreDia = "Lunes";
        } else if (diaOperacion == 2) {
            nombreDia = "Martes";
        } else if (diaOperacion == 3) {
            nombreDia = "Miercoles";
        } else if (diaOperacion == 4) {
            nombreDia = "Jueves";
        } else if (diaOperacion == 5) {
            nombreDia = "Viernes";
        } else if (diaOperacion == 6) {
            nombreDia = "Sabado";
        } else if (diaOperacion == 7) {
            nombreDia = "Domingo";
        }

        // Verificar si aplica TCE
        int aplicaTCE = 0;
        if (diaOperacion == 6 || diaOperacion == 7) {
            aplicaTCE = 1;
            JOptionPane.showMessageDialog(null,
                    "AVISO IMPORTANTE\n\n" +
                            "Dia de operacion: " + nombreDia + "\n\n" +
                            "Se aplicara Tasa de Control Especial (TCE) del 2%\n" +
                            "a todos los productos registrados hoy.",
                    "Tasa Especial Activada",
                    JOptionPane.WARNING_MESSAGE);
        }

        JOptionPane.showMessageDialog(null,
                "Configuracion completada:\n\n" +
                        "Dia de operacion: " + nombreDia + "\n" +
                        "Limite de productos: " + limiteProductos + "\n" +
                        "TCE aplicable: " + (aplicaTCE == 1 ? "Si (2%)" : "No"),
                "Resumen de Configuracion",
                JOptionPane.INFORMATION_MESSAGE);

        // ==================== REGISTRO DE PRODUCTOS ====================
        int continuar = 1;

        while (continuar == 1 && productosRegistrados < limiteProductos) {

            // Variables del producto
            String nombreProducto = "";
            float precioBase = 0;
            int categoria = 0;
            int riesgo = 0;
            int stock = 0;

            // Solicitar nombre del producto
            nombreProducto = JOptionPane.showInputDialog(null,
                    "PRODUCTO #" + (productosRegistrados + 1) + "\n\n" +
                            "Ingrese el nombre del producto:",
                    "Registro de Producto",
                    JOptionPane.QUESTION_MESSAGE);

            if (nombreProducto == null || nombreProducto.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Operacion cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                break;
            }

            // Solicitar precio base con validacion
            entradaValida = 0;
            while (entradaValida == 0) {
                String inputPrecio = JOptionPane.showInputDialog(null,
                        "Producto: " + nombreProducto + "\n\n" +
                                "Ingrese el precio base ($):",
                        "Precio Base",
                        JOptionPane.QUESTION_MESSAGE);

                if (inputPrecio == null) {
                    JOptionPane.showMessageDialog(null, "Operacion cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    precioBase = Float.parseFloat(inputPrecio);
                    if (precioBase > 0) {
                        entradaValida = 1;
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Error: El precio debe ser mayor a 0.",
                                "Error de Validacion",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Ingrese un numero valido.",
                            "Error de Validacion",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            // Solicitar categoria con validacion
            entradaValida = 0;
            while (entradaValida == 0) {
                String inputCategoria = JOptionPane.showInputDialog(null,
                        "Producto: " + nombreProducto + "\n" +
                                "Precio: $" + String.format("%.2f", precioBase) + "\n\n" +
                                "Seleccione la categoria:\n\n" +
                                "  1 = Electronica personal\n" +
                                "  2 = Componentes de computo\n" +
                                "  3 = Accesorios (exentos de IVA)\n" +
                                "  4 = Redes y telecomunicaciones\n\n" +
                                "Ingrese el numero de categoria (1-4):",
                        "Categoria del Producto",
                        JOptionPane.QUESTION_MESSAGE);

                if (inputCategoria == null) {
                    JOptionPane.showMessageDialog(null, "Operacion cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    categoria = Integer.parseInt(inputCategoria);
                    if (categoria >= 1 && categoria <= 4) {
                        entradaValida = 1;
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Error: Ingrese un valor entre 1 y 4.",
                                "Error de Validacion",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Ingrese un numero entero valido.",
                            "Error de Validacion",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            // Obtener nombre de categoria
            String nombreCategoria = "";
            if (categoria == 1) {
                nombreCategoria = "Electronica personal";
            } else if (categoria == 2) {
                nombreCategoria = "Componentes de computo";
            } else if (categoria == 3) {
                nombreCategoria = "Accesorios";
            } else if (categoria == 4) {
                nombreCategoria = "Redes y telecomunicaciones";
            }

            // Solicitar riesgo tecnologico con validacion
            entradaValida = 0;
            while (entradaValida == 0) {
                String inputRiesgo = JOptionPane.showInputDialog(null,
                        "Producto: " + nombreProducto + "\n" +
                                "Categoria: " + nombreCategoria + "\n\n" +
                                "El producto tiene riesgo tecnologico?\n\n" +
                                "  1 = Si\n" +
                                "  0 = No\n\n" +
                                "Ingrese su opcion (0 o 1):",
                        "Riesgo Tecnologico",
                        JOptionPane.QUESTION_MESSAGE);

                if (inputRiesgo == null) {
                    JOptionPane.showMessageDialog(null, "Operacion cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    riesgo = Integer.parseInt(inputRiesgo);
                    if (riesgo == 0 || riesgo == 1) {
                        entradaValida = 1;
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Error: Ingrese 0 o 1.",
                                "Error de Validacion",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Ingrese un numero entero valido.",
                            "Error de Validacion",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            // Solicitar stock con validacion
            entradaValida = 0;
            while (entradaValida == 0) {
                String inputStock = JOptionPane.showInputDialog(null,
                        "Producto: " + nombreProducto + "\n\n" +
                                "Ingrese la cantidad de stock (minimo 1):",
                        "Stock del Producto",
                        JOptionPane.QUESTION_MESSAGE);

                if (inputStock == null) {
                    JOptionPane.showMessageDialog(null, "Operacion cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    stock = Integer.parseInt(inputStock);
                    if (stock >= 1) {
                        entradaValida = 1;
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Error: El stock debe ser minimo 1.",
                                "Error de Validacion",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Ingrese un numero entero valido.",
                            "Error de Validacion",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            // ==================== CALCULO DE IMPUESTOS ====================
            float iva = 0;
            float imc = 0;
            float irt = 0;
            float tce = 0;
            float precioFinal = 0;

            if (categoria != 3) {
                iva = precioBase * TASA_IVA;
            } else {
                iva = 0;
            }

            imc = precioBase * TASA_IMC;

            if (riesgo == 1) {
                irt = precioBase * TASA_IRT;
            } else {
                irt = 0;
            }

            if (aplicaTCE == 1) {
                tce = precioBase * TASA_TCE;
            } else {
                tce = 0;
            }

            precioFinal = precioBase + iva + imc + irt + tce;

            // ==================== ACUMULAR TOTALES ====================
            totalIVA = totalIVA + (iva * stock);
            totalIMC = totalIMC + (imc * stock);
            totalIRT = totalIRT + (irt * stock);
            totalTCE = totalTCE + (tce * stock);
            montoTotalVentas = montoTotalVentas + (precioFinal * stock);

            if (categoria == 1) {
                contadorCat1 = contadorCat1 + 1;
            } else if (categoria == 2) {
                contadorCat2 = contadorCat2 + 1;
            } else if (categoria == 3) {
                contadorCat3 = contadorCat3 + 1;
            } else if (categoria == 4) {
                contadorCat4 = contadorCat4 + 1;
            }

            if (precioFinal > mayorPrecioFinal) {
                mayorPrecioFinal = precioFinal;
                nombreProductoMayorPrecio = nombreProducto;
            }

            // Guardar datos para el ranking
            nombresProductos[productosRegistrados] = nombreProducto;
            preciosFinales[productosRegistrados] = precioFinal;
            stocksProductos[productosRegistrados] = stock;

            productosRegistrados = productosRegistrados + 1;

            // ==================== REPORTE DEL PRODUCTO ====================
            String reporteProducto =
                    "========================================\n" +
                            "       REPORTE DEL PRODUCTO #" + productosRegistrados + "\n" +
                            "========================================\n\n" +
                            "Producto:      " + nombreProducto + "\n" +
                            "Categoria:     " + nombreCategoria + "\n" +
                            "Stock:         " + stock + " unidades\n" +
                            "Riesgo:        " + (riesgo == 1 ? "Si" : "No") + "\n\n" +
                            "----------------------------------------\n" +
                            "DESGLOSE DE IMPUESTOS\n" +
                            "----------------------------------------\n\n" +
                            "Precio base:   $" + String.format("%.2f", precioBase) + "\n" +
                            "IVA (12%):     $" + String.format("%.2f", iva) +
                            (categoria == 3 ? " (Exento)" : "") + "\n" +
                            "IMC (1.5%):    $" + String.format("%.2f", imc) + "\n" +
                            "IRT (3.7%):    $" + String.format("%.2f", irt) +
                            (riesgo == 0 ? " (No aplica)" : "") + "\n" +
                            "TCE (2%):      $" + String.format("%.2f", tce) +
                            (aplicaTCE == 0 ? " (No aplica)" : "") + "\n\n" +
                            "----------------------------------------\n" +
                            "PRECIO FINAL:  $" + String.format("%.2f", precioFinal) + "\n" +
                            "TOTAL (x" + stock + "):   $" + String.format("%.2f", (precioFinal * stock)) + "\n" +
                            "----------------------------------------";

            JOptionPane.showMessageDialog(null,
                    reporteProducto,
                    "Producto Registrado Exitosamente",
                    JOptionPane.INFORMATION_MESSAGE);

            // Verificar si se alcanzo el limite
            if (productosRegistrados >= limiteProductos) {
                JOptionPane.showMessageDialog(null,
                        "Se ha alcanzado el limite diario de " + limiteProductos + " productos.\n\n" +
                                "El registro de productos ha finalizado.",
                        "Limite Alcanzado",
                        JOptionPane.WARNING_MESSAGE);
                continuar = 0;
            } else {
                // Preguntar si desea continuar
                int respuesta = JOptionPane.showConfirmDialog(null,
                        "Productos registrados: " + productosRegistrados + " de " + limiteProductos + "\n\n" +
                                "Desea registrar otro producto?",
                        "Continuar Registro",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (respuesta == JOptionPane.YES_OPTION) {
                    continuar = 1;
                } else {
                    continuar = 0;
                }
            }
        }

        // ==================== DETERMINAR CATEGORIA CON MAS PRODUCTOS ====================
        String categoriaMayorProductos = "";
        int maxProductosCategoria = 0;

        if (contadorCat1 >= contadorCat2 && contadorCat1 >= contadorCat3 && contadorCat1 >= contadorCat4) {
            categoriaMayorProductos = "Electronica personal";
            maxProductosCategoria = contadorCat1;
        } else if (contadorCat2 >= contadorCat1 && contadorCat2 >= contadorCat3 && contadorCat2 >= contadorCat4) {
            categoriaMayorProductos = "Componentes de computo";
            maxProductosCategoria = contadorCat2;
        } else if (contadorCat3 >= contadorCat1 && contadorCat3 >= contadorCat2 && contadorCat3 >= contadorCat4) {
            categoriaMayorProductos = "Accesorios";
            maxProductosCategoria = contadorCat3;
        } else {
            categoriaMayorProductos = "Redes y telecomunicaciones";
            maxProductosCategoria = contadorCat4;
        }

        // ==================== ORDENAR PRODUCTOS PARA RANKING ====================
        // Ordenar por precio final (burbuja descendente)
        for (int i = 0; i < productosRegistrados - 1; i++) {
            for (int j = 0; j < productosRegistrados - i - 1; j++) {
                if (preciosFinales[j] < preciosFinales[j + 1]) {
                    float tempPrecio = preciosFinales[j];
                    preciosFinales[j] = preciosFinales[j + 1];
                    preciosFinales[j + 1] = tempPrecio;

                    String tempNombre = nombresProductos[j];
                    nombresProductos[j] = nombresProductos[j + 1];
                    nombresProductos[j + 1] = tempNombre;

                    int tempStock = stocksProductos[j];
                    stocksProductos[j] = stocksProductos[j + 1];
                    stocksProductos[j + 1] = tempStock;
                }
            }
        }

        // ==================== REPORTE FINAL - PARTE 1: RESUMEN ====================
        float totalImpuestos = totalIVA + totalIMC + totalIRT + totalTCE;

        String reporteFinal1 =
                "================================================\n" +
                        "           REPORTE FINAL DEL DIA\n" +
                        "                " + nombreDia.toUpperCase() + "\n" +
                        "================================================\n\n" +
                        "--- RESUMEN DE OPERACIONES ---\n\n" +
                        "Total de productos registrados: " + productosRegistrados + "\n\n" +
                        "--- DESGLOSE DE IMPUESTOS ---\n\n" +
                        "Total IVA recaudado:    $" + String.format("%.2f", totalIVA) + "\n" +
                        "Total IMC recaudado:    $" + String.format("%.2f", totalIMC) + "\n" +
                        "Total IRT recaudado:    $" + String.format("%.2f", totalIRT) + "\n" +
                        "Total TCE recaudado:    $" + String.format("%.2f", totalTCE) + "\n\n" +
                        "TOTAL IMPUESTOS:        $" + String.format("%.2f", totalImpuestos) + "\n\n" +
                        "--- MONTO TOTAL DE VENTAS ---\n\n" +
                        "TOTAL ACUMULADO:        $" + String.format("%.2f", montoTotalVentas);

        JOptionPane.showMessageDialog(null,
                reporteFinal1,
                "Reporte Final - Resumen Financiero",
                JOptionPane.INFORMATION_MESSAGE);

        // ==================== REPORTE FINAL - PARTE 2: ESTADISTICAS ====================
        String reporteFinal2 =
                "================================================\n" +
                        "         ESTADISTICAS POR CATEGORIA\n" +
                        "================================================\n\n" +
                        "Electronica personal:        " + contadorCat1 + " productos\n" +
                        "Componentes de computo:      " + contadorCat2 + " productos\n" +
                        "Accesorios:                  " + contadorCat3 + " productos\n" +
                        "Redes y telecomunicaciones:  " + contadorCat4 + " productos\n\n" +
                        "------------------------------------------------\n\n" +
                        "CATEGORIA CON MAS PRODUCTOS:\n" +
                        (productosRegistrados > 0 ?
                                "  " + categoriaMayorProductos + " (" + maxProductosCategoria + " productos)" :
                                "  No se registraron productos") + "\n\n" +
                        "------------------------------------------------\n\n" +
                        "PRODUCTO CON MAYOR PRECIO FINAL:\n" +
                        (productosRegistrados > 0 ?
                                "  " + nombreProductoMayorPrecio + "\n  Precio: $" + String.format("%.2f", mayorPrecioFinal) :
                                "  No se registraron productos");

        JOptionPane.showMessageDialog(null,
                reporteFinal2,
                "Reporte Final - Estadisticas",
                JOptionPane.INFORMATION_MESSAGE);

        // ==================== REPORTE FINAL - PARTE 3: RANKING ====================
        if (productosRegistrados > 0) {
            String rankingPrecio =
                    "================================================\n" +
                            "    RANKING DE PRODUCTOS (PRECIO FINAL)\n" +
                            "================================================\n\n";

            for (int i = 0; i < productosRegistrados; i++) {
                String posicion = "";
                if (i == 0) {
                    posicion = "1ro";
                } else if (i == 1) {
                    posicion = "2do";
                } else if (i == 2) {
                    posicion = "3ro";
                } else {
                    posicion = (i + 1) + "to";
                }

                rankingPrecio = rankingPrecio +
                        posicion + ". " + nombresProductos[i] + "\n" +
                        "    Precio: $" + String.format("%.2f", preciosFinales[i]) +
                        "  |  Stock: " + stocksProductos[i] + "\n\n";
            }

            JOptionPane.showMessageDialog(null,
                    rankingPrecio,
                    "Ranking por Precio Final",
                    JOptionPane.INFORMATION_MESSAGE);

            // Ordenar por stock para el segundo ranking
            String[] nombresOrdenadosStock = new String[productosRegistrados];
            float[] preciosOrdenadosStock = new float[productosRegistrados];
            int[] stocksOrdenados = new int[productosRegistrados];

            for (int i = 0; i < productosRegistrados; i++) {
                nombresOrdenadosStock[i] = nombresProductos[i];
                preciosOrdenadosStock[i] = preciosFinales[i];
                stocksOrdenados[i] = stocksProductos[i];
            }

            for (int i = 0; i < productosRegistrados - 1; i++) {
                for (int j = 0; j < productosRegistrados - i - 1; j++) {
                    if (stocksOrdenados[j] < stocksOrdenados[j + 1]) {
                        int tempStock = stocksOrdenados[j];
                        stocksOrdenados[j] = stocksOrdenados[j + 1];
                        stocksOrdenados[j + 1] = tempStock;

                        String tempNombre = nombresOrdenadosStock[j];
                        nombresOrdenadosStock[j] = nombresOrdenadosStock[j + 1];
                        nombresOrdenadosStock[j + 1] = tempNombre;

                        float tempPrecio = preciosOrdenadosStock[j];
                        preciosOrdenadosStock[j] = preciosOrdenadosStock[j + 1];
                        preciosOrdenadosStock[j + 1] = tempPrecio;
                    }
                }
            }

            String rankingStock =
                    "================================================\n" +
                            "      RANKING DE PRODUCTOS (POR STOCK)\n" +
                            "================================================\n\n";

            for (int i = 0; i < productosRegistrados; i++) {
                String posicion = "";
                if (i == 0) {
                    posicion = "1ro";
                } else if (i == 1) {
                    posicion = "2do";
                } else if (i == 2) {
                    posicion = "3ro";
                } else {
                    posicion = (i + 1) + "to";
                }

                rankingStock = rankingStock +
                        posicion + ". " + nombresOrdenadosStock[i] + "\n" +
                        "    Stock: " + stocksOrdenados[i] +
                        "  |  Precio: $" + String.format("%.2f", preciosOrdenadosStock[i]) + "\n\n";
            }

            JOptionPane.showMessageDialog(null,
                    rankingStock,
                    "Ranking por Stock",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        // ==================== MENSAJE FINAL ====================
        JOptionPane.showMessageDialog(null,
                "================================================\n" +
                        "                  FIN DEL REPORTE\n" +
                        "               TecnoLogix Global\n" +
                        "================================================\n\n" +
                        "Gracias por utilizar el Sistema de Gestion\n" +
                        "de Inventario Avanzado.\n\n" +
                        "Dia procesado: " + nombreDia + "\n" +
                        "Productos registrados: " + productosRegistrados + "\n" +
                        "Ventas totales: $" + String.format("%.2f", montoTotalVentas),
                "Operacion Finalizada",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

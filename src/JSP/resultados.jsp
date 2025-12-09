<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EduTech Games - Resultados</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        
        .container {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 20px;
            padding: 40px;
            box-shadow: 0 25px 50px rgba(0, 0, 0, 0.3);
            max-width: 700px;
            width: 100%;
        }
        
        .header {
            text-align: center;
            margin-bottom: 30px;
        }
        
        .header h1 {
            color: #1a1a2e;
            font-size: 28px;
            margin-bottom: 10px;
        }
        
        .header p {
            color: #666;
            font-size: 14px;
        }
        
        .success-icon {
            width: 70px;
            height: 70px;
            background: linear-gradient(135deg, #28a745, #20c997);
            border-radius: 50%;
            margin: 0 auto 20px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 32px;
            color: white;
        }
        
        .stats-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
            margin-bottom: 30px;
        }
        
        .stat-card {
            background: linear-gradient(135deg, #f8f9fa, #e9ecef);
            border-radius: 15px;
            padding: 25px;
            text-align: center;
            transition: transform 0.3s ease;
        }
        
        .stat-card:hover {
            transform: translateY(-5px);
        }
        
        .stat-card.max {
            background: linear-gradient(135deg, #d4edda, #c3e6cb);
            border: 2px solid #28a745;
        }
        
        .stat-card.min {
            background: linear-gradient(135deg, #f8d7da, #f5c6cb);
            border: 2px solid #dc3545;
        }
        
        .stat-card.avg {
            background: linear-gradient(135deg, #cce5ff, #b8daff);
            border: 2px solid #007bff;
        }
        
        .stat-card.count {
            background: linear-gradient(135deg, #fff3cd, #ffeeba);
            border: 2px solid #ffc107;
        }
        
        .stat-card .icon {
            font-size: 28px;
            margin-bottom: 10px;
        }
        
        .stat-card .value {
            font-size: 32px;
            font-weight: 700;
            color: #1a1a2e;
            margin-bottom: 5px;
        }
        
        .stat-card .label {
            font-size: 12px;
            color: #666;
            text-transform: uppercase;
            letter-spacing: 1px;
        }
        
        .puntajes-section {
            background: #f8f9fa;
            border-radius: 15px;
            padding: 25px;
            margin-bottom: 25px;
        }
        
        .puntajes-section h3 {
            color: #333;
            margin-bottom: 15px;
            font-size: 16px;
        }
        
        .puntajes-grid {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
        }
        
        .puntaje-badge {
            padding: 8px 16px;
            border-radius: 20px;
            font-size: 14px;
            font-weight: 600;
        }
        
        .puntaje-badge.aprobado {
            background: #d4edda;
            color: #155724;
        }
        
        .puntaje-badge.reprobado {
            background: #f8d7da;
            color: #721c24;
        }
        
        .puntaje-badge.destacado {
            background: linear-gradient(135deg, #ffd700, #ffb300);
            color: #333;
        }
        
        .btn {
            display: inline-block;
            padding: 16px 30px;
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: white;
            border: none;
            border-radius: 10px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            text-decoration: none;
            transition: all 0.3s ease;
            text-align: center;
        }
        
        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 10px 25px rgba(102, 126, 234, 0.4);
        }
        
        .btn-container {
            text-align: center;
        }
        
        .error-box {
            background: #f8d7da;
            border-left: 4px solid #dc3545;
            padding: 15px;
            border-radius: 0 10px 10px 0;
            margin-bottom: 25px;
        }
        
        .error-box p {
            color: #721c24;
            font-size: 13px;
        }
    </style>
</head>
<body>
    <div class="container">
        <%
            // Obtener cantidad de puntajes
            String cantidadStr = request.getParameter("cantidad");
            int cantidad = 0;
            String error = "";
            
            // Validar cantidad
            if (cantidadStr != null && !cantidadStr.isEmpty()) {
                cantidad = Integer.parseInt(cantidadStr);
            } else {
                error = "No se recibieron datos. Por favor, inicie el proceso nuevamente.";
            }
            
            if (error.isEmpty() && cantidad > 0) {
                // Crear arreglo para almacenar puntajes
                int[] puntajes = new int[cantidad];
                
                // Ciclo FOR para obtener los puntajes del formulario
                for (int i = 1; i <= cantidad; i++) {
                    String puntajeStr = request.getParameter("puntaje" + i);
                    if (puntajeStr != null && !puntajeStr.isEmpty()) {
                        int puntaje = Integer.parseInt(puntajeStr);
                        
                        // Validar rango 0-100
                        if (puntaje < 0) {
                            puntaje = 0;
                        }
                        if (puntaje > 100) {
                            puntaje = 100;
                        }
                        
                        puntajes[i - 1] = puntaje;
                    } else {
                        puntajes[i - 1] = 0;
                    }
                }
                
                // Calcular estadisticas usando estructuras basicas
                
                // Variables para los calculos
                int maximo = puntajes[0];
                int minimo = puntajes[0];
                int suma = 0;
                int cantidadMayores50 = 0;
                int cantidadAprobados = 0;
                int cantidadReprobados = 0;
                int cantidadDestacados = 0;
                
                // Ciclo FOR para calcular estadisticas
                for (int i = 0; i < cantidad; i++) {
                    // Sumar para el promedio
                    suma = suma + puntajes[i];
                    
                    // Buscar maximo
                    if (puntajes[i] > maximo) {
                        maximo = puntajes[i];
                    }
                    
                    // Buscar minimo
                    if (puntajes[i] < minimo) {
                        minimo = puntajes[i];
                    }
                    
                    // Contar mayores a 50
                    if (puntajes[i] > 50) {
                        cantidadMayores50 = cantidadMayores50 + 1;
                    }
                    
                    // Contar aprobados (>= 60)
                    if (puntajes[i] >= 60) {
                        cantidadAprobados = cantidadAprobados + 1;
                    } else {
                        cantidadReprobados = cantidadReprobados + 1;
                    }
                    
                    // Contar destacados (>= 90)
                    if (puntajes[i] >= 90) {
                        cantidadDestacados = cantidadDestacados + 1;
                    }
                }
                
                // Calcular promedio
                double promedio = (double) suma / cantidad;
        %>
        
        <div class="header">
            <div class="success-icon">✓</div>
            <h1>Resultados del Analisis</h1>
            <p>Se procesaron <%= cantidad %> puntajes exitosamente</p>
        </div>
        
        <div class="stats-grid">
            <div class="stat-card max">
                <div class="icon">📈</div>
                <div class="value"><%= maximo %></div>
                <div class="label">Puntaje Maximo</div>
            </div>
            
            <div class="stat-card min">
                <div class="icon">📉</div>
                <div class="value"><%= minimo %></div>
                <div class="label">Puntaje Minimo</div>
            </div>
            
            <div class="stat-card avg">
                <div class="icon">📊</div>
                <div class="value"><%= String.format("%.2f", promedio) %></div>
                <div class="label">Promedio General</div>
            </div>
            
            <div class="stat-card count">
                <div class="icon">⭐</div>
                <div class="value"><%= cantidadMayores50 %></div>
                <div class="label">Mayores a 50</div>
            </div>
        </div>
        
        <div class="puntajes-section">
            <h3>Detalle de Puntajes Registrados</h3>
            <div class="puntajes-grid">
                <%
                    // Ciclo FOR para mostrar cada puntaje con su clasificacion
                    for (int i = 0; i < cantidad; i++) {
                        String clase = "";
                        
                        // Clasificar usando IF-ELSE
                        if (puntajes[i] >= 90) {
                            clase = "destacado";
                        } else if (puntajes[i] >= 60) {
                            clase = "aprobado";
                        } else {
                            clase = "reprobado";
                        }
                %>
                <span class="puntaje-badge <%= clase %>">
                    #<%= (i + 1) %>: <%= puntajes[i] %> pts
                </span>
                <%
                    }
                %>
            </div>
        </div>
        
        <div class="puntajes-section">
            <h3>Resumen de Clasificacion</h3>
            <p style="color: #666; line-height: 1.8;">
                <strong style="color: #28a745;">Aprobados (>= 60):</strong> <%= cantidadAprobados %> estudiantes<br>
                <strong style="color: #dc3545;">Reprobados (< 60):</strong> <%= cantidadReprobados %> estudiantes<br>
                <strong style="color: #ffc107;">Destacados (>= 90):</strong> <%= cantidadDestacados %> estudiantes
            </p>
        </div>
        
        <div class="btn-container">
            <a href="index.jsp" class="btn">Nuevo Registro</a>
        </div>
        
        <%
            } else {
        %>
        <div class="header">
            <h1>Error</h1>
        </div>
        <div class="error-box">
            <p><%= error %></p>
        </div>
        <div class="btn-container">
            <a href="index.jsp" class="btn">Volver al Inicio</a>
        </div>
        <%
            }
        %>
    </div>
</body>
</html>
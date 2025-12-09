<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EduTech Games - Ingresar Puntajes</title>
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
            max-width: 600px;
            width: 100%;
        }
        
        .header {
            text-align: center;
            margin-bottom: 30px;
        }
        
        .header h1 {
            color: #1a1a2e;
            font-size: 24px;
            margin-bottom: 10px;
        }
        
        .header p {
            color: #666;
            font-size: 14px;
        }
        
        .badge {
            display: inline-block;
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: white;
            padding: 8px 20px;
            border-radius: 20px;
            font-size: 14px;
            margin-bottom: 20px;
        }
        
        .form-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(120px, 1fr));
            gap: 15px;
            margin-bottom: 25px;
        }
        
        .form-group {
            position: relative;
        }
        
        .form-group label {
            display: block;
            color: #333;
            font-weight: 600;
            margin-bottom: 5px;
            font-size: 12px;
            text-align: center;
        }
        
        .form-group input {
            width: 100%;
            padding: 12px;
            border: 2px solid #e0e0e0;
            border-radius: 10px;
            font-size: 16px;
            text-align: center;
            transition: all 0.3s ease;
        }
        
        .form-group input:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.2);
        }
        
        .info-box {
            background: #fff3cd;
            border-left: 4px solid #ffc107;
            padding: 15px;
            border-radius: 0 10px 10px 0;
            margin-bottom: 25px;
        }
        
        .info-box p {
            color: #856404;
            font-size: 13px;
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
        
        .btn {
            width: 100%;
            padding: 16px;
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: white;
            border: none;
            border-radius: 10px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        
        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 10px 25px rgba(102, 126, 234, 0.4);
        }
        
        .btn-secondary {
            background: #6c757d;
            margin-top: 10px;
        }
        
        .btn-secondary:hover {
            box-shadow: 0 10px 25px rgba(108, 117, 125, 0.4);
        }
    </style>
</head>
<body>
    <div class="container">
        <%
            // Obtener la cantidad de puntajes del formulario anterior
            String cantidadStr = request.getParameter("cantidad");
            int cantidad = 0;
            String error = "";
            
            // Validar que el parametro no sea nulo
            if (cantidadStr != null && !cantidadStr.isEmpty()) {
                cantidad = Integer.parseInt(cantidadStr);
                
                // Validar rango entre 5 y 15
                if (cantidad < 5 || cantidad > 15) {
                    error = "La cantidad debe estar entre 5 y 15. Valor ingresado: " + cantidad;
                    cantidad = 0;
                }
            } else {
                error = "Debe ingresar una cantidad valida.";
            }
            
            if (!error.isEmpty()) {
        %>
        <div class="header">
            <h1>Error de Validacion</h1>
        </div>
        <div class="error-box">
            <p><%= error %></p>
        </div>
        <a href="index.jsp"><button type="button" class="btn">Volver al Inicio</button></a>
        <%
            } else {
        %>
        <div class="header">
            <span class="badge">Paso 2 de 2</span>
            <h1>Ingresar Puntajes</h1>
            <p>Registrando <%= cantidad %> puntajes</p>
        </div>
        
        <form action="resultados.jsp" method="POST">
            <input type="hidden" name="cantidad" value="<%= cantidad %>">
            
            <div class="info-box">
                <p><strong>Nota:</strong> Cada puntaje debe estar entre 0 y 100 puntos.</p>
            </div>
            
            <div class="form-grid">
                <%
                    // Ciclo FOR para generar los campos de entrada
                    for (int i = 1; i <= cantidad; i++) {
                %>
                <div class="form-group">
                    <label for="puntaje<%= i %>">Puntaje <%= i %></label>
                    <input type="number" id="puntaje<%= i %>" name="puntaje<%= i %>" 
                           min="0" max="100" required placeholder="0-100">
                </div>
                <%
                    }
                %>
            </div>
            
            <button type="submit" class="btn">Procesar Puntajes</button>
            <a href="index.jsp"><button type="button" class="btn btn-secondary">Cancelar</button></a>
        </form>
        <%
            }
        %>
    </div>
</body>
</html>
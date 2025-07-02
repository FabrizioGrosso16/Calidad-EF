<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Iniciar Sesión Mitienda</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
    <style>
        .form-container {
            border: 4px solid #dc3545;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            transition: border-color 0.3s ease;
            max-width: 400px;
            margin: auto;
        }

        .form-container.success {
            border-color: #28a745;
        }

        .form-container.error {
            border-color: #dc3545;
        }

        .btn-outline-danger {
            transition: all 0.3s ease-in-out;
        }

        .btn-outline-danger:hover {
            background-color: #dc3545;
            color: white;
        }

        .animated-icon {
            animation: bounce 1s infinite;
        }

        @keyframes bounce {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(-5px); }
        }

        body {
            background-color: #f8f9fa;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
    </style>
</head>
<body>

<%
    String error = (String) request.getAttribute("error");
    String cssClass = "";

    if (error != null && !error.isEmpty()) {
        cssClass = "error";
    } else {
        cssClass = "success";
    }
%>

<div class="form-container <%= cssClass %>">
    <h3 class="text-center mb-4">
        <i class="fa-solid fa-store text-primary animated-icon"></i>
        Iniciar Sesión
    </h3>

    <% if (error != null) { %>
        <div class="alert alert-danger" role="alert">
            <%= error %>
        </div>
    <% } %>

    <form action="LoginServlet" method="post">
        <div class="mb-3">
            <label for="correo" class="form-label">Correo</label>
            <input type="text" id="correo" name="correo" class="form-control" required placeholder="Ingresa tu usuario o correo" />
        </div>
        <div class="mb-3">
            <label for="pss" class="form-label">Contraseña</label>
            <input type="password" id="pss" name="pss" class="form-control" required placeholder="Ingresa tu contraseña" />
        </div>

        <!-- CAPTCHA -->
        <div class="mb-3">
            <div class="g-recaptcha" data-sitekey="6LdIZkkrAAAAAJpVwEKxINeQKOHNLjNNGaVd11_2"></div>
        </div>

        <button type="submit" class="btn btn-outline-danger w-100 d-flex align-items-center justify-content-center">
            <i class="fa-solid fa-lock me-2"></i> Iniciar Sesión
        </button>
    </form>
</div>

<!-- Script del CAPTCHA de Google -->
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
</body>
</html>

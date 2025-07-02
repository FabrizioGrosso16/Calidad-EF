<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin Panel - MiTienda_Darsol</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Style/Style.css">

    <style>
        body {
            background-image: url('https://www.shutterstock.com/image-photo/grocery-store-interior-defocused-backdrop-260nw-2467551709.jpg'); /* Imagen de minimarket */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            height: 100vh;
            color: white;
        }
        .welcome-container {
            background-color: rgba(0, 0, 0, 0.5); /* Fondo semi-transparente para mejor lectura */
            padding: 2rem;
            border-radius: 10px;
            max-width: 600px;
            margin: 5rem auto;
            text-align: center;
            box-shadow: 0 0 15px rgba(0,0,0,0.6);
        }
    </style>
</head>
<body>
   <div class="d-flex">
    <!-- Sidebar -->
    <jsp:include page="/Admin/Componentes/sidebar.jsp" />

    <!-- Contenido principal -->
    <div class="welcome-container animate__animated animate__fadeIn">
        <h1>Bienvenido a Mi Tienda Darsol</h1>
        <p>Gestiona tus productos fácilmente desde aquí.</p>
        <i class="fas fa-store fa-3x"></i>
    </div>
   </div>

    <!-- JS: Bootstrap Bundle + Animación opcional -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Agregar animación al mostrar/ocultar el menú de navegación -->
    <script>
        const navbarCollapse = document.getElementById('navbarNav');

        navbarCollapse.addEventListener('show.bs.collapse', () => {
            navbarCollapse.classList.add('animate__animated', 'animate__fadeIn');
            navbarCollapse.classList.remove('animate__fadeOut');
        });

        navbarCollapse.addEventListener('hide.bs.collapse', () => {
            navbarCollapse.classList.remove('animate__fadeIn');
            navbarCollapse.classList.add('animate__animated', 'animate__fadeOut');
        });
    </script>
</body>
</html>

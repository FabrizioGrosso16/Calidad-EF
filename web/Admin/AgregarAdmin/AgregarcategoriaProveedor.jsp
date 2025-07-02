<%-- 
    Document   : AgregarcategoriaProveedor
    Created on : 20 may. 2025, 23:53:02
    Author     : User001
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Categoría de Proveedor</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Style/Style.css">
</head>
<body>

<div class="d-flex">
    <!-- Sidebar izquierdo -->
    <jsp:include page="/Admin/Componentes/sidebar.jsp" />

    <!-- Contenido principal -->
    <div class="container mt-5">
        <h2 class="mb-4">Agregar Nueva Categoría de Proveedor</h2>
        <form action="<%= request.getContextPath() %>/CategoriaProveedorControlador" method="POST">
            <input type="hidden" name="accion" value="agregar">

            <div class="mb-3">
                <label for="nombreCategoria" class="form-label">Nombre de Categoría</label>
                <input type="text" class="form-control" id="nombreCategoria" name="nombreCategoria" required>
            </div>

            <div class="mb-3">
                <label for="descripcion" class="form-label">Descripción</label>
                <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required></textarea>
            </div>

            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="<%= request.getContextPath() %>/CategoriaProveedorControlador?accion=listar" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</div>

<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    const navbarCollapse = document.getElementById('navbarNav');
    if (navbarCollapse) {
        navbarCollapse.addEventListener('show.bs.collapse', () => {
            navbarCollapse.classList.add('animate__animated', 'animate__fadeIn');
            navbarCollapse.classList.remove('animate__fadeOut');
        });

        navbarCollapse.addEventListener('hide.bs.collapse', () => {
            navbarCollapse.classList.remove('animate__fadeIn');
            navbarCollapse.classList.add('animate__animated', 'animate__fadeOut');
        });
    }
</script>

</body>
</html>

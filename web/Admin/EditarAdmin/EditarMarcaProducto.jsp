<%-- 
    Document   : EditarMarcaProducto
    Created on : 21 may. 2025, 17:15:47
    Author     : User001
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<%@ page import="pe.edu.modelo.MarcaProducto" %>

<%
    MarcaProducto marca = (MarcaProducto) request.getAttribute("marca");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Marca de Producto</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Style/Style.css">
</head>
<body>

<div class="d-flex">
    <!-- Sidebar -->
    <jsp:include page="/Admin/Componentes/sidebar.jsp" />

    <!-- Contenido principal -->
    <div class="container mt-5">
        <h2 class="mb-4">Editar Marca de Producto</h2>

        <form action="<%= request.getContextPath() %>/MarcaProductoControlador" method="POST">
            <input type="hidden" name="accion" value="actualizar">
            <input type="hidden" name="idMarcaProducto" value="<%= marca.getIdMarcaProducto() %>">

            <div class="mb-3">
                <label for="nombreMarca" class="form-label">Nombre de Marca</label>
                <input type="text" class="form-control" id="nombreMarca" name="nombre" value="<%= marca.getNombre() %>" required>
            </div>

            <div class="mb-3">
                <label for="descripcion" class="form-label">Descripción</label>
                <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required><%= marca.getDescripcion() %></textarea>
            </div>

            <button type="submit" class="btn btn-primary">Actualizar</button>
            <a href="<%= request.getContextPath() %>/MarcaProductoControlador?accion=listar" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</div>

<!-- JS Bootstrap y animación -->
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


<%-- 
    Document   : AgregarCategoriaProducto
    Created on : 26 may. 2025, 01:53:05
    Author     : User001
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAlmacenero.jsp" %>
<%@ page import="pe.edu.modelo.CategoriaProducto" %>

<%
    CategoriaProducto categoria = (CategoriaProducto) request.getAttribute("categoria");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Categoría de Producto - Jefe de Almacen</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Style/Style.css">
</head>
<body>

<div class="d-flex">
    <!-- Sidebar -->
    <jsp:include page="/Almacenero/Componentes/sidebar.jsp" />

    <!-- Contenido principal -->
    <div class="container mt-5">
        <h2 class="mb-4">Editar Categoría de Producto</h2>

        <form action="<%= request.getContextPath() %>/CategoriaProductoControlador2" method="POST">
            <input type="hidden" name="accion" value="actualizar">
            <input type="hidden" name="idCategoriaProducto" value="<%= categoria.getIdCategoriaProducto() %>">

            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="<%= categoria.getNombre() %>" required>
            </div>

            <div class="mb-3">
                <label for="descripcion" class="form-label">Descripción</label>
                <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required><%= categoria.getDescripcion() %></textarea>
            </div>

            <button type="submit" class="btn btn-primary">Actualizar</button>
            <a href="<%= request.getContextPath() %>/CategoriaProductoControlador2?accion=listar" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

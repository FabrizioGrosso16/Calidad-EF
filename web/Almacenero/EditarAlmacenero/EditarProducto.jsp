<%-- 
    Document   : EditarProducto
    Created on : 26 may. 2025, 02:44:24
    Author     : User001
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAlmacenero.jsp" %>
<%@ page import="pe.edu.modelo.Producto" %>
<%@ page import="pe.edu.modelo.CategoriaProducto" %>
<%@ page import="pe.edu.modelo.MarcaProducto" %>
<%@ page import="java.util.List" %>

<%
    Producto producto = (Producto) request.getAttribute("producto");
    List<CategoriaProducto> categorias = (List<CategoriaProducto>) request.getAttribute("categorias");
    List<MarcaProducto> marcas = (List<MarcaProducto>) request.getAttribute("marcas");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Producto - Jefe de Almacen</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Style/Style.css">
</head>
<body>

<div class="d-flex">
    <!-- Sidebar almacenero -->
    <jsp:include page="/Almacenero/Componentes/sidebar.jsp" />

    <!-- Contenido principal -->
    <div class="container mt-5">
        <h2 class="mb-4">Editar Producto</h2>

        <form action="<%= request.getContextPath() %>/ProductoControlador2" method="POST">
            <input type="hidden" name="accion" value="actualizar">
            <input type="hidden" name="idProducto" value="<%= producto.getIdProducto() %>">

            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="<%= producto.getNombre() %>" required>
            </div>

            <div class="mb-3">
                <label for="descripcion" class="form-label">Descripción</label>
                <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required><%= producto.getDescripcion() %></textarea>
            </div>

            <div class="mb-3">
                <label for="precio" class="form-label">Precio</label>
                <input type="number" step="0.01" class="form-control" id="precio" name="precio" value="<%= producto.getPrecio() %>" required>
            </div>

            <div class="mb-3">
                <label for="estado" class="form-label">Estado</label>
                <select class="form-select" id="estado" name="estado" required>
                    <option value="Activo" <%= "Activo".equals(producto.getEstado()) ? "selected" : "" %>>Activo</option>
                    <option value="Inactivo" <%= "Inactivo".equals(producto.getEstado()) ? "selected" : "" %>>Inactivo</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="idCategoriaProducto" class="form-label">Categoría</label>
                <select class="form-select" id="idCategoriaProducto" name="idCategoriaProducto" required>
                    <option value="">Seleccione una categoría</option>
                    <% if (categorias != null) {
                        for (CategoriaProducto cat : categorias) {
                            boolean selected = (cat.getIdCategoriaProducto() == producto.getIdCategoriaProducto());
                    %>
                        <option value="<%= cat.getIdCategoriaProducto() %>" <%= selected ? "selected" : "" %>>
                            <%= cat.getNombre() %>
                        </option>
                    <%  }
                       } %>
                </select>
            </div>

            <div class="mb-3">
                <label for="idMarcaProducto" class="form-label">Marca</label>
                <select class="form-select" id="idMarcaProducto" name="idMarcaProducto" required>
                    <option value="">Seleccione una marca</option>
                    <% if (marcas != null) {
                        for (MarcaProducto marca : marcas) {
                            boolean selected = (marca.getIdMarcaProducto() == producto.getIdMarcaProducto());
                    %>
                        <option value="<%= marca.getIdMarcaProducto() %>" <%= selected ? "selected" : "" %>>
                            <%= marca.getNombre() %>
                        </option>
                    <%  }
                       } %>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Actualizar</button>
            <a href="<%= request.getContextPath() %>/ProductoControlador2?accion=listar" class="btn btn-secondary">Cancelar</a>
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

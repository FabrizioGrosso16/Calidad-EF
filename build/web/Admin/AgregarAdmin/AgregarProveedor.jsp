<%-- 
    Document   : AgregarProveedor
    Created on : 21 may. 2025, 15:22:15
    Author     : User001
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<%@ page import="java.util.*, pe.edu.modelo.CategoriaProveedor" %>

<%
    List<CategoriaProveedor> categorias = (List<CategoriaProveedor>) request.getAttribute("categorias");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Proveedor</title>
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
        <h2 class="mb-4">Agregar Nuevo Proveedor</h2>

        <form action="<%= request.getContextPath() %>/ProveedorControlador" method="POST">
            <input type="hidden" name="accion" value="agregar">

            <div class="mb-3">
                <label for="razonSocial" class="form-label">Razón Social</label>
                <input type="text" class="form-control" id="razonSocial" name="razonSocial" required>
            </div>

            <div class="mb-3">
                <label for="ruc" class="form-label">RUC</label>
                <input type="text" class="form-control" id="ruc" name="ruc" required
                       pattern="^(10|20)\d{9}$"
                       title="El RUC debe comenzar con 10 o 20 y tener exactamente 11 dígitos">
            </div>

            <div class="mb-3">
                <label for="direccion" class="form-label">Dirección</label>
                <input type="text" class="form-control" id="direccion" name="direccion" required>
            </div>

            <div class="mb-3">
                <label for="correo" class="form-label">Correo</label>
                <input type="email" class="form-control" id="correo" name="correo" required>
            </div>

            <div class="mb-3">
                <label for="idCategoriaProveedor" class="form-label">Categoría del Proveedor</label>
                <select class="form-select" id="idCategoriaProveedor" name="idCategoriaProveedor" required>
                    <option value="" disabled selected>Seleccione una categoría</option>
                    <%
                        if (categorias != null) {
                            for (CategoriaProveedor cat : categorias) {
                    %>
                        <option value="<%= cat.getIdCategoriaProveedor() %>">
                            <%= cat.getNombreCategoria() %>
                        </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="<%= request.getContextPath() %>/ProveedorControlador?accion=listar" class="btn btn-secondary">Cancelar</a>
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

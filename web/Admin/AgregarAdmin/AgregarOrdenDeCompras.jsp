<%-- 
    Document   : AgregarOrdenDeCompras
    Created on : 25 may. 2025, 12:44:01
    Author     : User001
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<%@ page import="java.util.*, pe.edu.modelo.Trabajador, pe.edu.modelo.Proveedor" %>

<%
    List<Trabajador> trabajadores = (List<Trabajador>) request.getAttribute("trabajadores");
    List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Orden de Compra</title>
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
        <h2 class="mb-4">Agregar Nueva Orden de Compra</h2>

        <form action="<%= request.getContextPath() %>/OrdenDeComprasControlador" method="POST">
            <input type="hidden" name="accion" value="agregar">

            <div class="mb-3">
                <label for="fechaOrden" class="form-label">Fecha de Orden</label>
                <input type="date" class="form-control" id="fechaOrden" name="fechaOrden" required>
            </div>

            <div class="mb-3">
                <label for="total" class="form-label">Total (S/.)</label>
                <input type="number" step="0.01" min="0" class="form-control" id="total" name="total" required>
            </div>

            <div class="mb-3">
                <label for="idTrabajador" class="form-label">Trabajador</label>
                <select class="form-select" id="idTrabajador" name="idTrabajador" required>
                    <option value="" disabled selected>Seleccione un trabajador</option>
                    <%
                        if (trabajadores != null) {
                            for (Trabajador t : trabajadores) {
                    %>
                    <option value="<%= t.getIdTrabajador() %>"><%= t.getNombre() %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <div class="mb-3">
                <label for="idProveedor" class="form-label">Proveedor</label>
                <select class="form-select" id="idProveedor" name="idProveedor" required>
                    <option value="" disabled selected>Seleccione un proveedor</option>
                    <%
                        if (proveedores != null) {
                            for (Proveedor p : proveedores) {
                    %>
                    <option value="<%= p.getIdProveedor() %>"><%= p.getRazonSocial() %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <div class="mb-3">
                <label for="estado" class="form-label">Estado</label>
                <select class="form-select" id="estado" name="estado" required>
                    <option value="" disabled selected>Seleccione estado</option>
                    <option value="Pendiente">Pendiente</option>
                    <option value="Aprobado">Aprobado</option>
                    <option value="Cancelado">Cancelado</option>
                </select>
            </div>

            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="<%= request.getContextPath() %>/OrdenDeComprasControlador?accion=listar" class="btn btn-secondary">Cancelar</a>
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

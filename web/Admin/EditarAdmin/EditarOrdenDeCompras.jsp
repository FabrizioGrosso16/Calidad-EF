<%-- 
    Document   : EditarOrdenDeCompras
    Created on : 26 may. 2025, 15:12:43
    Author     : User001
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<%@ page import="pe.edu.modelo.OrdenDeCompras" %>
<%@ page import="pe.edu.modelo.Trabajador" %>
<%@ page import="pe.edu.modelo.Proveedor" %>
<%@ page import="java.util.List" %>

<%
    OrdenDeCompras orden = (OrdenDeCompras) request.getAttribute("orden");
    List<Trabajador> trabajadores = (List<Trabajador>) request.getAttribute("trabajadores");
    List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Orden de Compra</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Style/Style.css">
</head>
<body>

<div class="d-flex">
    <!-- Sidebar -->
    <jsp:include page="/Admin/Componentes/sidebar.jsp" />

    <!-- Contenido principal -->
    <div class="container mt-5">
        <h2 class="mb-4">Editar Orden de Compra</h2>

        <form action="<%= request.getContextPath() %>/OrdenDeComprasControlador" method="POST">
            <input type="hidden" name="accion" value="actualizar">
            <input type="hidden" name="idOrdenCompra" value="<%= orden.getIdOrdenCompra() %>">

            <div class="mb-3">
                <label for="fechaOrden" class="form-label">Fecha</label>
                <input type="date" class="form-control" id="fechaOrden" name="fechaOrden"
                       value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(orden.getFechaOrden()) %>" required>
            </div>

            <div class="mb-3">
                <label for="total" class="form-label">Total</label>
                <input type="number" step="0.01" class="form-control" id="total" name="total" value="<%= orden.getTotal() %>" required>
            </div>

            <div class="mb-3">
                <label for="idTrabajador" class="form-label">Trabajador</label>
                <select class="form-select" id="idTrabajador" name="idTrabajador" required>
                    <option value="">Seleccione un trabajador</option>
                    <% for (Trabajador t : trabajadores) {
                        boolean selected = (t.getIdTrabajador() == orden.getIdTrabajador());
                    %>
                        <option value="<%= t.getIdTrabajador() %>" <%= selected ? "selected" : "" %>>
                            <%= t.getNombre() %>
                        </option>
                    <% } %>
                </select>
            </div>

            <div class="mb-3">
                <label for="idProveedor" class="form-label">Proveedor</label>
                <select class="form-select" id="idProveedor" name="idProveedor" required>
                    <option value="">Seleccione un proveedor</option>
                    <% for (Proveedor p : proveedores) {
                        boolean selected = (p.getIdProveedor() == orden.getIdProveedor());
                    %>
                        <option value="<%= p.getIdProveedor() %>" <%= selected ? "selected" : "" %>>
                            <%= p.getRazonSocial() %>
                        </option>
                    <% } %>
                </select>
            </div>

            <div class="mb-3">
                <label for="estado" class="form-label">Estado</label>
                <select class="form-select" id="estado" name="estado">
                    <option value="pendiente" <%= orden.getEstado().equalsIgnoreCase("pendiente") ? "selected" : "" %>>⏳ Pendiente</option>
                    <option value="aprobado" <%= orden.getEstado().equalsIgnoreCase("aprobado") ? "selected" : "" %>>✔ Aprobado</option>
                    <option value="cancelado" <%= orden.getEstado().equalsIgnoreCase("cancelado") ? "selected" : "" %>>❌ Cancelado</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
            <a href="<%= request.getContextPath() %>/OrdenDeComprasControlador?accion=listar" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

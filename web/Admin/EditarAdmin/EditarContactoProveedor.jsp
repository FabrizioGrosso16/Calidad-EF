<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<%@ page import="pe.edu.modelo.ContactoProveedor" %>
<%@ page import="java.util.List" %>
<%@ page import="pe.edu.modelo.Proveedor" %>

<% 
    List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
    ContactoProveedor contacto = (ContactoProveedor) request.getAttribute("contacto");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Contacto de Proveedor</title>
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
        <h2 class="mb-4">Editar Contacto de Proveedor</h2>

       <form action="<%= request.getContextPath() %>/ContactoProveedorControlador" method="POST">
    <input type="hidden" name="accion" value="actualizar">
    <input type="hidden" name="idContacto" value="<%= contacto.getIdContacto() %>">

    <div class="mb-3">
        <label for="nombre" class="form-label">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" value="<%= contacto.getNombre() %>" required>
    </div>

    <div class="mb-3">
        <label for="telefono" class="form-label">Teléfono</label>
        <input type="text" class="form-control" id="telefono" name="telefono" value="<%= contacto.getTelefono() %>" required
               pattern="^9\d{8}$" maxlength="9" minlength="9"
                    title="El número debe iniciar con 9 y tener exactamente 9 dígitos">
        
    </div>

    <div class="mb-3">
        <label for="email" class="form-label">Correo Electrónico</label>
        <input type="email" class="form-control" id="email" name="email" value="<%= contacto.getEmail() %>" required>
    </div>

    <div class="mb-3">
        <label for="idProveedor" class="form-label">Proveedor</label>
        <select class="form-select" id="idProveedor" name="idProveedor" required>
            <option value="">Seleccione un proveedor</option>
            <% if (proveedores != null) {
                for (Proveedor p : proveedores) {
                    boolean selected = (p.getIdProveedor() == contacto.getIdProveedor());
            %>
                <option value="<%= p.getIdProveedor() %>" <%= selected ? "selected" : "" %> >
                    <%= p.getRazonSocial() %>
                </option>
            <% } } %>
        </select>
    </div>

    <button type="submit" class="btn btn-primary">Actualizar</button>
    <a href="<%= request.getContextPath() %>/ContactoProveedorControlador?accion=listar" class="btn btn-secondary">Cancelar</a>
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

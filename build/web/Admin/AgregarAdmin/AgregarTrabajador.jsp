<%-- 
    Document   : AgregarTrabajador
    Created on : 25 may. 2025
    Author     : User001
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<%@ page import="java.util.*, pe.edu.modelo.Rol" %>

<%
    List<Rol> roles = (List<Rol>) request.getAttribute("roles");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Trabajador</title>
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
        <h2 class="mb-4">Agregar Nuevo Trabajador</h2>

        <form action="<%= request.getContextPath() %>/TrabajadorControlador" method="POST">
            <input type="hidden" name="accion" value="agregar">

            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="nombre" name="nombre" required>
            </div>

            <div class="mb-3">
                <label for="apellido" class="form-label">Apellido</label>
                <input type="text" class="form-control" id="apellido" name="apellido" required>
            </div>

            <div class="mb-3">
                <label for="dni" class="form-label">DNI</label>
                <input type="text" class="form-control" id="dni" name="dni" required
                       pattern="^\d{8}$" maxlength="8" minlength="8"
                       title="El DNI debe tener exactamente 8 dígitos numéricos">
            </div>

            <div class="mb-3">
                <label for="telefono" class="form-label">Teléfono</label>
                <input type="text" class="form-control" id="telefono" name="telefono" required
                       pattern="^9\d{8}$" maxlength="9" minlength="9"
                       title="El número debe iniciar con 9 y tener exactamente 9 dígitos">
            </div>

            <div class="mb-3">
                <label for="correo" class="form-label">Correo</label>
                <input type="email" class="form-control" id="correo" name="correo" required>
            </div>

            <div class="mb-3">
                <label for="idRol" class="form-label">Rol</label>
                <select class="form-select" id="idRol" name="idRol" required>
                    <option value="" disabled selected>Seleccione un rol</option>
                    <%
                        if (roles != null) {
                            for (Rol r : roles) {
                    %>
                        <option value="<%= r.getIdRol() %>">
                            <%= r.getNombre() %>
                        </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <div class="mb-3">
                <label for="clave" class="form-label">Clave</label>
                <input type="password" class="form-control" id="clave" name="clave" required>
            </div>

            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="<%= request.getContextPath() %>/TrabajadorControlador?accion=listar" class="btn btn-secondary">Cancelar</a>
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

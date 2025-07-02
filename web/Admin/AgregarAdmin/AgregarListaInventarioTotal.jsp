<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<%@ page import="java.util.*, pe.edu.modelo.Trabajador, pe.edu.modelo.Producto" %>

<%
    // Obtener las listas de trabajadores y productos desde el request
    List<Trabajador> trabajadores = (List<Trabajador>) request.getAttribute("trabajadores");
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Inventario</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Style/Style.css">
</head>
<body>

<div class="d-flex">
    <!-- Sidebar administrador -->
    <jsp:include page="/Admin/Componentes/sidebar.jsp" />

    <!-- Contenido principal -->
    <div class="container mt-5">
        <h2 class="mb-4">Agregar Nuevo Registro de Inventario</h2>

        <form action="<%= request.getContextPath() %>/ListaInventarioTotalControlador" method="POST">
            <input type="hidden" name="accion" value="agregar">

            <!-- Campo para la cantidad -->
            <div class="mb-3">
                <label for="cantidad" class="form-label">Cantidad</label>
                <input type="number" class="form-control" id="cantidad" name="cantidad" required min="0">
            </div>

            <!-- Campo para el precio -->
            <div class="mb-3">
                <label for="precio" class="form-label">Precio</label>
                <input type="number" step="0.01" class="form-control" id="precio" name="precio" required min="0">
            </div>

            <!-- Campo para el lote -->
            <div class="mb-3">
                <label for="lote" class="form-label">Lote</label>
                <input type="text" class="form-control" id="lote" name="lote" required>
            </div>

            <!-- Selección de trabajador -->
            <div class="mb-3">
                <label for="idTrabajador" class="form-label">Trabajador</label>
                <select class="form-select" id="idTrabajador" name="idTrabajador" required>
                    <option value="" disabled selected>Seleccione un trabajador</option>
                    <%
                        if (trabajadores != null) {
                            for (Trabajador trabajador : trabajadores) {
                    %>
                        <option value="<%= trabajador.getIdTrabajador() %>">
                            <%= trabajador.getNombre() %>
                        </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <!-- Selección de producto -->
            <div class="mb-3">
                <label for="idProducto" class="form-label">Producto</label>
                <select class="form-select" id="idProducto" name="idProducto" required>
                    <option value="" disabled selected>Seleccione un producto</option>
                    <%
                        if (productos != null) {
                            for (Producto producto : productos) {
                    %>
                        <option value="<%= producto.getIdProducto() %>">
                            <%= producto.getNombre() %>
                        </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <!-- Botones -->
            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="<%= request.getContextPath() %>/ListaInventarioTotalControlador?accion=listar" class="btn btn-secondary">Cancelar</a>
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

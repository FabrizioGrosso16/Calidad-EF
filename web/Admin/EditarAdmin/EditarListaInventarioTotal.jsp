<%-- 
    Document   : EditarListaInventarioTotal
    Created on : 22 may. 2025, 20:57:08
    Author     : User001
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<%@ page import="pe.edu.modelo.ListaInventarioTotal" %>
<%@ page import="pe.edu.modelo.Trabajador" %>
<%@ page import="pe.edu.modelo.Producto" %>
<%@ page import="java.util.List" %>

<%
    ListaInventarioTotal lit = (ListaInventarioTotal) request.getAttribute("lit");
    List<Trabajador> trabajadores = (List<Trabajador>) request.getAttribute("trabajadores");
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Inventario Total</title>
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
        <h2 class="mb-4">Editar Registro de Inventario Total</h2>

        <form action="<%= request.getContextPath() %>/ListaInventarioTotalControlador" method="POST">
            <input type="hidden" name="accion" value="actualizar">
            <input type="hidden" name="idLIT" value="<%= lit.getIdLIT() %>">

            <div class="mb-3">
                <label for="cantidad" class="form-label">Cantidad</label>
                <input type="number" class="form-control" id="cantidad" name="cantidad" value="<%= lit.getCantidad() %>" required>
            </div>

            <div class="mb-3">
                <label for="precio" class="form-label">Precio</label>
                <input type="number" step="0.01" class="form-control" id="precio" name="precio" value="<%= lit.getPrecio() %>" required>
            </div>

            <div class="mb-3">
                <label for="lote" class="form-label">Lote</label>
                <input type="text" class="form-control" id="lote" name="lote" value="<%= lit.getLote() %>" required>
            </div>

            <div class="mb-3">
                <label for="idTrabajador" class="form-label">Trabajador</label>
                <select class="form-select" id="idTrabajador" name="idTrabajador" required>
                    <option value="">Seleccione un trabajador</option>
                    <% if (trabajadores != null) {
                        for (Trabajador trabajador : trabajadores) {
                            boolean selected = (trabajador.getIdTrabajador() == lit.getIdTrabajador());
                    %>
                        <option value="<%= trabajador.getIdTrabajador() %>" <%= selected ? "selected" : "" %>>
                            <%= trabajador.getNombre() %>
                        </option>
                    <%  }
                       } %>
                </select>
            </div>

            <div class="mb-3">
                <label for="idProducto" class="form-label">Producto</label>
                <select class="form-select" id="idProducto" name="idProducto" required>
                    <option value="">Seleccione un producto</option>
                    <% if (productos != null) {
                        for (Producto producto : productos) {
                            boolean selected = (producto.getIdProducto() == lit.getIdProducto());
                    %>
                        <option value="<%= producto.getIdProducto() %>" <%= selected ? "selected" : "" %>>
                            <%= producto.getNombre() %>
                        </option>
                    <%  }
                       } %>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Actualizar</button>
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


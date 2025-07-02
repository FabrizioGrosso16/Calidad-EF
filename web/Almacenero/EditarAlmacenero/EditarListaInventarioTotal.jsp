<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAlmacenero.jsp" %>
<%@ page import="pe.edu.modelo.ListaInventarioTotal" %>
<%@ page import="pe.edu.modelo.Trabajador" %>
<%@ page import="pe.edu.modelo.Producto" %>
<%@ page import="java.util.List" %>

<%
    ListaInventarioTotal lit = (ListaInventarioTotal) request.getAttribute("lit");
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");

    // Obtener trabajador logueado desde la sesión
    Trabajador trabajadorSesion = (Trabajador) session.getAttribute("trabajador");
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
    <!-- Sidebar del almacenero -->
    <jsp:include page="/Almacenero/Componentes/sidebar.jsp" />

    <!-- Contenido principal -->
    <div class="container mt-5">
        <h2 class="mb-4">Editar Registro de Lista Inventario Total - Jefe de Almacen</h2>

        <form action="<%= request.getContextPath() %>/ListaInventarioTotalControlador2" method="POST">
            <input type="hidden" name="accion" value="actualizar">
            <input type="hidden" name="idLIT" value="<%= lit.getIdLIT() %>">

            <!-- Cantidad -->
            <div class="mb-3">
                <label for="cantidad" class="form-label">Cantidad</label>
                <input type="number" class="form-control" id="cantidad" name="cantidad" value="<%= lit.getCantidad() %>" required>
            </div>

            <!-- Precio -->
            <div class="mb-3">
                <label for="precio" class="form-label">Precio</label>
                <input type="number" step="0.01" class="form-control" id="precio" name="precio" value="<%= lit.getPrecio() %>" required>
            </div>

            <!-- Lote -->
            <div class="mb-3">
                <label for="lote" class="form-label">Lote</label>
                <input type="text" class="form-control" id="lote" name="lote" value="<%= lit.getLote() %>" required>
            </div>

            <!-- Trabajador logueado (automático) -->
            <input type="hidden" name="idTrabajador" value="<%= trabajadorSesion.getIdTrabajador() %>">
            <div class="mb-3">
                <label class="form-label">Trabajador</label>
                <input type="text" class="form-control" value="<%= trabajadorSesion.getNombre() %>" readonly>
            </div>

            <!-- Producto -->
            <div class="mb-3">
                <label for="idProducto" class="form-label">Producto</label>
                <select class="form-select" id="idProducto" name="idProducto" required>
                    <option value="">Seleccione un producto</option>
                    <%
                        if (productos != null) {
                            for (Producto producto : productos) {
                                boolean selected = (producto.getIdProducto() == lit.getIdProducto());
                    %>
                        <option value="<%= producto.getIdProducto() %>" <%= selected ? "selected" : "" %>>
                            <%= producto.getNombre() %>
                        </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <!-- Botones -->
            <button type="submit" class="btn btn-primary">Actualizar</button>
            <a href="<%= request.getContextPath() %>/ListaInventarioTotalControlador2?accion=listar" class="btn btn-secondary">Cancelar</a>
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

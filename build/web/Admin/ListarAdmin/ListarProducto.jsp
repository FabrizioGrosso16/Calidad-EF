<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<%@ page import="java.util.*, pe.edu.modelo.Producto, pe.edu.modelo.CategoriaProducto, pe.edu.modelo.MarcaProducto" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Productos</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Style/Style.css">

    <!-- DataTables CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.4.2/css/buttons.bootstrap5.min.css">
</head>
<body>

<div class="d-flex">
    <!-- Sidebar izquierdo -->
    <jsp:include page="/Admin/Componentes/sidebar.jsp" />

    <div class="container mt-4">
        <h1 class="mb-4">Productos Registrados</h1>
        <a class="btn btn-success mb-3" href="<%= request.getContextPath() %>/ProductoControlador?accion=nuevo">Agregar Producto</a>

        <table id="tablaProductos" class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Precio</th>
                    <th>Estado</th>
                    <th>Categoría</th>
                    <th>Marca</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
                    List<CategoriaProducto> categorias = (List<CategoriaProducto>) request.getAttribute("categorias");
                    List<MarcaProducto> marcas = (List<MarcaProducto>) request.getAttribute("marcas");

                    if (productos != null && !productos.isEmpty()) {
                        for (Producto p : productos) {
                            String nombreCategoria = "Desconocida";
                            String nombreMarca = "Desconocida";

                            for (CategoriaProducto c : categorias) {
                                if (c.getIdCategoriaProducto() == p.getIdCategoriaProducto()) {
                                    nombreCategoria = c.getNombre();
                                    break;
                                }
                            }

                            for (MarcaProducto m : marcas) {
                                if (m.getIdMarcaProducto() == p.getIdMarcaProducto()) {
                                    nombreMarca = m.getNombre();
                                    break;
                                }
                            }
                %>
                    <tr>
                        <td><%= p.getIdProducto() %></td>
                        <td><%= p.getNombre() %></td>
                        <td><%= p.getDescripcion() %></td>
                        <td>S/. <%= String.format("%.2f", p.getPrecio()) %></td>
                        <td><%= p.getEstado() %></td>
                        <td><%= nombreCategoria %></td>
                        <td><%= nombreMarca %></td>
                        <td>
                            <a class="btn btn-primary btn-sm" href="<%= request.getContextPath() %>/ProductoControlador?accion=editar&id=<%= p.getIdProducto() %>">
                                <i class="fas fa-edit"></i> Editar
                            </a>
                            <span>|</span>
                            <a class="btn btn-danger btn-sm" href="<%= request.getContextPath() %>/ProductoControlador?accion=eliminar&id=<%= p.getIdProducto() %>" onclick="return confirm('¿Está seguro de eliminar este producto?');">
                                <i class="fas fa-trash-alt"></i> Eliminar
                            </a>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="8" class="text-center">No hay productos registrados.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <a class="btn btn-secondary" href="/Admin/AdminIndex.jsp">Regresar</a>
    </div>
</div>

<!-- JS: jQuery, Bootstrap Bundle -->
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- DataTables JS -->
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>

<!-- Botones exportación -->
<script src="https://cdn.datatables.net/buttons/2.4.2/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.bootstrap5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.print.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.colVis.min.js"></script>

<!-- Inicialización DataTables con idioma español y botones -->
<script>
    $(document).ready(function () {
        $('#tablaProductos').DataTable({
            responsive: true,
            language: {
                url: '//cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json'
            },
            dom: 'Bfrtip',
            buttons: [
                { extend: 'copy', text: 'Copiar' },
                { 
                    extend: 'pdf', 
                    text: 'Exportar PDF',
                    exportOptions: {
                        columns: ':not(:last-child)'
                    }
                },
                { extend: 'print', text: 'Imprimir' }
            ]
        });
    });
</script>

<!-- Animación menú -->
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

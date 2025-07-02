<%-- 
    Document   : ListarCategoriaProducto
    Created on : 22 may. 2025, 20:47:51
    Author     : User001
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAlmacenero.jsp" %>
<%@ page import="java.util.*, pe.edu.modelo.CategoriaProducto" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Categorías de Producto - Jefe de Almacen</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Style/Style.css">

    <!-- DataTables CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.4.2/css/buttons.bootstrap5.min.css">
</head>
<body>

<div class="d-flex">
    <!-- Sidebar -->
    <jsp:include page="/Almacenero/Componentes/sidebar.jsp" />

    <!-- Main Content -->
    <div class="container mt-4">
        <h1 class="mb-4">Categorías de Producto</h1>
        <a class="btn btn-success mb-3" href="<%= request.getContextPath() %>/CategoriaProductoControlador2?accion=nuevo">
            <i class="fas fa-plus-circle"></i> Agregar Categoría
        </a>

        <!-- Tabla con ID para DataTable -->
        <table id="tablaCategorias" class="table table-bordered table-hover display nowrap" style="width:100%">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<CategoriaProducto> lista = (List<CategoriaProducto>) request.getAttribute("categorias");
                    if (lista != null && !lista.isEmpty()) {
                        for (CategoriaProducto cp : lista) {
                %>
                <tr>
                    <td><%= cp.getIdCategoriaProducto() %></td>
                    <td><%= cp.getNombre() %></td>
                    <td><%= cp.getDescripcion() %></td>
                    <td>
                        <a class="btn btn-primary btn-sm" href="<%= request.getContextPath() %>/CategoriaProductoControlador2?accion=editar&id=<%= cp.getIdCategoriaProducto() %>">
                            <i class="fas fa-edit"></i> Editar
                        </a>
                        <span>|</span>
                        <a class="btn btn-danger btn-sm" href="<%= request.getContextPath() %>/CategoriaProductoControlador2?accion=eliminar&id=<%= cp.getIdCategoriaProducto() %>"
                           onclick="return confirm('¿Está seguro de eliminar esta categoría?');">
                            <i class="fas fa-trash-alt"></i> Eliminar
                        </a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4" class="text-center">No hay categorías registradas.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <a class="btn btn-secondary mt-3" href="/Almacenero/AlmaceneroIndex.jsp">Regresar</a>
    </div>
</div>

<!-- Scripts Bootstrap & DataTables -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- DataTables Scripts -->
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>

<!-- Botones de exportación -->
<script src="https://cdn.datatables.net/buttons/2.4.2/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.bootstrap5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.print.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.colVis.min.js"></script>

<!-- Idioma español -->
<script src="https://cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json"></script>

<!-- Inicialización de DataTable -->
<script>
    $(document).ready(function () {
        $('#tablaCategorias').DataTable({
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

</body>
</html>

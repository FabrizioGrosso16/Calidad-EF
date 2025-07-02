<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<%@ page import="java.util.*, pe.edu.modelo.MarcaProducto" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Marcas de Producto</title>
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
        <h1 class="mb-4">Marcas de Producto</h1>
        <a class="btn btn-success mb-3" href="<%= request.getContextPath() %>/MarcaProductoControlador?accion=nuevo">Agregar Marca</a>
        
        <table id="tablaMarcas" class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre de Marca</th>
                    <th>Descripción</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<MarcaProducto> marcas = (List<MarcaProducto>) request.getAttribute("marcas");
                    if (marcas != null && !marcas.isEmpty()) {
                        for (MarcaProducto mp : marcas) {
                %>
                    <tr>
                        <td><%= mp.getIdMarcaProducto() %></td>
                        <td><%= mp.getNombre() %></td>
                        <td><%= mp.getDescripcion() %></td>
                        <td>
                            <a class="btn btn-primary btn-sm" href="<%= request.getContextPath() %>/MarcaProductoControlador?accion=editar&id=<%= mp.getIdMarcaProducto() %>">
                                <i class="fas fa-edit"></i> Editar
                            </a> 
                            <span>|</span> 
                            <a class="btn btn-danger btn-sm" href="<%= request.getContextPath() %>/MarcaProductoControlador?accion=eliminar&id=<%= mp.getIdMarcaProducto() %>" onclick="return confirm('¿Está seguro de eliminar esta marca de producto?');">
                                <i class="fas fa-trash-alt"></i> Eliminar
                            </a>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="4" class="text-center">No hay marcas registradas.</td>
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
        $('#tablaMarcas').DataTable({
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

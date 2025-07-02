<%-- 
    Document   : ListarDetalleProducto
    Created on : 22 may. 2025, 21:49:57
    Author     : User001
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<%@ page import="java.util.*, pe.edu.modelo.DetalleProducto, pe.edu.modelo.Producto, pe.edu.modelo.Trabajador" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Historial de Detalles de Producto</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <!-- Animate.css -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>

    <!-- Custom CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Style/Style.css">

    <!-- DataTables CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.4.2/css/buttons.bootstrap5.min.css">
</head>
<body>

<div class="d-flex">
    <!-- Sidebar -->
    <jsp:include page="/Admin/Componentes/sidebar.jsp" />

    <div class="container mt-4">
        <h1 class="mb-4">Historial de Detalles de Producto</h1>

        
        <table id="tablaDetalles" class="table table-bordered table-hover display nowrap" style="width:100%">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Fecha</th>
                    <th>Cantidad</th>
                    <th>Producto</th>
                    <th>Trabajador</th>
                </tr>
            </thead>

            <tbody>
                <%
                    List<DetalleProducto> listaDetalles = (List<DetalleProducto>) request.getAttribute("listaDetalles");
                    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
                    List<Trabajador> trabajadores = (List<Trabajador>) request.getAttribute("trabajadores");

                    if (listaDetalles != null && !listaDetalles.isEmpty()) {
                        for (DetalleProducto detalle : listaDetalles) {
                            String nombreProducto = "Producto no encontrado";
                            for (Producto p : productos) {
                                if (p.getIdProducto() == detalle.getIdProducto()) {
                                    nombreProducto = p.getNombre();
                                    break;
                                }
                            }

                            String nombreTrabajador = "Trabajador no encontrado";
                            for (Trabajador t : trabajadores) {
                                if (t.getIdTrabajador() == detalle.getIdTrabajador()) {
                                    nombreTrabajador = t.getNombre();
                                    break;
                                }
                            }
                %>
                <tr>
                    <td><%= detalle.getIdDetalleProducto() %></td>
                    <td><%= detalle.getFecha() %></td>
                    <td><%= detalle.getCantidad() %></td>
                    <td><%= nombreProducto %></td>
                    <td><%= nombreTrabajador %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5" class="text-center">No hay detalles registrados.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <a class="btn btn-secondary mt-3" href="/Admin/AdminIndex.jsp">Regresar</a>
    </div>
</div>

<!-- JS: jQuery + Bootstrap Bundle -->
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

<!-- DataTable Initialization -->
<script>
    $(document).ready(function () {
        $('#tablaDetalles').DataTable({
            responsive: true,
            language: {
                url: '//cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json'
            },
            dom: 'Bfrtip',
            buttons: [
                {
                    extend: 'copy',
                    text: 'Copiar'
                },
                { 
                    extend: 'pdf', 
                    text: 'Exportar PDF'                  
                },
                {
                    extend: 'print',
                    text: 'Imprimir'
                }
            ]
        });
    });
</script>

</body>
</html>

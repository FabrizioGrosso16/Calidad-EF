<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<%@ page import="java.util.*, pe.edu.modelo.OrdenDeCompras, pe.edu.modelo.Trabajador, pe.edu.modelo.Proveedor" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Órdenes de Compra</title>

    <!-- Bootstrap y estilos -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Style/Style.css">

    <!-- DataTables -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.4.2/css/buttons.bootstrap5.min.css">
</head>
<body>

<div class="d-flex">
    <!-- Sidebar -->
    <jsp:include page="/Admin/Componentes/sidebar.jsp" />

    
    <div class="container mt-4">
        <h1 class="mb-4">Órdenes de Compra</h1>

        <a class="btn btn-success mb-3" href="OrdenDeComprasControlador?accion=nuevo">Agregar Orden</a>

        <table id="tablaOrdenes" class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Fecha</th>
                    <th>Total</th>
                    <th>Trabajador</th>
                    <th>Proveedor</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<OrdenDeCompras> ordenes = (List<OrdenDeCompras>) request.getAttribute("ordenes");
                    List<Trabajador> trabajadores = (List<Trabajador>) request.getAttribute("trabajadores");
                    List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");

                    if (ordenes != null && !ordenes.isEmpty()) {
                        for (OrdenDeCompras orden : ordenes) {
                            String trabajadorNombre = "Desconocido";
                            String proveedorNombre = "Desconocido";

                            for (Trabajador t : trabajadores) {
                                if (t.getIdTrabajador() == orden.getIdTrabajador()) {
                                    trabajadorNombre = t.getNombre();
                                    break;
                                }
                            }

                            for (Proveedor p : proveedores) {
                                if (p.getIdProveedor() == orden.getIdProveedor()) {
                                    proveedorNombre = p.getRazonSocial();
                                    break;
                                }
                            }
                %>
                    <tr>
                        <td><%= orden.getIdOrdenCompra() %></td>
                        <td><%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(orden.getFechaOrden()) %></td>
                        <td>S/. <%= String.format("%.2f", orden.getTotal()) %></td>
                        <td><%= trabajadorNombre %></td>
                        <td><%= proveedorNombre %></td>
                        <td>
                            <form action="OrdenDeComprasControlador" method="post" style="display: flex; gap: 4px; align-items: center;">
                                <input type="hidden" name="accion" value="cambiarEstado">
                                <input type="hidden" name="id" value="<%= orden.getIdOrdenCompra() %>">

                                <select name="estado" class="form-select form-select-sm"
                                        onchange="this.form.submit()"
                                        style="width: auto;">
                                    <option value="aceptado" <%= orden.getEstado().equalsIgnoreCase("Aprobado") ? "selected" : "" %>>✔ Aceptado</option>
                                    <option value="pendiente" <%= orden.getEstado().equalsIgnoreCase("pendiente") ? "selected" : "" %>>⏳ Pendiente</option>
                                    <option value="cancelado" <%= orden.getEstado().equalsIgnoreCase("cancelado") ? "selected" : "" %>>❌ Cancelado</option>
                                </select>
                            </form>
                        </td>
                        <td>
                            <a class="btn btn-sm btn-primary" href="OrdenDeComprasControlador?accion=editar&id=<%= orden.getIdOrdenCompra() %>">
                                <i class="fas fa-edit"></i> Editar
                            </a>
                            <span>|</span>
                            <a class="btn btn-sm btn-danger" href="OrdenDeComprasControlador?accion=eliminar&id=<%= orden.getIdOrdenCompra() %>"
                               onclick="return confirm('¿Está seguro de eliminar esta orden de compra?');">
                                <i class="fas fa-trash-alt"></i> Eliminar
                            </a>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="7" class="text-center">No hay órdenes de compra registradas.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <a class="btn btn-secondary mt-3" href="/Admin/AdminIndex.jsp">Regresar</a>
    </div>
</div>

<!-- JS Bootstrap, jQuery y DataTables -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
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

<script>
    $(document).ready(function () {
        $('#tablaOrdenes').DataTable({
            responsive: true,
            language: {
                url: 'https://cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json'
            },
            dom: 'Bfrtip',
            buttons: [
                { extend: 'copy', text: 'Copiar' },
                { extend: 'pdf', text: 'Exportar PDF', exportOptions: { columns: ':not(:last-child)' } },
                { extend: 'print', text: 'Imprimir' }
            ]
        });
    });
</script>

</body>
</html>

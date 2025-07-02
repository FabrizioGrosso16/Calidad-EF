<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAlmacenero.jsp" %>
<%@ page import="java.util.*, pe.edu.modelo.OrdenDeCompras, pe.edu.modelo.Trabajador, pe.edu.modelo.Proveedor" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Órdenes de Compra - Jefe de Almacen</title>

    <!-- Estilos -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Style/Style.css">

    <!-- DataTables -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.4.2/css/buttons.bootstrap5.min.css">
</head>
<body>

<div class="d-flex">
    <jsp:include page="/Almacenero/Componentes/sidebar.jsp" />

    <div class="container mt-4">
        <h1 class="mb-4">Órdenes de Compra </h1>

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
                        <form action="OrdenDeComprasControlador2" method="post" style="display: flex; gap: 4px;">
                            <input type="hidden" name="accion" value="cambiarEstado">
                            <input type="hidden" name="id" value="<%= orden.getIdOrdenCompra() %>">
                            <select 
                                name="estado" 
                                class="form-select form-select-sm estado-select" 
                                data-id="<%= orden.getIdOrdenCompra() %>"
                                <%= (orden.getEstado().equalsIgnoreCase("aprobado") || orden.getEstado().equalsIgnoreCase("cancelado")) ? "disabled" : "" %>
                            >
                                <option value="pendiente" <%= orden.getEstado().equalsIgnoreCase("pendiente") ? "selected" : "" %>>⏳ Pendiente</option>
                                <option value="aprobado" <%= orden.getEstado().equalsIgnoreCase("aprobado") ? "selected" : "" %>>✔ Aprobado</option>
                                <option value="cancelado" <%= orden.getEstado().equalsIgnoreCase("cancelado") ? "selected" : "" %>>❌ Cancelado</option>
                            </select>
                        </form>
                    </td>
                    <td>
                        <!-- Botón que abre el modal -->
                        <button 
                            type="button" 
                            class="btn btn-sm btn-info" 
                            data-bs-toggle="modal" 
                            data-bs-target="#detalleModal<%= orden.getIdOrdenCompra() %>">
                            <i class="fas fa-eye"></i> Ver
                        </button>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr><td colspan="7" class="text-center">No hay órdenes registradas.</td></tr>
                <% } %>
            </tbody>
        </table>

        <a class="btn btn-secondary mt-3" href="/Admin/AdminIndex.jsp">Regresar</a>
    </div>
</div>

<%-- Aquí agregamos los modales para cada orden --%>
<%
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

<div class="modal fade" id="detalleModal<%= orden.getIdOrdenCompra() %>" tabindex="-1" aria-labelledby="detalleModalLabel<%= orden.getIdOrdenCompra() %>" aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="detalleModalLabel<%= orden.getIdOrdenCompra() %>">Detalle Orden #<%= orden.getIdOrdenCompra() %></h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body">
        <p><strong>ID Orden:</strong> <%= orden.getIdOrdenCompra() %></p>
        <p><strong>Fecha:</strong> <%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(orden.getFechaOrden()) %></p>
        <p><strong>Total:</strong> S/. <%= String.format("%.2f", orden.getTotal()) %></p>
        <p><strong>Trabajador:</strong> <%= trabajadorNombre %></p>
        <p><strong>Proveedor:</strong> <%= proveedorNombre %></p>
        <p><strong>Estado:</strong> <%= orden.getEstado() %></p>
        <%-- Puedes agregar más detalles aquí --%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>

<%
        }
    }
%>

<!-- Scripts Bootstrap & DataTables -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>

<script src="https://cdn.datatables.net/buttons/2.4.2/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.bootstrap5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.print.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.colVis.min.js"></script>

<script>
    $(document).ready(function () {
        $('.estado-select').each(function() {
            // Guardamos el estado original en un atributo data para referencia
            $(this).data('estado-original', $(this).val());
        });

        $('.estado-select').change(function() {
            const select = $(this);
            const nuevoEstado = select.val();
            const estadoOriginal = select.data('estado-original');

            // Solo hacer confirmación si el estado original es pendiente y cambia a aprobado o cancelado
            if (estadoOriginal === 'pendiente' && (nuevoEstado === 'aprobado' || nuevoEstado === 'cancelado')) {
                const confirmado = confirm("¿Está seguro de cambiar el estado a '" + nuevoEstado + "'?");

                if (confirmado) {
                    // Enviar el formulario para actualizar el estado
                    select.closest('form').submit();

                    // Bloquear el select para que ya no pueda cambiarse después
                    select.prop('disabled', true);
                } else {
                    // Si cancela, vuelve al estado original
                    select.val(estadoOriginal);
                }
            } else if (estadoOriginal !== 'pendiente') {
                // Si ya está aprobado o cancelado, no debe cambiar (por si)
                select.val(estadoOriginal);
                select.prop('disabled', true);
            }
        });
    });
</script>

<script>
    $(document).ready(function () {
        $('#tablaOrdenes').DataTable({
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

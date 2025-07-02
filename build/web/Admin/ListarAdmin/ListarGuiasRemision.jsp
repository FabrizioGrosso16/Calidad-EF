<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<%@ page import="java.util.*, pe.edu.modelo.GuiasRemision, pe.edu.modelo.Trabajador, pe.edu.modelo.Proveedor, pe.edu.modelo.Producto" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Guías de Remisión</title>

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
        <h1 class="mb-4">Guías de Remisión</h1>

        <a class="btn btn-success mb-3" href="GuiasRemisionControlador?accion=nuevo">
            <i class="fas fa-plus"></i> Agregar Guía
        </a>

        <table id="tablaGuias" class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Fecha</th>
                    <th>Razón Social</th>
                    <th>Cantidad</th>
                    <th>Trabajador</th>
                    <th>Proveedor</th>
                    <th>Producto</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
            <%
                List<GuiasRemision> guias = (List<GuiasRemision>) request.getAttribute("guias");
                List<Trabajador> trabajadores = (List<Trabajador>) request.getAttribute("trabajadores");
                List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
                List<Producto> productos = (List<Producto>) request.getAttribute("productos");

                if (guias != null && !guias.isEmpty()) {
                    for (GuiasRemision guia : guias) {
                        String trabajadorNombre = "Desconocido";
                        String proveedorNombre = "Desconocido";
                        String productoNombre = "Desconocido";

                        for (Trabajador t : trabajadores) {
                            if (t.getIdTrabajador() == guia.getIdTrabajador()) {
                                trabajadorNombre = t.getNombre();
                                break;
                            }
                        }
                        for (Proveedor p : proveedores) {
                            if (p.getIdProveedor() == guia.getIdProveedor()) {
                                proveedorNombre = p.getRazonSocial();
                                break;
                            }
                        }
                        for (Producto pr : productos) {
                            if (pr.getIdProducto() == guia.getIdProducto()) {
                                productoNombre = pr.getNombre();
                                break;
                            }
                        }
            %>
                <tr>
                    <td><%= guia.getIdGuia() %></td>
                    <td><%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(guia.getFecha()) %></td>
                    <td><%= guia.getRazonSocial() %></td>
                    <td><%= guia.getCantidad() %></td>
                    <td><%= trabajadorNombre %></td>
                    <td><%= proveedorNombre %></td>
                    <td><%= productoNombre %></td>
                    <td>
                        <form action="GuiasRemisionControlador" method="post" style="display: flex; gap: 4px; align-items: center;">
                            <input type="hidden" name="accion" value="cambiarEstado">
                            <input type="hidden" name="id" value="<%= guia.getIdGuia() %>">

                            <select name="estado" class="form-select form-select-sm" onchange="this.form.submit()" style="width: auto;">
                                <option value="aceptado" <%= guia.getEstado().equalsIgnoreCase("Por Revisar") ? "selected" : "" %>>✔ Revisado</option>
                                <option value="pendiente" <%= guia.getEstado().equalsIgnoreCase("Revisado") ? "selected" : "" %>>⏳ Por Revisar</option>
                                <option value="cancelado" <%= guia.getEstado().equalsIgnoreCase("Rechazado") ? "selected" : "" %>>❌ Rechazado</option>
                            </select
                        </form>
                    </td>
                    <td>
                        <a class="btn btn-sm btn-primary" href="GuiasRemisionControlador?accion=editar&id=<%= guia.getIdGuia() %>">
                            <i class="fas fa-edit"></i> Editar
                        </a>
                        <span>|</span>
                        <a class="btn btn-sm btn-danger" href="GuiasRemisionControlador?accion=eliminar&id=<%= guia.getIdGuia() %>"
                           onclick="return confirm('¿Está seguro de eliminar esta guía de remisión?');">
                            <i class="fas fa-trash-alt"></i> Eliminar
                        </a>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="9" class="text-center">No hay guías de remisión registradas.</td>
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
        $('#tablaGuias').DataTable({
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

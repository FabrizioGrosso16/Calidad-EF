<%-- 
    Document   : ListarContactoProveedor
    Created on : 21 may. 2025, 15:47:56
    Author     : User001
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<%@ page import="java.util.*, pe.edu.modelo.ContactoProveedor, pe.edu.modelo.Proveedor" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Contactos de Proveedores</title>

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
        <h1 class="mb-4">Contactos de Proveedores</h1>
        <a class="btn btn-success mb-3" href="<%= request.getContextPath() %>/ContactoProveedorControlador?accion=nuevo">
            <i class="fas fa-plus-circle"></i> Agregar Contacto
        </a>

        <table id="tablaContactos" class="table table-bordered table-hover display nowrap" style="width:100%">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Teléfono</th>
                    <th>Email</th>
                    <th>Proveedor</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<ContactoProveedor> contactos = (List<ContactoProveedor>) request.getAttribute("contactos");
                    List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");

                    if (contactos != null && !contactos.isEmpty()) {
                        for (ContactoProveedor c : contactos) {
                            String nombreProveedor = "Desconocido";

                            for (Proveedor p : proveedores) {
                                if (p.getIdProveedor() == c.getIdProveedor()) {
                                    nombreProveedor = p.getRazonSocial();
                                    break;
                                }
                            }
                %>
                <tr>
                    <td><%= c.getIdContacto() %></td>
                    <td><%= c.getNombre() %></td>
                    <td><%= c.getTelefono() %></td>
                    <td><%= c.getEmail() %></td>
                    <td><%= nombreProveedor %></td>
                    <td>
                        <a class="btn btn-primary btn-sm" href="<%= request.getContextPath() %>/ContactoProveedorControlador?accion=editar&id=<%= c.getIdContacto() %>">
                            <i class="fas fa-edit"></i> Editar
                        </a>
                        <span>|</span>
                        <a class="btn btn-danger btn-sm" href="<%= request.getContextPath() %>/ContactoProveedorControlador?accion=eliminar&id=<%= c.getIdContacto() %>"
                           onclick="return confirm('¿Está seguro de eliminar este contacto?');">
                            <i class="fas fa-trash-alt"></i> Eliminar
                        </a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6" class="text-center">No hay contactos registrados.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <a class="btn btn-secondary mt-3" href="/Admin/AdminIndex.jsp">Regresar</a>
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

<!-- Inicialización de DataTable con idioma español -->
<script>
    $(document).ready(function () {
        $('#tablaContactos').DataTable({
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


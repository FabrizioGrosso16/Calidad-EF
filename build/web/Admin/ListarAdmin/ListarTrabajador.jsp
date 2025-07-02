<%-- 
    Document   : ListarRol
    Created on : 23 may. 2025, 03:04:11
    Author     : User001
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<%@ page import="java.util.*, pe.edu.modelo.Trabajador, pe.edu.modelo.Rol" %>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Trabajadores</title>

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

    <!-- Main Content -->
    <div class="container mt-4">
        <h1 class="mb-4">Trabajadores</h1>
        <a class="btn btn-success mb-3" href="<%= request.getContextPath() %>/TrabajadorControlador?accion=nuevo">
            <i class="fas fa-plus-circle"></i> Agregar Trabajador
        </a>

        <table id="tablaTrabajadores" class="table table-bordered table-hover display nowrap" style="width:100%">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>DNI</th>
                    <th>Teléfono</th>
                    <th>Correo</th>
                    <th>Rol (ID)</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Trabajador> trabajadores = (List<Trabajador>) request.getAttribute("trabajadores");
                    List<Rol> roles = (List<Rol>) request.getAttribute("roles");

                    if (trabajadores != null && !trabajadores.isEmpty()) {
                        for (Trabajador t : trabajadores) {
                            String nombreRol = "Desconocido";
                            for (Rol r : roles) {
                                if (r.getIdRol() == t.getIdRol()) {
                                    nombreRol = r.getNombre();
                                    break;
                                }
                            }
                %>
                <tr>
                    <td><%= t.getIdTrabajador() %></td>
                    <td><%= t.getNombre() %></td>
                    <td><%= t.getApellido() %></td>
                    <td><%= t.getDni() %></td>
                    <td><%= t.getTelefono() %></td>
                    <td><%= t.getCorreo() %></td>
                    <td><%= nombreRol %></td>
                    <td>
                        <a class="btn btn-primary btn-sm" href="<%= request.getContextPath() %>/TrabajadorControlador?accion=editar&id=<%= t.getIdTrabajador() %>">
                            <i class="fas fa-edit"></i> Editar
                        </a>
                        <span>|</span>
                        <a class="btn btn-danger btn-sm" href="<%= request.getContextPath() %>/TrabajadorControlador?accion=eliminar&id=<%= t.getIdTrabajador() %>"
                           onclick="return confirm('¿Está seguro de eliminar este trabajador?');">
                            <i class="fas fa-trash-alt"></i> Eliminar
                        </a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="8" class="text-center">No hay trabajadores registrados.</td>
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
        $('#tablaTrabajadores').DataTable({
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

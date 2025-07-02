<%-- 
    Document   : ListarMarcaProducto
    Created on : 21 may. 2025, 18:24:28
    Author     : User001
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAlmacenero.jsp" %>
<%@ page import="java.util.*, pe.edu.modelo.MarcaProducto" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Marcas de Producto - Jefe de Almacen</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Style/Style.css">
</head>
<body>
    
    <div class="d-flex">
        <!-- Sidebar izquierdo del almacenero -->
        <jsp:include page="/Almacenero/Componentes/sidebar.jsp" />

        <div class="container mt-4">
            <h1 class="mb-4">Marcas de Producto</h1>
            <a class="btn btn-success mb-3" href="<%= request.getContextPath() %>/MarcaProductoControlador2?accion=nuevo">Agregar Marca</a>
            
            <table class="table table-bordered table-hover">
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
                                <a class="btn btn-primary btn-sm" href="<%= request.getContextPath() %>/MarcaProductoControlador2?accion=editar&id=<%= mp.getIdMarcaProducto() %>">
                                    <i class="fas fa-edit"></i> Editar
                                </a>

                                <span>|</span>

                                <a class="btn btn-danger btn-sm" href="<%= request.getContextPath() %>/MarcaProductoControlador2?accion=eliminar&id=<%= mp.getIdMarcaProducto() %>" onclick="return confirm('¿Está seguro de eliminar esta marca de producto?');">
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

            <a class="btn btn-secondary" href="<%= request.getContextPath() %>/Almacenero/AlmaceneroIndex.jsp">Regresar</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        const navbarCollapse = document.getElementById('navbarNav');

        navbarCollapse.addEventListener('show.bs.collapse', () => {
            navbarCollapse.classList.add('animate__animated', 'animate__fadeIn');
            navbarCollapse.classList.remove('animate__fadeOut');
        });

        navbarCollapse.addEventListener('hide.bs.collapse', () => {
            navbarCollapse.classList.remove('animate__fadeIn');
            navbarCollapse.classList.add('animate__animated', 'animate__fadeOut');
        });
    </script>
</body>
</html>


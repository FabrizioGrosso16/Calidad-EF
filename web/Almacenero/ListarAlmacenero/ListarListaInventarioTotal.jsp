<%-- 
    Document   : ListarListaInventarioTotal
    Created on : 26 may. 2025, 02:07:39
    Author     : User001
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAlmacenero.jsp" %>
<%@ page import="java.util.*, pe.edu.modelo.ListaInventarioTotal, pe.edu.modelo.Trabajador, pe.edu.modelo.Producto" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista Inventario Total - Jefe de Almacen</title>

    <!-- Bootstrap y estilos -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Style/Style.css">

    <!-- DataTables + Botones Exportación -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.4.2/css/buttons.bootstrap5.min.css">
</head>
<body>

<div class="d-flex">
    <!-- Sidebar almacenero -->
    <jsp:include page="/Almacenero/Componentes/sidebar.jsp" />

    <div class="container mt-4">
        <h1 class="mb-4">Lista Inventario Total</h1>

        <a class="btn btn-success mb-3" href="<%= request.getContextPath() %>/ListaInventarioTotalControlador2?accion=nuevo">Agregar Inventario</a>

        <table id="tablaInventario" class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Cantidad</th>
                    <th>Precio</th>
                    <th>Lote</th>
                    <th>Trabajador</th>
                    <th>Producto</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<ListaInventarioTotal> listaInventario = (List<ListaInventarioTotal>) request.getAttribute("listaInventario");
                    List<Trabajador> trabajadores = (List<Trabajador>) request.getAttribute("trabajadores");
                    List<Producto> productos = (List<Producto>) request.getAttribute("productos");

                    if (listaInventario != null && !listaInventario.isEmpty()) {
                        for (ListaInventarioTotal lit : listaInventario) {
                            String trabajadorNombre = "Desconocido";
                            for (Trabajador trabajador : trabajadores) {
                                if (trabajador.getIdTrabajador() == lit.getIdTrabajador()) {
                                    trabajadorNombre = trabajador.getNombre();
                                    break;
                                }
                            }

                            String productoNombre = "Desconocido";
                            for (Producto producto : productos) {
                                if (producto.getIdProducto() == lit.getIdProducto()) {
                                    productoNombre = producto.getNombre();
                                    break;
                                }
                            }
                %>
                    <tr>
                        <td><%= lit.getIdLIT() %></td>
                        <td>
                            <form action="<%= request.getContextPath() %>/ListaInventarioTotalControlador2" method="post" class="d-flex">
                                <input type="hidden" name="accion" value="actualizarCantidad">
                                <input type="hidden" name="idLIT" value="<%= lit.getIdLIT() %>">
                                <input type="number" name="nuevaCantidad" value="<%= lit.getCantidad() %>" class="form-control form-control-sm me-1" min="0" required>
                                <button type="submit" class="btn btn-sm btn-warning" title="Actualizar cantidad">
                                    <i class="fas fa-save"></i>
                                </button>
                            </form>
                        </td>
                        <td>S/. <%= String.format("%.2f", lit.getPrecio()) %></td>
                        <td><%= lit.getLote() %></td>
                        <td><%= trabajadorNombre %></td>
                        <td><%= productoNombre %></td>
                        <td>
                            <a class="btn btn-primary btn-sm" href="<%= request.getContextPath() %>/ListaInventarioTotalControlador2?accion=editar&id=<%= lit.getIdLIT() %>">
                                <i class="fas fa-edit"></i> Editar
                            </a>
                            <span>|</span>
                            <a class="btn btn-danger btn-sm" href="<%= request.getContextPath() %>/ListaInventarioTotalControlador2?accion=eliminar&id=<%= lit.getIdLIT() %>" onclick="return confirm('¿Está seguro de eliminar este inventario?');">
                                <i class="fas fa-trash-alt"></i> Eliminar
                            </a>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="7" class="text-center">No hay inventarios registrados.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <a class="btn btn-secondary mt-3" href="<%= request.getContextPath() %>/Almacenero/AlmaceneroIndex.jsp">Regresar</a>
    </div>
</div>

<!-- JS Bootstrap, jQuery y DataTables -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>

<!-- Exportación con botones -->
<script src="https://cdn.datatables.net/buttons/2.4.2/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.bootstrap5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.print.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.colVis.min.js"></script>

<!-- Inicialización de DataTables -->
<script>
    $(document).ready(function () {
        $('#tablaInventario').DataTable({
            responsive: true,
            language: {
                url: 'https://cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json'
            },
            dom: 'Bfrtip',
            buttons: [
    {
        extend: 'copy',
        text: 'Copiar'
    },
    {
        extend: 'pdf',
        text: 'Exportar PDF',
        exportOptions: {
            columns: ':not(:last-child)',
            format: {
                body: function (data, row, column, node) {
                    if (column === 1) { // columna de cantidad
                        let input = $('input[name="nuevaCantidad"]', node);
                        return input.length ? input.val() : '';
                    }
                    return $(node).text().trim();
                }
            }
        }
    },
    {
        extend: 'print',
        text: 'Imprimir',
        exportOptions: {
            columns: ':not(:last-child)',
            format: {
                body: function (data, row, column, node) {
                    if (column === 1) {
                        let input = $('input[name="nuevaCantidad"]', node);
                        return input.length ? input.val() : '';
                    }
                    return $(node).text().trim();
                }
            }
        }
    }
]
        });
    });
</script>

<!-- Navbar animación -->
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

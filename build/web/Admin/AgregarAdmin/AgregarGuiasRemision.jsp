<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Validacion/ValidacionAdmin.jsp" %>
<%@ page import="java.util.*, pe.edu.modelo.Trabajador, pe.edu.modelo.Proveedor, pe.edu.modelo.Producto" %>

<%
    List<Trabajador> trabajadores = (List<Trabajador>) request.getAttribute("trabajadores");
    List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Guía de Remisión</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Style/Style.css">
</head>
<body>

<div class="d-flex">
    <!-- Sidebar -->
    <jsp:include page="/Admin/Componentes/sidebar.jsp" />

    <!-- Contenido principal -->
    <div class="container mt-5">
        <h2 class="mb-4">Agregar Nueva Guía de Remisión</h2>

        <form action="<%= request.getContextPath() %>/GuiasRemisionControlador" method="POST">
            <input type="hidden" name="accion" value="agregar">

            <div class="mb-3">
                <label for="razonSocial" class="form-label">Razón Social</label>
                <input type="text" class="form-control" id="razonSocial" name="razonSocial" required>
            </div>

            <div class="mb-3">
                <label for="fecha" class="form-label">Fecha</label>
                <input type="date" class="form-control" id="fecha" name="fecha"
                       value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" readonly>
            </div>

            <div class="mb-3">
                <label for="idTrabajador" class="form-label">Trabajador</label>
                <select class="form-select" id="idTrabajador" name="idTrabajador" required>
                    <option value="" disabled selected>Seleccione un trabajador</option>
                    <% for (Trabajador t : trabajadores) { %>
                        <option value="<%= t.getIdTrabajador() %>"><%= t.getNombre() %></option>
                    <% } %>
                </select>
            </div>

            <div class="mb-3">
                <label for="idProveedor" class="form-label">Proveedor</label>
                <select class="form-select" id="idProveedor" name="idProveedor" required>
                    <option value="" disabled selected>Seleccione un proveedor</option>
                    <% for (Proveedor p : proveedores) { %>
                        <option value="<%= p.getIdProveedor() %>"><%= p.getRazonSocial() %></option>
                    <% } %>
                </select>
            </div>

            <div class="mb-3">
                <label class="form-label">Agregar Productos</label>
                <div class="input-group mb-3">
                    <select class="form-select" id="selectProducto">
                        <option value="" disabled selected>Seleccione un producto</option>
                        <% for (Producto pr : productos) { %>
                            <option value="<%= pr.getIdProducto() %>"><%= pr.getNombre() %></option>
                        <% } %>
                    </select>
                    <input type="number" min="1" id="cantidadProducto" class="form-control" placeholder="Cantidad">
                    <button type="button" id="btnAgregarProducto" class="btn btn-primary">Agregar</button>
                </div>

                <table class="table table-bordered" id="tablaProductos">
                    <thead>
                    <tr>
                        <th>Producto</th>
                        <th>Cantidad</th>
                        <th>Acción</th>
                    </tr>
                    </thead>
                    <tbody>
                        <!-- Productos agregados se insertarán aquí -->
                    </tbody>
                </table>
            </div>

            <div class="mb-3">
                <label for="estado" class="form-label">Estado</label>
                <select class="form-select" id="estado" name="estado" required>
                    <option value="" disabled selected>Seleccione estado</option>
                    <option value="Por Revisar">Por Revisar</option>
                    <option value="Revisado">Revisado</option>
                    <option value="Rechazado">Rechazado</option>
                </select>
            </div>

            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="<%= request.getContextPath() %>/GuiasRemisionControlador?accion=listar" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- Script para manejar productos y validación -->
<script>
    const productos = {
        <% if (productos != null) {
            for (int i = 0; i < productos.size(); i++) {
                Producto p = productos.get(i);
                out.print("'" + p.getIdProducto() + "': '" + p.getNombre() + "'");
                if (i < productos.size() - 1) out.print(",");
            }
        } %>
    };

    const btnAgregar = document.getElementById('btnAgregarProducto');
    const selectProducto = document.getElementById('selectProducto');
    const cantidadProducto = document.getElementById('cantidadProducto');
    const tablaProductosBody = document.querySelector('#tablaProductos tbody');

    btnAgregar.addEventListener('click', () => {
        const prodId = selectProducto.value;
        const cantidad = parseInt(cantidadProducto.value);

        if (!prodId) {
            alert('Seleccione un producto.');
            return;
        }

        if (!cantidad || cantidad <= 0) {
            alert('Ingrese una cantidad válida.');
            return;
        }

        // Verificar si ya fue agregado
        let exists = false;
        tablaProductosBody.querySelectorAll('input[name="productos[]"]').forEach(input => {
            if (input.value === prodId) {
                exists = true;
            }
        });

        if (exists) {
            alert('Este producto ya está en la lista.');
            return;
        }

        // Crear fila con inputs ocultos
        const fila = document.createElement('tr');
        fila.innerHTML = `
            <td>
                ${productos[prodId]}
                <input type="hidden" name="productos[]" value="${prodId}">
            </td>
            <td>
                ${cantidad}
                <input type="hidden" name="cantidades[]" value="${cantidad}">
            </td>
            <td>
                <button type="button" class="btn btn-danger btn-sm btnEliminar">Eliminar</button>
            </td>
        `;
        tablaProductosBody.appendChild(fila);

        // Limpiar campos
        selectProducto.value = "";
        cantidadProducto.value = "";
    });

    tablaProductosBody.addEventListener('click', (e) => {
        if (e.target.classList.contains('btnEliminar')) {
            e.target.closest('tr').remove();
        }
    });

    // Validar que se agregue al menos un producto antes de enviar
    document.querySelector('form').addEventListener('submit', function(e) {
        const productosAgregados = document.querySelectorAll('input[name="productos[]"]');
        if (productosAgregados.length === 0) {
            e.preventDefault();
            alert('Debe agregar al menos un producto antes de guardar.');
        }
    });
</script>

</body>
</html>

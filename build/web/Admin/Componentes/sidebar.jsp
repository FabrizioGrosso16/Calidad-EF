
<%-- 
    Document   : sidebar
    Created on : 20 may. 2025, 23:16:51
    Author     : User001
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
/* Estilos para modo oscuro */
.dark-mode {
    background-color: #1a1a1a !important;
}
.dark-mode .nav-link,
.dark-mode .navbar-brand,
.dark-mode .text-dark {
    color: #f0f0f0 !important;
}
.dark-mode .text-danger {
    color: #ff4d4d !important;
}

/* Desactiva todas las animaciones y transiciones */
.no-animation * {
    transition: none !important;
    animation: none !important;
}
</style>

<nav class="navbar navbar-light" id="sidebarNavbar" style="background-color: #FFB6C1; border-right: 1px solid #ddd;">
    <div class="container-fluid flex-column">

        <!-- Botón de modo oscuro -->
        <button id="darkModeToggle" class="btn btn-sm btn-outline-dark mb-3 align-self-start">
            🌙 Modo Oscuro
        </button>

        <a class="navbar-brand small-brand-text" href="#">
            <i class="fas fa-store" id="store-icon"></i> MiTienda_Darsol
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav flex-column align-items-start">
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/CategoriaProveedorControlador?accion=listar">
                        Categoria Proveedor
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/ProveedorControlador?accion=listar">
                        Proveedor
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/ContactoProveedorControlador?accion=listar">
                        Contacto Proveedor
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/MarcaProductoControlador?accion=listar">
                        Marca Producto
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/CategoriaProductoControlador?accion=listar">
                        Categoria Producto
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/ProductoControlador?accion=listar">
                        Producto
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/ListaInventarioTotalControlador?accion=listar">
                        Lista Inventario Total
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/ListaInventarioTotalControlador?accion=historial">
                        Ver Detalles de Productos
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/TrabajadorControlador?accion=listar">
                        Ver Trabajadores
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/OrdenDeComprasControlador?accion=listar">
                        Ver Orden de Compras
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/GuiasRemisionControlador?accion=listar">
                        Ver Guias de Remision
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-danger" href="<%= request.getContextPath() %>/LogoutServlet">
                        Cerrar Sesión
                    </a>
                </li>
            </ul>
        </div>

        <!-- Mostrar el nombre del usuario en el sidebar -->
        <%
            String nombreUsuario = (String) session.getAttribute("usuario");
            if (nombreUsuario == null) {
                nombreUsuario = "Usuario";
            }
        %>
        <h1></h1>
        <div class="mt-auto text-center text-dark small p-2 border-top">
            👤 <strong><%= nombreUsuario %></strong>
        </div>
    </div>
</nav>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const toggleBtn = document.getElementById('darkModeToggle');
        const sidebar = document.getElementById('sidebarNavbar');
        const body = document.body;

        // Aplicar modo oscuro si ya estaba activado
        const modoOscuroActivo = localStorage.getItem('modoOscuro') === 'true';

        if (modoOscuroActivo) {
            sidebar.classList.add('dark-mode', 'no-animation');
            body.classList.add('dark-mode', 'no-animation');
            toggleBtn.innerHTML = '☀️ Modo Claro';
        }

        toggleBtn.addEventListener('click', function () {
            const isDark = !sidebar.classList.contains('dark-mode');
            
            // Alternar clases
            sidebar.classList.toggle('dark-mode');
            sidebar.classList.toggle('no-animation');
            body.classList.toggle('dark-mode');
            body.classList.toggle('no-animation');

            // Cambiar texto y guardar en localStorage
            toggleBtn.innerHTML = isDark ? '☀️ Modo Claro' : '🌙 Modo Oscuro';
            localStorage.setItem('modoOscuro', isDark);
        });
    });
</script>

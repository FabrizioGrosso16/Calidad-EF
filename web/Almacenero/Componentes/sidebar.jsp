<%-- 
    Document   : sidebar
    Created on : 21 may. 2025, 17:54:09
    Author     : User001
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    .dark-mode {
        background-color: #1a1a1a !important;
        color: #f0f0f0 !important;
    }

    .dark-mode .nav-link {
        color: #f0f0f0 !important;
    }

    .dark-mode .navbar {
        background-color: #2c2c2c !important;
        border-color: #444 !important;
    }

    .dark-mode .btn-outline-dark {
        border-color: #f0f0f0;
        color: #f0f0f0;
    }

    .dark-mode .btn-outline-dark:hover {
        background-color: #f0f0f0;
        color: #1a1a1a;
    }

    .no-animation {
        transition: none !important;
    }
</style>

<nav class="navbar navbar-light" style="background-color: #FFB6C1; border-right: 1px solid #ddd;">
    <div class="container-fluid flex-column">
          <!-- Botón de Modo Oscuro -->
        <div class="mt-3 text-center">
            <button id="darkModeToggle" class="btn btn-sm btn-outline-dark">🌙 Modo Oscuro</button>
        </div>
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
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/MarcaProductoControlador2?accion=listar">
                        Marca Producto
                    </a> 
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/CategoriaProductoControlador2?accion=listar">
                        Categoria Producto
                    </a> 
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/ProductoControlador2?accion=listar">
                        Producto
                    </a> 
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/ListaInventarioTotalControlador2?accion=listar">
                        Lista Inventario Total
                    </a> 
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<%= request.getContextPath() %>/OrdenDeComprasControlador2?accion=listar">
                        Ver Ordenes de Compra
                    </a> 
                </li>
                <li class="nav-item">
                    <a class="nav-link text-danger" href="<%= request.getContextPath() %>/LogoutServlet">Cerrar Sesión</a>
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

<!-- Script Modo Oscuro Persistente -->
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const toggleBtn = document.getElementById('darkModeToggle');
        const sidebar = document.querySelector('nav.navbar');
        const body = document.body;

        // Activar si estaba guardado en localStorage
        const modoOscuroActivo = localStorage.getItem('modoOscuro') === 'true';
        if (modoOscuroActivo) {
            sidebar.classList.add('dark-mode', 'no-animation');
            body.classList.add('dark-mode', 'no-animation');
            toggleBtn.innerHTML = '☀️ Modo Claro';
        }

        toggleBtn.addEventListener('click', function () {
            const isDark = !sidebar.classList.contains('dark-mode');

            sidebar.classList.toggle('dark-mode');
            sidebar.classList.toggle('no-animation');
            body.classList.toggle('dark-mode');
            body.classList.toggle('no-animation');

            toggleBtn.innerHTML = isDark ? '☀️ Modo Claro' : '🌙 Modo Oscuro';
            localStorage.setItem('modoOscuro', isDark);
        });
    });
</script>
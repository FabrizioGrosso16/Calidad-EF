<%-- 
    Document   : ValidacionAdministrador
    Created on : 1 may. 2025, 19:35:46
    Author     : User001
--%>

<%
    HttpSession _session = request.getSession(false);
    if (session == null || session.getAttribute("usuario") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp?error=sesion_invalida");
        return;
    }

    String rol = (String) session.getAttribute("rol");
    if (!"administrador".equalsIgnoreCase(rol)) {
        response.sendRedirect(request.getContextPath() + "/index.jsp?error=acceso_no_autorizado");
        return;
    }
%>
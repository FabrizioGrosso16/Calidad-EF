<%-- 
    Document   : ValidacionAlmacenero
    Created on : 1 may. 2025, 19:36:06
    Author     : User001
--%>

<%
    HttpSession _session = request.getSession(false);
    if (session == null || session.getAttribute("usuario") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp?error=sesion_invalida");
        return;
    }

    String rol = (String) session.getAttribute("rol");
    if (!"jefe de almacen".equalsIgnoreCase(rol)) {
        response.sendRedirect(request.getContextPath() + "/index.jsp?error=acceso_no_autorizado");
        return;
    }
%>

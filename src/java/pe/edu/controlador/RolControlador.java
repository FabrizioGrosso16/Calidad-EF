/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.edu.dao.RolDAO;
import pe.edu.modelo.Rol;
import pe.edu.modelo.Trabajador;

@WebServlet(name = "RolControlador", urlPatterns = {"/RolControlador"})
public class RolControlador extends HttpServlet {

    private RolDAO rolDAO = new RolDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar"; // Acción por defecto
        }

        try {
            switch (accion) {
                case "listar":
                    listarRoles(request, response);
                    break;
                case "verTrabajadores":
                    verTrabajadoresPorRol(request, response);
                    break;
                default:
                    listarRoles(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar errores, tal vez redirigiendo a una página de error
        }
    }

    // Listar los roles disponibles
    private void listarRoles(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        List<Rol> listaRoles = rolDAO.listar();
        request.setAttribute("roles", listaRoles); // Asegúrate de que esta línea esté presente
        request.getRequestDispatcher("Admin/ListarAdmin/ListarRol.jsp").forward(request, response);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    // Ver los trabajadores asociados a un rol específico
    private void verTrabajadoresPorRol(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        int idRol = Integer.parseInt(request.getParameter("idRol"));  // Se obtiene el ID del rol desde el parámetro
        List<Trabajador> trabajadores = rolDAO.buscarTrabajadoresPorRol(idRol); // Busca los trabajadores vinculados a ese rol
        request.setAttribute("trabajadores", trabajadores);  // Se pasa la lista de trabajadores al JSP
        request.getRequestDispatcher("Admin/ListarAdmin/ListarTrabajadoresPorRol.jsp").forward(request, response); // Redirige al JSP para mostrar los trabajadores
    }
}


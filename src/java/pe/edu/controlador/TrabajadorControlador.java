/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.controlador;

import pe.edu.dao.TrabajadorDAO;
import pe.edu.dao.RolDAO;
import pe.edu.modelo.Trabajador;
import pe.edu.modelo.Rol;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/TrabajadorControlador")
public class TrabajadorControlador extends HttpServlet {
    TrabajadorDAO dao = new TrabajadorDAO();
    RolDAO rolDAO = new RolDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";

            switch (accion) {
                case "listar":
                    List<Trabajador> trabajadores = dao.listar();
                    List<Rol> roles = rolDAO.listar();
                    request.setAttribute("trabajadores", trabajadores);
                    request.setAttribute("roles", roles);
                    request.getRequestDispatcher("Admin/ListarAdmin/ListarTrabajador.jsp").forward(request, response);
                    break;

                case "nuevo":
                    List<Rol> rolesNuevo = rolDAO.listar();
                    request.setAttribute("roles", rolesNuevo);
                    request.getRequestDispatcher("Admin/AgregarAdmin/AgregarTrabajador.jsp").forward(request, response);
                    break;

                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Trabajador trabajador = dao.obtenerPorId(id);
                    List<Rol> rolesEditar = rolDAO.listar();
                    request.setAttribute("trabajador", trabajador);
                    request.setAttribute("roles", rolesEditar);
                    request.getRequestDispatcher("Admin/EditarAdmin/EditarTrabajador.jsp").forward(request, response);
                    break;

                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idEliminar);
                    response.sendRedirect("TrabajadorControlador?accion=listar");
                    break;

                default:
                    response.sendRedirect("TrabajadorControlador?accion=listar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Opcional: redirige a una página de error personalizada
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "";

            Trabajador trabajador = new Trabajador(
                0,
                request.getParameter("nombre"),
                request.getParameter("dni"),
                request.getParameter("apellido"),
                request.getParameter("telefono"),
                request.getParameter("correo"),
                Integer.parseInt(request.getParameter("idRol")),
                request.getParameter("clave")
            );

            if ("agregar".equals(accion)) {
                dao.agregar(trabajador);
            } else if ("actualizar".equals(accion)) {
                trabajador.setIdTrabajador(Integer.parseInt(request.getParameter("idTrabajador")));
                dao.actualizar(trabajador);
            }

            response.sendRedirect("TrabajadorControlador?accion=listar");
        } catch (Exception e) {
            e.printStackTrace();
            // Opcional: redirige a una página de error personalizada
        }
    }
}


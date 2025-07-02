/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.controlador;

import pe.edu.dao.CategoriaProveedorDAO;
import pe.edu.modelo.CategoriaProveedor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/CategoriaProveedorControlador")
public class CategoriaProveedorControlador extends HttpServlet {
    CategoriaProveedorDAO dao = new CategoriaProveedorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";

            switch (accion) {
                case "listar":
                    List<CategoriaProveedor> lista = dao.listar();
                    request.setAttribute("categorias", lista);
                    request.getRequestDispatcher("Admin/ListarAdmin/ListarCategoriaProveedor.jsp").forward(request, response);
                    break;
                case "nuevo":
                    request.getRequestDispatcher("Admin/AgregarAdmin/AgregarcategoriaProveedor.jsp").forward(request, response);
                    break;
                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    CategoriaProveedor c = dao.obtenerPorId(id);
                    request.setAttribute("categoria", c);
                    request.getRequestDispatcher("Admin/EditarAdmin/EditarCategoriaProveedor.jsp").forward(request, response);
                    break;
                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idEliminar);
                    response.sendRedirect("CategoriaProveedorControlador?accion=listar");
                    break;
                default:
                    response.sendRedirect("CategoriaProveedorControlador?accion=listar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Opcional: mostrar página de error o mensaje amigable
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");

            CategoriaProveedor c = new CategoriaProveedor(
                    0,
                    request.getParameter("nombreCategoria"),
                    request.getParameter("descripcion")
            );

            if ("agregar".equals(accion)) {
                dao.agregar(c);
            } else if ("actualizar".equals(accion)) {
                c.setIdCategoriaProveedor(Integer.parseInt(request.getParameter("idCategoriaProveedor")));
                dao.actualizar(c);
            }
            response.sendRedirect("CategoriaProveedorControlador?accion=listar");
        } catch (Exception e) {
            e.printStackTrace();
            // Opcional: mostrar página de error o mensaje amigable
        }
    }
}


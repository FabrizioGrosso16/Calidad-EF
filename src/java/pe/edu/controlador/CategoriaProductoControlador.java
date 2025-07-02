/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.controlador;

import pe.edu.dao.CategoriaProductoDAO;
import pe.edu.modelo.CategoriaProducto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/CategoriaProductoControlador")
public class CategoriaProductoControlador extends HttpServlet {
    CategoriaProductoDAO dao = new CategoriaProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";

            switch (accion) {
                case "listar":
                    List<CategoriaProducto> lista = dao.listar();
                    request.setAttribute("categorias", lista);
                    request.getRequestDispatcher("Admin/ListarAdmin/ListarCategoriaProducto.jsp").forward(request, response);
                    break;
                case "nuevo":
                    request.getRequestDispatcher("Admin/AgregarAdmin/AgregarCategoriaProducto.jsp").forward(request, response);
                    break;
                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    CategoriaProducto c = dao.obtenerPorId(id);
                    request.setAttribute("categoria", c);
                    request.getRequestDispatcher("Admin/EditarAdmin/EditarCategoriaProducto.jsp").forward(request, response);
                    break;
                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idEliminar);
                    response.sendRedirect("CategoriaProductoControlador?accion=listar");
                    break;
                default:
                    response.sendRedirect("CategoriaProductoControlador?accion=listar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Opcional: agregar página de error o mensaje amigable
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");

            CategoriaProducto c = new CategoriaProducto(
                    0,
                    request.getParameter("nombre"),
                    request.getParameter("descripcion")
            );

            if ("agregar".equals(accion)) {
                dao.agregar(c);
            } else if ("actualizar".equals(accion)) {
                c.setIdCategoriaProducto(Integer.parseInt(request.getParameter("idCategoriaProducto")));
                dao.actualizar(c);
            }
            response.sendRedirect("CategoriaProductoControlador?accion=listar");
        } catch (Exception e) {
            e.printStackTrace();
            // Opcional: agregar página de error o mensaje amigable
        }
    }
}

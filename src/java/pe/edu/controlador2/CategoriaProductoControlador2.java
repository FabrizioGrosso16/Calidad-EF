/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.controlador2;

import pe.edu.dao.CategoriaProductoDAO;
import pe.edu.modelo.CategoriaProducto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/CategoriaProductoControlador2")
public class CategoriaProductoControlador2 extends HttpServlet {
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
                    request.getRequestDispatcher("/Almacenero/ListarAlmacenero/ListarCategoriaProducto.jsp").forward(request, response);
                    break;
                case "nuevo":
                    request.getRequestDispatcher("/Almacenero/AgregarAlmacenero/AgregarCategoriaProducto.jsp").forward(request, response);
                    break;
                case "editar":
                    int idEditar = Integer.parseInt(request.getParameter("id"));
                    CategoriaProducto cEditar = dao.obtenerPorId(idEditar);
                    request.setAttribute("categoria", cEditar);
                    request.getRequestDispatcher("/Almacenero/EditarAlmacenero/EditarCategoriaProducto.jsp").forward(request, response);
                    break;
                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idEliminar);
                    response.sendRedirect("CategoriaProductoControlador2?accion=listar");
                    break;
                default:
                    response.sendRedirect("CategoriaProductoControlador2?accion=listar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes redirigir a una página de error o mostrar mensaje
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
            response.sendRedirect("CategoriaProductoControlador2?accion=listar");
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes manejar error apropiadamente
        }
    }
}


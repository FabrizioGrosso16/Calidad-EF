/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.controlador;

import pe.edu.dao.MarcaProductoDAO;
import pe.edu.modelo.MarcaProducto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/MarcaProductoControlador")
public class MarcaProductoControlador extends HttpServlet {
    MarcaProductoDAO dao = new MarcaProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";

            switch (accion) {
                case "listar":
                    List<MarcaProducto> lista = dao.listar();
                    request.setAttribute("marcas", lista);
                    request.getRequestDispatcher("Admin/ListarAdmin/ListarMarcaProducto.jsp").forward(request, response);
                    break;
                case "nuevo":
                    request.getRequestDispatcher("Admin/AgregarAdmin/AgregarMarcaProducto.jsp").forward(request, response);
                    break;
                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    MarcaProducto marca = dao.obtenerPorId(id);
                    request.setAttribute("marca", marca);
                    request.getRequestDispatcher("Admin/EditarAdmin/EditarMarcaProducto.jsp").forward(request, response);
                    break;
                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idEliminar);
                    response.sendRedirect("MarcaProductoControlador?accion=listar");
                    break;
                default:
                    response.sendRedirect("MarcaProductoControlador?accion=listar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Opcional: manejar errores o mostrar página amigable
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");

            MarcaProducto marca = new MarcaProducto(
                    0,
                    request.getParameter("nombre"),
                    request.getParameter("descripcion")
            );

            if ("agregar".equals(accion)) {
                dao.agregar(marca);
            } else if ("actualizar".equals(accion)) {
                marca.setIdMarcaProducto(Integer.parseInt(request.getParameter("idMarcaProducto")));
                dao.actualizar(marca);
            }
            response.sendRedirect("MarcaProductoControlador?accion=listar");
        } catch (Exception e) {
            e.printStackTrace();
            // Opcional: manejar errores o mostrar página amigable
        }
    }
}

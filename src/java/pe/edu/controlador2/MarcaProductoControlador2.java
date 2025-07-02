package pe.edu.controlador2;

import pe.edu.dao.MarcaProductoDAO;
import pe.edu.modelo.MarcaProducto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/MarcaProductoControlador2") // Ruta diferente para evitar conflicto
public class MarcaProductoControlador2 extends HttpServlet {
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
                    request.getRequestDispatcher("Almacenero/ListarAlmacenero/ListarMarcaProducto.jsp").forward(request, response);
                    break;
                case "nuevo":
                    request.getRequestDispatcher("Almacenero/AgregarAlmacenero/AgregarMarcaProducto.jsp").forward(request, response);
                    break;
                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    MarcaProducto marca = dao.obtenerPorId(id);
                    request.setAttribute("marca", marca);
                    request.getRequestDispatcher("Almacenero/EditarAlmacenero/EditarMarcaProducto.jsp").forward(request, response);
                    break;
                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idEliminar);
                    response.sendRedirect("MarcaProductoControlador2?accion=listar"); // 👈 corregido
                    break;
                default:
                    response.sendRedirect("MarcaProductoControlador2?accion=listar"); // 👈 corregido
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Opcional: redirigir a página de error
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
            response.sendRedirect("MarcaProductoControlador2?accion=listar"); // 👈 corregido
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Opcional
        }
    }
}

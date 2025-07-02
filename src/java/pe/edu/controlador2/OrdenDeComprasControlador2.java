package pe.edu.controlador2;

import pe.edu.dao.OrdenDeComprasDAO;
import pe.edu.dao.TrabajadorDAO;
import pe.edu.dao.ProveedorDAO;
import pe.edu.modelo.OrdenDeCompras;
import pe.edu.modelo.Trabajador;
import pe.edu.modelo.Proveedor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/OrdenDeComprasControlador2")
public class OrdenDeComprasControlador2 extends HttpServlet {
    OrdenDeComprasDAO dao = new OrdenDeComprasDAO();
    TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
    ProveedorDAO proveedorDAO = new ProveedorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";

            switch (accion) {
                case "listar":
                    List<OrdenDeCompras> listaOrdenes = dao.listar();
                    List<Trabajador> listaTrabajadores = trabajadorDAO.listar();
                    List<Proveedor> listaProveedores = proveedorDAO.listar();

                    request.setAttribute("ordenes", listaOrdenes);
                    request.setAttribute("trabajadores", listaTrabajadores);
                    request.setAttribute("proveedores", listaProveedores);

                    request.getRequestDispatcher("Almacenero/ListarAlmacenero/ListarOrdenDeCompras.jsp").forward(request, response);
                    break;

                case "verDetalles":
                    int id = Integer.parseInt(request.getParameter("id"));
                    OrdenDeCompras orden = dao.obtenerPorId(id);
                    if (orden != null) {
                        request.setAttribute("orden", orden);
                        request.getRequestDispatcher("Almacenero/DetallesAlmacenero/VerDetallesOrdenDeCompras.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("OrdenDeComprasControlador2?accion=listar");
                    }
                    break;

                default:
                    response.sendRedirect("OrdenDeComprasControlador2?accion=listar");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        String accion = request.getParameter("accion");

        if ("cambiarEstado".equals(accion)) {
            int idOrdenCompra = Integer.parseInt(request.getParameter("id"));
            String nuevoEstado = request.getParameter("estado");

            OrdenDeCompras orden = dao.obtenerPorId(idOrdenCompra);
            if (orden != null) {
                orden.setEstado(nuevoEstado);
                dao.actualizar(orden);
            }

            // Redirigir a listar después de actualizar
            response.sendRedirect("OrdenDeComprasControlador2?accion=listar");

        } else {
            response.sendRedirect("OrdenDeComprasControlador2?accion=listar");
        }

    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("error.jsp");
    }
}
}

package pe.edu.controlador;

import pe.edu.dao.GuiasRemisionDAO;
import pe.edu.dao.TrabajadorDAO;
import pe.edu.dao.ProveedorDAO;
import pe.edu.dao.ProductoDAO;
import pe.edu.modelo.GuiasRemision;
import pe.edu.modelo.Trabajador;
import pe.edu.modelo.Proveedor;
import pe.edu.modelo.Producto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/GuiasRemisionControlador")
public class GuiasRemisionControlador extends HttpServlet {
    GuiasRemisionDAO dao = new GuiasRemisionDAO();
    TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
    ProveedorDAO proveedorDAO = new ProveedorDAO();
    ProductoDAO productoDAO = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";

            switch (accion) {
                case "listar":
                    List<GuiasRemision> listaGuias = dao.listar();
                    List<Trabajador> listaTrabajadores = trabajadorDAO.listar();
                    List<Proveedor> listaProveedores = proveedorDAO.listar();
                    List<Producto> listaProductos = productoDAO.listar();

                    request.setAttribute("guias", listaGuias);
                    request.setAttribute("trabajadores", listaTrabajadores);
                    request.setAttribute("proveedores", listaProveedores);
                    request.setAttribute("productos", listaProductos);

                    request.getRequestDispatcher("Admin/ListarAdmin/ListarGuiasRemision.jsp").forward(request, response);
                    break;

                case "nuevo":
                    request.setAttribute("trabajadores", trabajadorDAO.listar());
                    request.setAttribute("proveedores", proveedorDAO.listar());
                    request.setAttribute("productos", productoDAO.listar());
                    request.getRequestDispatcher("Admin/AgregarAdmin/AgregarGuiasRemision.jsp").forward(request, response);
                    break;

                case "editar":
                    int idEditar = Integer.parseInt(request.getParameter("id"));
                    GuiasRemision guiaEditar = dao.obtenerPorId(idEditar);

                    request.setAttribute("guia", guiaEditar);
                    request.setAttribute("trabajadores", trabajadorDAO.listar());
                    request.setAttribute("proveedores", proveedorDAO.listar());
                    request.setAttribute("productos", productoDAO.listar());

                    request.getRequestDispatcher("Admin/EditarAdmin/EditarGuiasRemision.jsp").forward(request, response);
                    break;

                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idEliminar);
                    response.sendRedirect("GuiasRemisionControlador?accion=listar");
                    break;

                default:
                    response.sendRedirect("GuiasRemisionControlador?accion=listar");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");

            if ("cambiarEstado".equals(accion)) {
                int idGuia = Integer.parseInt(request.getParameter("id"));
                String nuevoEstado = request.getParameter("estado");

                GuiasRemision guia = dao.obtenerPorId(idGuia);
                if (guia != null) {
                    guia.setEstado(nuevoEstado);
                    dao.actualizar(guia);
                }

                response.sendRedirect("GuiasRemisionControlador?accion=listar");
                return;
            }

            if ("agregar".equals(accion)) {
    Date fecha = new Date(); // fecha actual

    String razonSocial = request.getParameter("razonSocial");
    int idTrabajador = Integer.parseInt(request.getParameter("idTrabajador"));
    int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
    String estado = request.getParameter("estado");

    String[] productos = request.getParameterValues("productos[]");
    String[] cantidadesStr = request.getParameterValues("cantidades[]");

    if (productos != null && cantidadesStr != null && productos.length == cantidadesStr.length) {
        for (int i = 0; i < productos.length; i++) {
            String productoStr = productos[i];
            String cantidadStr = cantidadesStr[i];

            // Validar que no estén vacíos
            if (productoStr != null && !productoStr.trim().isEmpty() &&
                cantidadStr != null && !cantidadStr.trim().isEmpty()) {

                try {
                    int idProducto = Integer.parseInt(productoStr.trim());
                    int cantidad = Integer.parseInt(cantidadStr.trim());

                    GuiasRemision guia = new GuiasRemision(
                        0, razonSocial, fecha, cantidad, idTrabajador, idProveedor, idProducto, estado
                    );

                    dao.agregar(guia);

                } catch (NumberFormatException ex) {
                    // Podrías loguearlo o ignorar solo ese ítem
                    ex.printStackTrace(); // O usar logging real
                }
            }
        }
    }

    response.sendRedirect("GuiasRemisionControlador?accion=listar");
} else if ("actualizar".equals(accion)) {
                int idGuia = Integer.parseInt(request.getParameter("idGuia"));
                Date fecha = new Date();

                String razonSocial = request.getParameter("razonSocial");
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                int idTrabajador = Integer.parseInt(request.getParameter("idTrabajador"));
                int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
                int idProducto = Integer.parseInt(request.getParameter("idProducto"));
                String estado = request.getParameter("estado");

                GuiasRemision guiaActualizar = new GuiasRemision(idGuia, razonSocial, fecha, cantidad, idTrabajador, idProveedor, idProducto, estado);
                dao.actualizar(guiaActualizar);

                response.sendRedirect("GuiasRemisionControlador?accion=listar");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.controlador;

import pe.edu.dao.OrdenDeComprasDAO;
import pe.edu.modelo.OrdenDeCompras;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import pe.edu.dao.ProveedorDAO;
import pe.edu.dao.TrabajadorDAO;
import pe.edu.modelo.Proveedor;
import pe.edu.modelo.Trabajador;

@WebServlet("/OrdenDeComprasControlador")
public class OrdenDeComprasControlador extends HttpServlet {
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

        request.getRequestDispatcher("Admin/ListarAdmin/ListarOrdenDeCompras.jsp").forward(request, response);
        break;

    case "nuevo":
        request.setAttribute("trabajadores", trabajadorDAO.listar());
        request.setAttribute("proveedores", proveedorDAO.listar());
        request.getRequestDispatcher("Admin/AgregarAdmin/AgregarOrdenDeCompras.jsp").forward(request, response);
        break;

    case "editar":
        int idEditar = Integer.parseInt(request.getParameter("id"));
        OrdenDeCompras ordenEditar = dao.obtenerPorId(idEditar);

        request.setAttribute("orden", ordenEditar);
        request.setAttribute("trabajadores", trabajadorDAO.listar());
        request.setAttribute("proveedores", proveedorDAO.listar());

        request.getRequestDispatcher("Admin/EditarAdmin/EditarOrdenDeCompras.jsp").forward(request, response);
        break;

    case "eliminar":
        int idEliminar = Integer.parseInt(request.getParameter("id"));
        dao.eliminar(idEliminar);
        response.sendRedirect("OrdenDeComprasControlador?accion=listar");
        break;

    default:
        response.sendRedirect("OrdenDeComprasControlador?accion=listar");
        break;
}
        } catch (Exception e) {
            e.printStackTrace();
            // Podrías enviar a una página de error o mostrar un mensaje
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

            response.sendRedirect("OrdenDeComprasControlador?accion=listar");
            return;
        }

        // Caso agregar o actualizar
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        int idOrdenCompra = 0;
        Date fechaOrden = null;

        try {
            fechaOrden = formato.parse(request.getParameter("fechaOrden"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        double total = Double.parseDouble(request.getParameter("total"));
        int idTrabajador = Integer.parseInt(request.getParameter("idTrabajador"));
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        String estado = request.getParameter("estado");

        if ("agregar".equals(accion)) {
            OrdenDeCompras nuevaOrden = new OrdenDeCompras(0, fechaOrden, total, idTrabajador, idProveedor, estado);
            dao.agregar(nuevaOrden);
        } else if ("actualizar".equals(accion)) {
            idOrdenCompra = Integer.parseInt(request.getParameter("idOrdenCompra"));
            OrdenDeCompras ordenActualizar = new OrdenDeCompras(idOrdenCompra, fechaOrden, total, idTrabajador, idProveedor, estado);
            dao.actualizar(ordenActualizar);
        }

        response.sendRedirect("OrdenDeComprasControlador?accion=listar");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}

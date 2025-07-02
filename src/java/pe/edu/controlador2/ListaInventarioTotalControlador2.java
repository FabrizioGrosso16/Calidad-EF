/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.controlador2;

import pe.edu.dao.DetalleProductoDAO;
import pe.edu.dao.ListaInventarioTotalDAO;
import pe.edu.dao.ProductoDAO;
import pe.edu.dao.TrabajadorDAO;
import pe.edu.modelo.DetalleProducto;
import pe.edu.modelo.ListaInventarioTotal;
import pe.edu.modelo.Producto;
import pe.edu.modelo.Trabajador;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/ListaInventarioTotalControlador2")
public class ListaInventarioTotalControlador2 extends HttpServlet {

    ListaInventarioTotalDAO dao = new ListaInventarioTotalDAO();
    ProductoDAO productoDAO = new ProductoDAO();
    TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
    DetalleProductoDAO detalleDAO = new DetalleProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";

            switch (accion) {
                case "listar":
                    List<ListaInventarioTotal> lista = dao.listar();
                    List<Producto> productos = productoDAO.listar();
                    List<Trabajador> trabajadores = trabajadorDAO.listar();

                    request.setAttribute("listaInventario", lista);
                    request.setAttribute("productos", productos);
                    request.setAttribute("trabajadores", trabajadores);

                    // Vista para Almacenero
                    request.getRequestDispatcher("Almacenero/ListarAlmacenero/ListarListaInventarioTotal.jsp").forward(request, response);
                    break;

                case "nuevo":
                    request.setAttribute("productos", productoDAO.listar());
                    request.setAttribute("trabajadores", trabajadorDAO.listar());
                    request.getRequestDispatcher("Almacenero/AgregarAlmacenero/AgregarListaInventarioTotal.jsp").forward(request, response);
                    break;

                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    ListaInventarioTotal lit = dao.obtenerPorId(id);

                    request.setAttribute("lit", lit);
                    request.setAttribute("productos", productoDAO.listar());
                    request.setAttribute("trabajadores", trabajadorDAO.listar());

                    request.getRequestDispatcher("Almacenero/EditarAlmacenero/EditarListaInventarioTotal.jsp").forward(request, response);
                    break;

                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idEliminar);
                    response.sendRedirect("ListaInventarioTotalControlador2?accion=listar");
                    break;

                // No incluir 'historial' para Almacenero

                default:
                    response.sendRedirect("ListaInventarioTotalControlador2?accion=listar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Opcional: enviar a página de error o mensaje
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");

            if ("agregar".equals(accion)) {
                ListaInventarioTotal lit = new ListaInventarioTotal(
                    0,
                    Integer.parseInt(request.getParameter("cantidad")),
                    Double.parseDouble(request.getParameter("precio")),
                    request.getParameter("lote"),
                    Integer.parseInt(request.getParameter("idTrabajador")),
                    Integer.parseInt(request.getParameter("idProducto"))
                );
                dao.agregar(lit);

            } else if ("actualizar".equals(accion)) {
                ListaInventarioTotal lit = new ListaInventarioTotal(
                    Integer.parseInt(request.getParameter("idLIT")),
                    Integer.parseInt(request.getParameter("cantidad")),
                    Double.parseDouble(request.getParameter("precio")),
                    request.getParameter("lote"),
                    Integer.parseInt(request.getParameter("idTrabajador")),
                    Integer.parseInt(request.getParameter("idProducto"))
                );
                dao.actualizar(lit);

            } else if ("actualizarCantidad".equals(accion)) {
                int idLIT = Integer.parseInt(request.getParameter("idLIT"));
                int nuevaCantidad = Integer.parseInt(request.getParameter("nuevaCantidad"));

                // Obtener datos actuales del LIT
                ListaInventarioTotal litActual = dao.obtenerPorId(idLIT);
                int cantidadOriginal = litActual.getCantidad();
                int diferencia = nuevaCantidad - cantidadOriginal;

                // Actualizar cantidad
                dao.actualizarCantidad(idLIT, nuevaCantidad);

                // Solo crear DetalleProducto si hay diferencia
                if (diferencia != 0) {
                    HttpSession sesion = request.getSession();
                    Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");
                    int idTrabajador = trabajador.getIdTrabajador();

                    DetalleProducto detalle = new DetalleProducto(
                        0,
                        new Date(),
                        diferencia,
                        litActual.getIdProducto(),
                        idTrabajador
                    );
                    detalleDAO.agregar(detalle);
                }

                // Para Almacenero no mostramos historial, redirigimos a listar
                response.sendRedirect("ListaInventarioTotalControlador2?accion=listar");
                return;
            }

            response.sendRedirect("ListaInventarioTotalControlador2?accion=listar");

        } catch (Exception e) {
            e.printStackTrace();
            // Opcional: manejar error
        }
    }
}

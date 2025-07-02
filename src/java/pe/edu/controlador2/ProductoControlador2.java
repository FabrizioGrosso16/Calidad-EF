/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.controlador2;

import pe.edu.dao.ProductoDAO;
import pe.edu.dao.CategoriaProductoDAO;
import pe.edu.dao.MarcaProductoDAO;
import pe.edu.modelo.Producto;
import pe.edu.modelo.CategoriaProducto;
import pe.edu.modelo.MarcaProducto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProductoControlador2")
public class ProductoControlador2 extends HttpServlet {
    ProductoDAO dao = new ProductoDAO();
    CategoriaProductoDAO categoriaDAO = new CategoriaProductoDAO();
    MarcaProductoDAO marcaDAO = new MarcaProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";

            switch (accion) {
                case "listar":
                    List<CategoriaProducto> categoriasListar = categoriaDAO.listar();
                    List<MarcaProducto> marcasListar = marcaDAO.listar();
                    List<Producto> lista = dao.listar();
                    request.setAttribute("categorias", categoriasListar);
                    request.setAttribute("marcas", marcasListar);
                    request.setAttribute("productos", lista);
                    request.getRequestDispatcher("Almacenero/ListarAlmacenero/ListarProducto.jsp").forward(request, response);
                    break;

                case "nuevo":
                    List<CategoriaProducto> categorias = categoriaDAO.listar();
                    List<MarcaProducto> marcas = marcaDAO.listar();
                    request.setAttribute("categorias", categorias);
                    request.setAttribute("marcas", marcas);
                    request.getRequestDispatcher("Almacenero/AgregarAlmacenero/AgregarProducto.jsp").forward(request, response);
                    break;

                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Producto producto = dao.obtenerPorId(id);
                    List<CategoriaProducto> categoriasEditar = categoriaDAO.listar();
                    List<MarcaProducto> marcasEditar = marcaDAO.listar();
                    request.setAttribute("producto", producto);
                    request.setAttribute("categorias", categoriasEditar);
                    request.setAttribute("marcas", marcasEditar);
                    request.getRequestDispatcher("Almacenero/EditarAlmacenero/EditarProducto.jsp").forward(request, response);
                    break;

                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idEliminar);
                    response.sendRedirect("ProductoControlador2?accion=listar");
                    break;

                default:
                    response.sendRedirect("ProductoControlador2?accion=listar");
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

            Producto p = new Producto(
                0,
                request.getParameter("nombre"),
                request.getParameter("descripcion"),
                Double.parseDouble(request.getParameter("precio")),
                request.getParameter("estado"),
                Integer.parseInt(request.getParameter("idCategoriaProducto")),
                Integer.parseInt(request.getParameter("idMarcaProducto"))
            );

            if ("agregar".equals(accion)) {
                dao.agregar(p);
            } else if ("actualizar".equals(accion)) {
                p.setIdProducto(Integer.parseInt(request.getParameter("idProducto")));
                dao.actualizar(p);
            }

            response.sendRedirect("ProductoControlador2?accion=listar");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


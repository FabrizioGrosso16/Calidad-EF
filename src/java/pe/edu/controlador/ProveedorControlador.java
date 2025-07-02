/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.controlador;

/**
 *
 * @author Estudiante
 */
import pe.edu.dao.ProveedorDAO;
import pe.edu.modelo.Proveedor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import pe.edu.dao.CategoriaProveedorDAO;
import pe.edu.modelo.CategoriaProducto;
import pe.edu.modelo.CategoriaProveedor;

@WebServlet("/ProveedorControlador")
public class ProveedorControlador extends HttpServlet {
    ProveedorDAO dao = new ProveedorDAO();
    CategoriaProveedorDAO categoriaDAO = new CategoriaProveedorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";

            switch (accion) {
                case "listar":
                    List<CategoriaProveedor> categoriasListar = categoriaDAO.listar();
                    List<Proveedor> proveedores = dao.listar();

                    request.setAttribute("categorias", categoriasListar);
                    request.setAttribute("proveedores", proveedores);

                    request.getRequestDispatcher("Admin/ListarAdmin/ListarProveedor.jsp").forward(request, response);
                    break;
                case "nuevo":
                    List<CategoriaProveedor> categorias = categoriaDAO.listar();
                    request.setAttribute("categorias", categorias);
                    request.getRequestDispatcher("Admin/AgregarAdmin/AgregarProveedor.jsp").forward(request, response);
                    break;
                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Proveedor p = dao.obtenerPorId(id);

                    List<CategoriaProveedor> categoriasEditar = categoriaDAO.listar(); 
                    request.setAttribute("categorias", categoriasEditar);             
                    request.setAttribute("proveedor", p);
                    request.getRequestDispatcher("Admin/EditarAdmin/EditarProveedor.jsp").forward(request, response);
                    break;
                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idEliminar);
                    response.sendRedirect("ProveedorControlador?accion=listar");
                    break;
                default:
                    response.sendRedirect("ProveedorControlador?accion=listar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Aquí podrías redirigir a una página de error o mostrar mensaje amigable
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");

            Proveedor p = new Proveedor(
                0,
                request.getParameter("razonSocial"),
                request.getParameter("ruc"),
                request.getParameter("direccion"),
                request.getParameter("correo"),
                Integer.parseInt(request.getParameter("idCategoriaProveedor"))
            );

            if ("agregar".equals(accion)) {
                dao.agregar(p);
            } else if ("actualizar".equals(accion)) {
                p.setIdProveedor(Integer.parseInt(request.getParameter("idProveedor")));
                dao.actualizar(p);
            }
            response.sendRedirect("ProveedorControlador?accion=listar");
        } catch (Exception e) {
            e.printStackTrace();
            // Opcional: mostrar página de error o mensaje amigable
        }
    }
}


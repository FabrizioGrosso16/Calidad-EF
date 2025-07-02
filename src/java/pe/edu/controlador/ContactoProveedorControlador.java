/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.controlador;

import pe.edu.dao.ContactoProveedorDAO;
import pe.edu.modelo.ContactoProveedor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import pe.edu.dao.ProveedorDAO;
import pe.edu.modelo.Proveedor;


@WebServlet("/ContactoProveedorControlador")
public class ContactoProveedorControlador extends HttpServlet {
    ContactoProveedorDAO dao = new ContactoProveedorDAO();
    ProveedorDAO proveedorDAO = new ProveedorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) accion = "listar";

            switch (accion) {
                case "listar":
                    List<ContactoProveedor> lista = dao.listar();
                    List<Proveedor> proveedorListar = proveedorDAO.listar();
                    request.setAttribute("contactos", lista);
                    request.setAttribute("proveedores", proveedorListar);
                    request.getRequestDispatcher("Admin/ListarAdmin/ListarContactoProveedor.jsp").forward(request, response);
                    break;
                case "nuevo":
                    List<Proveedor> proveedores = proveedorDAO.listar();  
                    request.setAttribute("proveedores", proveedores);      
                    request.getRequestDispatcher("Admin/AgregarAdmin/AgregarContactoProveedor.jsp").forward(request, response);
                    break;
                case "editar":
                    List<Proveedor> proveedorese = proveedorDAO.listar();
                    request.setAttribute("proveedores", proveedorese); 
                    int id = Integer.parseInt(request.getParameter("id"));
                    ContactoProveedor contacto = dao.obtenerPorId(id);
                    request.setAttribute("contacto", contacto);
                    
                    
                    List<Proveedor> proveedoresEdicion = proveedorDAO.listar();
                    request.setAttribute("proveedores", proveedoresEdicion);
                    
                    request.getRequestDispatcher("Admin/EditarAdmin/EditarContactoProveedor.jsp").forward(request, response);
                    
                    
                    
                    break;
                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idEliminar);
                    response.sendRedirect("ContactoProveedorControlador?accion=listar");
                    break;
                default:
                    response.sendRedirect("ContactoProveedorControlador?accion=listar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Opcional: manejar errores con una página de error o mensaje amigable
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");

            ContactoProveedor contacto = new ContactoProveedor(
                0,
                request.getParameter("nombre"),
                request.getParameter("telefono"),
                request.getParameter("email"),
                Integer.parseInt(request.getParameter("idProveedor"))
            );

            if ("agregar".equals(accion)) {
                dao.agregar(contacto);
            } else if ("actualizar".equals(accion)) {
                contacto.setIdContacto(Integer.parseInt(request.getParameter("idContacto")));
                dao.actualizar(contacto);
            }

            response.sendRedirect("ContactoProveedorControlador?accion=listar");
        } catch (Exception e) {
            e.printStackTrace();
          
        }
    }
}


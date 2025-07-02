/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.edu.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener la sesión actual sin crear una nueva
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            session.invalidate(); // Invalida la sesión actual
        }

        // Redirigir al login con un mensaje opcional
        response.sendRedirect("index.jsp?mensaje=sesion_cerrada");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Mismo comportamiento para POST
    }

    @Override
    public String getServletInfo() {
        return "Servlet que cierra sesión y redirige al login";
    }
}


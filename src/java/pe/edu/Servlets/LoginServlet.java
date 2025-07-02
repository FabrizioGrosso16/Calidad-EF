/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.edu.Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;
import pe.edu.modelo.Trabajador;
import pe.edu.util.Conexion;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final int MAX_INTENTOS = 3;
    private static final int TIEMPO_BLOQUEO_MS = 10 * 1000; // 10 segundos

    // Reemplaza con tu clave secreta de Google reCAPTCHA
    private static final String SECRET_KEY = "6LdIZkkrAAAAAIIXe85tKJMzKIGW6Fw2mZ1Apeq-";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(">>> Entró al servlet LoginServlet");

        // 1. Validar reCAPTCHA
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        boolean captchaValido = false;

        try {
            captchaValido = verifyRecaptcha(gRecaptchaResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!captchaValido) {
            request.setAttribute("error", "Por favor, completa el reCAPTCHA correctamente.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // 2. Continuar con validación normal de login
        String correo = request.getParameter("correo");
        String clave = request.getParameter("pss");

        System.out.println("Correo ingresado: " + correo);
        System.out.println("Clave ingresada: " + clave);

        HttpSession session = request.getSession();

        // Obtener intentos y tiempo de bloqueo actuales
        Integer intentos = (Integer) session.getAttribute("intentos");
        Long tiempoBloqueo = (Long) session.getAttribute("tiempoBloqueo");

        if (intentos == null) intentos = 0;

        // Verificar si está bloqueado
        if (tiempoBloqueo != null) {
            long tiempoActual = System.currentTimeMillis();
            long diferencia = tiempoActual - tiempoBloqueo;
            if (diferencia < TIEMPO_BLOQUEO_MS) {
                long segundosRestantes = (TIEMPO_BLOQUEO_MS - diferencia) / 1000;
                System.out.println("Intento de login bloqueado. Usuario: " + correo + ". Tiempo restante de bloqueo: " + segundosRestantes + " segundos.");
                request.setAttribute("error", "Demasiados intentos fallidos. Intenta de nuevo en " + segundosRestantes + " segundos.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            } else {
                // Expiró bloqueo, resetear contador y tiempo
                session.removeAttribute("tiempoBloqueo");
                intentos = 0;
                session.setAttribute("intentos", intentos);
            }
        }

        Conexion conexion = new Conexion();
        Connection cn = null;

        try {
            cn = conexion.conecta();
            if (cn == null) {
                response.getWriter().println("Error: No se pudo conectar a la base de datos.");
                return;
            }

            String sql = "SELECT t.IdTrabajador, t.Nombre, t.Dni, t.Apellido, t.Telefono, t.Correo, t.IdRol, t.Clave, r.Nombre AS rol " +
                    "FROM Trabajador t " +
                    "JOIN Rol r ON t.IdRol = r.IdRol " +
                    "WHERE t.Correo = ? AND t.Clave = ?";

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, clave);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
              
                // Resetear intentos al iniciar sesión correctamente
                session.removeAttribute("intentos");
                session.removeAttribute("tiempoBloqueo");

                int idTrabajador = rs.getInt("IdTrabajador");
                String nombre = rs.getString("Nombre");
                String dni = rs.getString("Dni");
                String apellido = rs.getString("Apellido");
                String telefono = rs.getString("Telefono");
                String correoDB = rs.getString("Correo");
                int idRol = rs.getInt("IdRol");
                String claveDB = rs.getString("Clave");
                String rol = rs.getString("rol");

                Trabajador trabajadorAutenticado = new Trabajador(idTrabajador, nombre, dni, apellido, telefono, correoDB, idRol, claveDB);

                
                session.setAttribute("usuario", nombre);
                session.setAttribute("rol", rol);
                session.setAttribute("trabajador", trabajadorAutenticado);
                  // Login exitoso
                System.out.println("Usuario autenticado: " + correo);

                System.out.println("==== DATOS DEL USUARIO LOGUEADO ====");
                System.out.println("ID: " + rs.getInt("IdTrabajador"));
                System.out.println("Nombre: " + rs.getString("Nombre"));
                System.out.println("Apellido: " + rs.getString("Apellido"));
                System.out.println("DNI: " + rs.getString("Dni"));
                System.out.println("Teléfono: " + rs.getString("Telefono"));
                System.out.println("Correo: " + rs.getString("Correo"));
                System.out.println("Rol: " + rs.getString("rol"));
                System.out.println("=====================================");

                switch (rol.toLowerCase()) {
                    case "administrador":
                        response.sendRedirect("Admin/AdminIndex.jsp");
                        break;
                    case "jefe de almacen":
                        response.sendRedirect("Almacenero/AlmaceneroIndex.jsp");
                        break;
                    default:
                        response.sendRedirect("index.jsp?error=rol_desconocido");
                        break;
                }
            } else {
                // Login fallido
                intentos++;
                session.setAttribute("intentos", intentos);
                System.out.println("Intento de login fallido #" + intentos + " para el usuario: " + correo);

                if (intentos >= MAX_INTENTOS) {
                    long tiempoAhora = System.currentTimeMillis();
                    session.setAttribute("tiempoBloqueo", tiempoAhora);
                    System.out.println("Usuario " + correo + " bloqueado por 10 segundos debido a 3 intentos fallidos.");
                    request.setAttribute("error", "Demasiados intentos fallidos. Intenta de nuevo en 10 segundos.");
                } else {
                    request.setAttribute("error", "Credenciales inválidas. Intento " + intentos + " de 3.");
                }

                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error en LoginServlet: " + e.getMessage());
        } finally {
            try {
                if (cn != null) cn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }

    private boolean verifyRecaptcha(String gRecaptchaResponse) throws IOException {
        if (gRecaptchaResponse == null || gRecaptchaResponse.isEmpty()) {
            return false;
        }

        String url = "https://www.google.com/recaptcha/api/siteverify";
        String params = "secret=" + URLEncoder.encode(SECRET_KEY, "UTF-8") +
                "&response=" + URLEncoder.encode(gRecaptchaResponse, "UTF-8");

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
            out.writeBytes(params);
            out.flush();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder resp = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            resp.append(inputLine);
        }
        in.close();

        String jsonResponse = resp.toString();
        System.out.println("Respuesta reCAPTCHA: " + jsonResponse);

        return jsonResponse.contains("\"success\": true");
    }
}

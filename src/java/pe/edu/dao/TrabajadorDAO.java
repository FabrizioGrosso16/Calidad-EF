/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.dao;

import pe.edu.modelo.Trabajador;
import pe.edu.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrabajadorDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Listar todos los trabajadores
    public List<Trabajador> listar() throws ClassNotFoundException {
        List<Trabajador> lista = new ArrayList<>();
        String sql = "SELECT * FROM Trabajador";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Trabajador t = new Trabajador(
                    rs.getInt("IdTrabajador"),
                    rs.getString("Nombre"),
                    rs.getString("DNI"),
                    rs.getString("Apellido"),
                    rs.getString("Telefono"),
                    rs.getString("Correo"),
                    rs.getInt("IdRol"),
                    rs.getString("Clave")
                );
                lista.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    // Agregar un nuevo trabajador
    public void agregar(Trabajador t) throws ClassNotFoundException {
        String sql = "INSERT INTO Trabajador (Nombre, DNI, Apellido, Telefono, Correo, IdRol, Clave) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getDni());
            ps.setString(3, t.getApellido());
            ps.setString(4, t.getTelefono());
            ps.setString(5, t.getCorreo());
            ps.setInt(6, t.getIdRol());
            ps.setString(7, t.getClave());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Actualizar trabajador existente
    public void actualizar(Trabajador t) throws ClassNotFoundException {
        String sql = "UPDATE Trabajador SET Nombre = ?, DNI = ?, Apellido = ?, Telefono = ?, Correo = ?, IdRol = ?, Clave = ? WHERE IdTrabajador = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getDni());
            ps.setString(3, t.getApellido());
            ps.setString(4, t.getTelefono());
            ps.setString(5, t.getCorreo());
            ps.setInt(6, t.getIdRol());
            ps.setString(7, t.getClave());
            ps.setInt(8, t.getIdTrabajador());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Eliminar trabajador por ID
    public void eliminar(int id) throws ClassNotFoundException {
        String sql = "DELETE FROM Trabajador WHERE IdTrabajador = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Obtener trabajador por ID
    public Trabajador obtenerPorId(int id) throws ClassNotFoundException {
        Trabajador t = null;
        String sql = "SELECT * FROM Trabajador WHERE IdTrabajador = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                t = new Trabajador(
                    rs.getInt("IdTrabajador"),
                    rs.getString("Nombre"),
                    rs.getString("DNI"),
                    rs.getString("Apellido"),
                    rs.getString("Telefono"),
                    rs.getString("Correo"),
                    rs.getInt("IdRol"),
                    rs.getString("Clave")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return t;
    }

    // Cerrar recursos
    private void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.dao;

/**
 *
 * @author Estudiante
 */

import pe.edu.modelo.ContactoProveedor;
import pe.edu.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactoProveedorDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Listar todos los contactos de proveedores
    public List<ContactoProveedor> listar() throws ClassNotFoundException {
        List<ContactoProveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM ContactoProveedor";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ContactoProveedor contacto = new ContactoProveedor(
                    rs.getInt("idContacto"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getInt("idProveedor")
                );
                lista.add(contacto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    // Agregar un nuevo contacto de proveedor
    public void agregar(ContactoProveedor contacto) throws ClassNotFoundException {
        String sql = "INSERT INTO ContactoProveedor (nombre, telefono, email, idProveedor) VALUES (?, ?, ?, ?)";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, contacto.getNombre());
            ps.setString(2, contacto.getTelefono());
            ps.setString(3, contacto.getEmail());
            ps.setInt(4, contacto.getIdProveedor());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Actualizar un contacto de proveedor existente
    public void actualizar(ContactoProveedor contacto) throws ClassNotFoundException {
        String sql = "UPDATE ContactoProveedor SET nombre = ?, telefono = ?, email = ?, idProveedor = ? WHERE idContacto = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, contacto.getNombre());
            ps.setString(2, contacto.getTelefono());
            ps.setString(3, contacto.getEmail());
            ps.setInt(4, contacto.getIdProveedor());
            ps.setInt(5, contacto.getIdContacto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Eliminar un contacto de proveedor por ID
    public void eliminar(int idContacto) throws ClassNotFoundException {
        String sql = "DELETE FROM ContactoProveedor WHERE idContacto = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idContacto);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Obtener un contacto de proveedor por ID
    public ContactoProveedor obtenerPorId(int idContacto) throws ClassNotFoundException {
        ContactoProveedor contacto = null;
        String sql = "SELECT * FROM ContactoProveedor WHERE idContacto = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idContacto);
            rs = ps.executeQuery();

            if (rs.next()) {
                contacto = new ContactoProveedor(
                    rs.getInt("idContacto"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getInt("idProveedor")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return contacto;
    }

    // Método para cerrar recursos (ResultSet, PreparedStatement, Connection)
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

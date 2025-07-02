/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.dao;

/**
 *
 * @author Estudiante
 */

import pe.edu.modelo.MarcaProducto;
import pe.edu.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarcaProductoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Listar todas las marcas de productos
    public List<MarcaProducto> listar() throws ClassNotFoundException {
        List<MarcaProducto> lista = new ArrayList<>();
        String sql = "SELECT * FROM MarcaProducto";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                MarcaProducto m = new MarcaProducto(
                    rs.getInt("IdMarcaProducto"),
                    rs.getString("Nombre"),
                    rs.getString("Descripcion")
                );
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    // Agregar una nueva marca de producto
    public void agregar(MarcaProducto m) throws ClassNotFoundException {
        String sql = "INSERT INTO MarcaProducto (Nombre, Descripcion) VALUES (?, ?)";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getDescripcion());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Actualizar una marca de producto existente
    public void actualizar(MarcaProducto m) throws ClassNotFoundException {
        String sql = "UPDATE MarcaProducto SET Nombre = ?, Descripcion = ? WHERE IdMarcaProducto = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getDescripcion());
            ps.setInt(3, m.getIdMarcaProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Eliminar una marca de producto por ID
    public void eliminar(int id) throws ClassNotFoundException {
        String sql = "DELETE FROM MarcaProducto WHERE IdMarcaProducto = ?";

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

    // Obtener una marca de producto por ID
    public MarcaProducto obtenerPorId(int id) throws ClassNotFoundException {
        MarcaProducto m = null;
        String sql = "SELECT * FROM MarcaProducto WHERE IdMarcaProducto = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                m = new MarcaProducto(
                    rs.getInt("IdMarcaProducto"),
                    rs.getString("Nombre"),
                    rs.getString("Descripcion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return m;
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


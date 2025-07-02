/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.dao;

import pe.edu.modelo.GuiasRemision;
import pe.edu.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuiasRemisionDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Listar todas las guías de remisión
    public List<GuiasRemision> listar() throws ClassNotFoundException {
        List<GuiasRemision> lista = new ArrayList<>();
        String sql = "SELECT * FROM GuiasRemision";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                GuiasRemision guia = new GuiasRemision(
                    rs.getInt("idGuia"),
                    rs.getString("razonSocial"),
                    rs.getDate("fecha"),
                    rs.getInt("cantidad"),
                    rs.getInt("idTrabajador"),
                    rs.getInt("idProveedor"),
                    rs.getInt("idProducto"),
                    rs.getString("estado")
                );
                lista.add(guia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    // Agregar una nueva guía de remisión
    public void agregar(GuiasRemision guia) throws ClassNotFoundException {
        String sql = "INSERT INTO GuiasRemision (razonSocial, fecha, cantidad, idTrabajador, idProveedor, idProducto, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, guia.getRazonSocial());
            ps.setDate(2, new java.sql.Date(guia.getFecha().getTime()));
            ps.setInt(3, guia.getCantidad());
            ps.setInt(4, guia.getIdTrabajador());
            ps.setInt(5, guia.getIdProveedor());
            ps.setInt(6, guia.getIdProducto());
            ps.setString(7, guia.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Actualizar una guía de remisión existente
    public void actualizar(GuiasRemision guia) throws ClassNotFoundException {
        String sql = "UPDATE GuiasRemision SET razonSocial = ?, fecha = ?, cantidad = ?, idTrabajador = ?, idProveedor = ?, idProducto = ?, estado = ? WHERE idGuia = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, guia.getRazonSocial());
            ps.setDate(2, new java.sql.Date(guia.getFecha().getTime()));
            ps.setInt(3, guia.getCantidad());
            ps.setInt(4, guia.getIdTrabajador());
            ps.setInt(5, guia.getIdProveedor());
            ps.setInt(6, guia.getIdProducto());
            ps.setString(7, guia.getEstado());
            ps.setInt(8, guia.getIdGuia());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Eliminar una guía de remisión por ID
    public void eliminar(int idGuia) throws ClassNotFoundException {
        String sql = "DELETE FROM GuiasRemision WHERE idGuia = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idGuia);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Obtener una guía de remisión por su ID
    public GuiasRemision obtenerPorId(int idGuia) throws ClassNotFoundException {
        GuiasRemision guia = null;
        String sql = "SELECT * FROM GuiasRemision WHERE idGuia = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idGuia);
            rs = ps.executeQuery();

            if (rs.next()) {
                guia = new GuiasRemision(
                    rs.getInt("idGuia"),
                    rs.getString("razonSocial"),
                    rs.getDate("fecha"),
                    rs.getInt("cantidad"),
                    rs.getInt("idTrabajador"),
                    rs.getInt("idProveedor"),
                    rs.getInt("idProducto"),
                    rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return guia;
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

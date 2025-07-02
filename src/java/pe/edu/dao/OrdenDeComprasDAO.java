/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.dao;

import pe.edu.modelo.OrdenDeCompras;
import pe.edu.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdenDeComprasDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Listar todas las órdenes de compra
    public List<OrdenDeCompras> listar() throws ClassNotFoundException {
        List<OrdenDeCompras> lista = new ArrayList<>();
        String sql = "SELECT * FROM OrdenDeCompras";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                OrdenDeCompras o = new OrdenDeCompras(
                    rs.getInt("IdOrden_Compra"),
                    rs.getDate("FechaOrden"),
                    rs.getDouble("Total"),
                    rs.getInt("IdTrabajador"),
                    rs.getInt("IdProveedor"),
                    rs.getString("estado")      // <-- Aquí se agrega el estado
                );
                lista.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    // Agregar una nueva orden de compra
    public void agregar(OrdenDeCompras o) throws ClassNotFoundException {
        String sql = "INSERT INTO OrdenDeCompras (FechaOrden, Total, IdTrabajador, IdProveedor, Estado) VALUES (?, ?, ?, ?, ?)";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(o.getFechaOrden().getTime()));
            ps.setDouble(2, o.getTotal());
            ps.setInt(3, o.getIdTrabajador());
            ps.setInt(4, o.getIdProveedor());
            ps.setString(5, o.getEstado());  // <-- Insertar estado
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Actualizar una orden de compra existente
    public void actualizar(OrdenDeCompras o) throws ClassNotFoundException {
        String sql = "UPDATE OrdenDeCompras SET FechaOrden = ?, Total = ?, IdTrabajador = ?, IdProveedor = ?, estado = ? WHERE IdOrden_Compra = ?"; // CORREGIDO


        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(o.getFechaOrden().getTime()));
            ps.setDouble(2, o.getTotal());
            ps.setInt(3, o.getIdTrabajador());
            ps.setInt(4, o.getIdProveedor());
            ps.setString(5, o.getEstado());   // <-- Actualizar estado
            ps.setInt(6, o.getIdOrdenCompra());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Eliminar una orden de compra por ID
    public void eliminar(int id) throws ClassNotFoundException {
        String sql = "DELETE FROM OrdenDeCompras WHERE IdOrden_Compra = ?";

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

    // Obtener una orden de compra por ID
    public OrdenDeCompras obtenerPorId(int id) throws ClassNotFoundException {
        OrdenDeCompras o = null;
        String sql = "SELECT * FROM OrdenDeCompras WHERE IdOrden_Compra = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                o = new OrdenDeCompras(
                    rs.getInt("IdOrden_Compra"),
                    rs.getDate("FechaOrden"),
                    rs.getDouble("Total"),
                    rs.getInt("IdTrabajador"),
                    rs.getInt("IdProveedor"),
                    rs.getString("estado")       // <-- Obtener estado
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return o;
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

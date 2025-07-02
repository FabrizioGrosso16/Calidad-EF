/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.dao;

/**
 *
 * @author Estudiante
 */

import pe.edu.modelo.Factura;
import pe.edu.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Listar todas las facturas
    public List<Factura> listar() throws ClassNotFoundException {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM Factura";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Factura factura = new Factura(
                    rs.getInt("idFactura"),
                    rs.getDouble("precio"),
                    rs.getDate("fechaIngreso"),
                    rs.getInt("idProducto"),
                    rs.getInt("idTrabajador"),
                    rs.getInt("idProveedor")
                );
                lista.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    // Agregar una nueva factura
    public void agregar(Factura factura) throws ClassNotFoundException {
        String sql = "INSERT INTO Factura (precio, fechaIngreso, idProducto, idTrabajador, idProveedor) VALUES (?, ?, ?, ?, ?)";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, factura.getPrecio());
            ps.setDate(2, new java.sql.Date(factura.getFechaIngreso().getTime()));
            ps.setInt(3, factura.getIdProducto());
            ps.setInt(4, factura.getIdTrabajador());
            ps.setInt(5, factura.getIdProveedor());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Actualizar una factura existente
    public void actualizar(Factura factura) throws ClassNotFoundException {
        String sql = "UPDATE Factura SET precio = ?, fechaIngreso = ?, idProducto = ?, idTrabajador = ?, idProveedor = ? WHERE idFactura = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, factura.getPrecio());
            ps.setDate(2, new java.sql.Date(factura.getFechaIngreso().getTime()));
            ps.setInt(3, factura.getIdProducto());
            ps.setInt(4, factura.getIdTrabajador());
            ps.setInt(5, factura.getIdProveedor());
            ps.setInt(6, factura.getIdFactura());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Eliminar una factura por ID
    public void eliminar(int idFactura) throws ClassNotFoundException {
        String sql = "DELETE FROM Factura WHERE idFactura = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idFactura);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Obtener una factura por su ID
    public Factura obtenerPorId(int idFactura) throws ClassNotFoundException {
        Factura factura = null;
        String sql = "SELECT * FROM Factura WHERE idFactura = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idFactura);
            rs = ps.executeQuery();

            if (rs.next()) {
                factura = new Factura(
                    rs.getInt("idFactura"),
                    rs.getDouble("precio"),
                    rs.getDate("fechaIngreso"),
                    rs.getInt("idProducto"),
                    rs.getInt("idTrabajador"),
                    rs.getInt("idProveedor")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return factura;
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


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package pe.edu.dao;

import pe.edu.modelo.DetalleProducto;
import pe.edu.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleProductoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Listar todos los registros de DetalleProducto
   public List<DetalleProducto> listar() throws ClassNotFoundException {
    List<DetalleProducto> lista = new ArrayList<>();
    String sql = "SELECT * FROM DetalleProducto";

    try {
        con = cn.conecta();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            DetalleProducto d = new DetalleProducto(
                rs.getInt("idDetalle_Producto"),
                rs.getDate("fecha"),
                rs.getInt("cantidad"),
                rs.getInt("idProducto"),
                rs.getInt("idTrabajador")
            );
            lista.add(d);
        }
        System.out.println("DETALLES ENCONTRADOS: " + lista.size()); // <-- Agrega esto
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cerrarRecursos();
    }
    return lista;
}

    public void agregar(DetalleProducto detalle) throws ClassNotFoundException {
    String sql = "INSERT INTO DetalleProducto (fecha, cantidad, idProducto, idTrabajador) VALUES (?, ?, ?, ?)";

    try {
        con = cn.conecta();
        ps = con.prepareStatement(sql);
        ps.setDate(1, new java.sql.Date(detalle.getFecha().getTime()));
        ps.setInt(2, detalle.getCantidad());
        ps.setInt(3, detalle.getIdProducto());
        ps.setInt(4, detalle.getIdTrabajador());

        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cerrarRecursos();
    }
}
    // Cierre de recursos
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

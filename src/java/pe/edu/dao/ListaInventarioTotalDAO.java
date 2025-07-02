/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.dao;

/**
 *
 * @author Estudiante
 */

import pe.edu.modelo.ListaInventarioTotal;
import pe.edu.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListaInventarioTotalDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Listar todos los registros de inventario total
    public List<ListaInventarioTotal> listar() throws ClassNotFoundException {
        List<ListaInventarioTotal> lista = new ArrayList<>();
        String sql = "SELECT * FROM ListaInventarioTotal";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ListaInventarioTotal lit = new ListaInventarioTotal(
                    rs.getInt("idLIT"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio"),
                    rs.getString("lote"),
                    rs.getInt("idTrabajador"),
                    rs.getInt("idProducto")
                );
                lista.add(lit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    // Agregar un nuevo registro de inventario
    public void agregar(ListaInventarioTotal lit) throws ClassNotFoundException {
        String sql = "INSERT INTO ListaInventarioTotal (cantidad, precio, lote, idTrabajador, idProducto) VALUES (?, ?, ?, ?, ?)";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, lit.getCantidad());
            ps.setDouble(2, lit.getPrecio());
            ps.setString(3, lit.getLote());
            ps.setInt(4, lit.getIdTrabajador());
            ps.setInt(5, lit.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Actualizar un registro existente de inventario
    public void actualizar(ListaInventarioTotal lit) throws ClassNotFoundException {
        String sql = "UPDATE ListaInventarioTotal SET cantidad = ?, precio = ?, lote = ?, idTrabajador = ?, idProducto = ? WHERE idLIT = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, lit.getCantidad());
            ps.setDouble(2, lit.getPrecio());
            ps.setString(3, lit.getLote());
            ps.setInt(4, lit.getIdTrabajador());
            ps.setInt(5, lit.getIdProducto());
            ps.setInt(6, lit.getIdLIT());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Eliminar un registro por ID
    public void eliminar(int idLIT) throws ClassNotFoundException {
        String sql = "DELETE FROM ListaInventarioTotal WHERE idLIT = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idLIT);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Obtener un registro por ID
    public ListaInventarioTotal obtenerPorId(int idLIT) throws ClassNotFoundException {
        ListaInventarioTotal lit = null;
        String sql = "SELECT * FROM ListaInventarioTotal WHERE idLIT = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idLIT);
            rs = ps.executeQuery();

            if (rs.next()) {
                lit = new ListaInventarioTotal(
                    rs.getInt("idLIT"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio"),
                    rs.getString("lote"),
                    rs.getInt("idTrabajador"),
                    rs.getInt("idProducto")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return lit;
    }
    public void actualizarCantidad(int idLIT, int nuevaCantidad) throws ClassNotFoundException {
    String sql = "UPDATE ListaInventarioTotal SET cantidad = ? WHERE idLIT = ?";

    try {
        con = cn.conecta();
        ps = con.prepareStatement(sql);
        ps.setInt(1, nuevaCantidad);
        ps.setInt(2, idLIT);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cerrarRecursos();
    }
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


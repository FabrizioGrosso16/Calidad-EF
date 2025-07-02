/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.dao;

import pe.edu.modelo.Producto;
import pe.edu.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Listar todos los productos
    public List<Producto> listar() throws ClassNotFoundException {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM Producto";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto p = new Producto(
                    rs.getInt("IdProducto"),
                    rs.getString("Nombre"),
                    rs.getString("Descripcion"),
                    rs.getDouble("Precio"),
                    rs.getString("Estado"),
                    rs.getInt("IdCategoriaProducto"),
                    rs.getInt("IdMarcaProducto")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    // Agregar un nuevo producto
    public void agregar(Producto p) throws ClassNotFoundException {
        String sql = "INSERT INTO Producto (Nombre, Descripcion, Precio, Estado, IdCategoriaProducto, IdMarcaProducto) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setString(4, p.getEstado());
            ps.setInt(5, p.getIdCategoriaProducto());
            ps.setInt(6, p.getIdMarcaProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Actualizar producto existente
    public void actualizar(Producto p) throws ClassNotFoundException {
        String sql = "UPDATE Producto SET Nombre = ?, Descripcion = ?, Precio = ?, Estado = ?, IdCategoriaProducto = ?, IdMarcaProducto = ? WHERE IdProducto = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setString(4, p.getEstado());
            ps.setInt(5, p.getIdCategoriaProducto());
            ps.setInt(6, p.getIdMarcaProducto());
            ps.setInt(7, p.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Eliminar producto por ID
    public void eliminar(int id) throws ClassNotFoundException {
        String sql = "DELETE FROM Producto WHERE IdProducto = ?";

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

    // Obtener producto por ID
    public Producto obtenerPorId(int id) throws ClassNotFoundException {
        Producto p = null;
        String sql = "SELECT * FROM Producto WHERE IdProducto = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                p = new Producto(
                    rs.getInt("IdProducto"),
                    rs.getString("Nombre"),
                    rs.getString("Descripcion"),
                    rs.getDouble("Precio"),
                    rs.getString("Estado"),
                    rs.getInt("IdCategoriaProducto"),
                    rs.getInt("IdMarcaProducto")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return p;
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

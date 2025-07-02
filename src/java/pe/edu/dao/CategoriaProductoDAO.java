/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.dao;

import pe.edu.modelo.CategoriaProducto;
import pe.edu.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaProductoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Listar todas las categorías de producto
    public List<CategoriaProducto> listar() throws ClassNotFoundException {
        List<CategoriaProducto> lista = new ArrayList<>();
        String sql = "SELECT * FROM CategoriaProducto";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                CategoriaProducto categoria = new CategoriaProducto(
                    rs.getInt("idCategoriaProducto"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                );
                lista.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    // Agregar una nueva categoría de producto
    public void agregar(CategoriaProducto categoria) throws ClassNotFoundException {
        String sql = "INSERT INTO CategoriaProducto (nombre, descripcion) VALUES (?, ?)";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Actualizar una categoría de producto existente
    public void actualizar(CategoriaProducto categoria) throws ClassNotFoundException {
        String sql = "UPDATE CategoriaProducto SET nombre = ?, descripcion = ? WHERE idCategoriaProducto = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.setInt(3, categoria.getIdCategoriaProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Eliminar una categoría de producto por ID
    public void eliminar(int idCategoriaProducto) throws ClassNotFoundException {
        String sql = "DELETE FROM CategoriaProducto WHERE idCategoriaProducto = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCategoriaProducto);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Obtener una categoría de producto por ID
    public CategoriaProducto obtenerPorId(int idCategoriaProducto) throws ClassNotFoundException {
        CategoriaProducto categoria = null;
        String sql = "SELECT * FROM CategoriaProducto WHERE idCategoriaProducto = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCategoriaProducto);
            rs = ps.executeQuery();

            if (rs.next()) {
                categoria = new CategoriaProducto(
                    rs.getInt("idCategoriaProducto"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return categoria;
    }

    // Método para cerrar recursos
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


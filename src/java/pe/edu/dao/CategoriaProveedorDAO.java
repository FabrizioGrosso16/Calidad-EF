/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.dao;

/**
 *
 * @author Estudiante
 */


import pe.edu.modelo.CategoriaProveedor;
import pe.edu.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaProveedorDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Listar todas las categorías de proveedores
    public List<CategoriaProveedor> listar() throws ClassNotFoundException {
        List<CategoriaProveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM CategoriaProveedor";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                CategoriaProveedor categoria = new CategoriaProveedor(
                    rs.getInt("idCategoriaProveedor"),
                    rs.getString("nombreCategoria"),
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

    // Agregar una nueva categoría de proveedor
    public void agregar(CategoriaProveedor categoria) throws ClassNotFoundException {
        String sql = "INSERT INTO CategoriaProveedor (nombreCategoria, descripcion) VALUES (?, ?)";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNombreCategoria());
            ps.setString(2, categoria.getDescripcion());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Actualizar una categoría de proveedor existente
    public void actualizar(CategoriaProveedor categoria) throws ClassNotFoundException {
        String sql = "UPDATE CategoriaProveedor SET nombreCategoria = ?, descripcion = ? WHERE idCategoriaProveedor = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNombreCategoria());
            ps.setString(2, categoria.getDescripcion());
            ps.setInt(3, categoria.getIdCategoriaProveedor());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Eliminar una categoría de proveedor por ID
    public void eliminar(int idCategoriaProveedor) throws ClassNotFoundException {
        String sql = "DELETE FROM CategoriaProveedor WHERE idCategoriaProveedor = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCategoriaProveedor);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Obtener una categoría de proveedor por ID
    public CategoriaProveedor obtenerPorId(int idCategoriaProveedor) throws ClassNotFoundException {
        CategoriaProveedor categoria = null;
        String sql = "SELECT * FROM CategoriaProveedor WHERE idCategoriaProveedor = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCategoriaProveedor);
            rs = ps.executeQuery();

            if (rs.next()) {
                categoria = new CategoriaProveedor(
                    rs.getInt("idCategoriaProveedor"),
                    rs.getString("nombreCategoria"),
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


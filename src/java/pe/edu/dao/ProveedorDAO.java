/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.dao;

import pe.edu.modelo.Proveedor;
import pe.edu.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Listar todos los proveedores
    public List<Proveedor> listar() throws ClassNotFoundException {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Proveedor";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Proveedor p = new Proveedor(
                    rs.getInt("IdProveedor"),
                    rs.getString("RazonSocial"),
                    rs.getString("RUC"),
                    rs.getString("Direccion"),
                    rs.getString("Correo"),
                    rs.getInt("IdCategoriaProveedor")
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

    // Agregar un nuevo proveedor
    public void agregar(Proveedor p) throws ClassNotFoundException {
        String sql = "INSERT INTO Proveedor (RazonSocial, RUC, Direccion, Correo, IdCategoriaProveedor) VALUES (?, ?, ?, ?, ?)";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getRazonSocial());
            ps.setString(2, p.getRuc());
            ps.setString(3, p.getDireccion());
            ps.setString(4, p.getCorreo());
            ps.setInt(5, p.getIdCategoriaProveedor());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Actualizar proveedor
    public void actualizar(Proveedor p) throws ClassNotFoundException {
        String sql = "UPDATE Proveedor SET RazonSocial = ?, RUC = ?, Direccion = ?, Correo = ?, IdCategoriaProveedor = ? WHERE IdProveedor = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getRazonSocial());
            ps.setString(2, p.getRuc());
            ps.setString(3, p.getDireccion());
            ps.setString(4, p.getCorreo());
            ps.setInt(5, p.getIdCategoriaProveedor());
            ps.setInt(6, p.getIdProveedor());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    // Eliminar proveedor
    public void eliminar(int id) throws ClassNotFoundException {
        String sql = "DELETE FROM Proveedor WHERE IdProveedor = ?";

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

    // Obtener proveedor por ID
    public Proveedor obtenerPorId(int id) throws ClassNotFoundException {
        Proveedor p = null;
        String sql = "SELECT * FROM Proveedor WHERE IdProveedor = ?";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                p = new Proveedor(
                    rs.getInt("IdProveedor"),
                    rs.getString("RazonSocial"),
                    rs.getString("RUC"),
                    rs.getString("Direccion"),
                    rs.getString("Correo"),
                    rs.getInt("IdCategoriaProveedor")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return p;
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

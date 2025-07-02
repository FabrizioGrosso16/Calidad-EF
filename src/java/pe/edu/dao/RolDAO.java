/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.dao;

import pe.edu.modelo.Rol;
import pe.edu.modelo.Trabajador;
import pe.edu.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Listar todos los roles
    public List<Rol> listar() throws ClassNotFoundException {
        List<Rol> lista = new ArrayList<>();
        String sql = "SELECT * FROM Rol";

        try {
            con = cn.conecta();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Rol rol = new Rol(
                    rs.getInt("idRol"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                );
                lista.add(rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    // Buscar trabajadores vinculados a un rol específico
    // Buscar trabajadores vinculados a un rol específico
public List<Trabajador> buscarTrabajadoresPorRol(int idRol) throws ClassNotFoundException {
    List<Trabajador> lista = new ArrayList<>();
    String sql = "SELECT * FROM Trabajador WHERE idRol = ?";

    try {
        con = cn.conecta();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idRol);
        rs = ps.executeQuery();

        while (rs.next()) {
            Trabajador t = new Trabajador(
                rs.getInt("idTrabajador"),
                rs.getString("nombre"),
                rs.getString("dni"),
                rs.getString("apellido"),
                rs.getString("telefono"),
                rs.getString("correo"),
                rs.getInt("idRol"),
                rs.getString("clave")
            );
            lista.add(t);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cerrarRecursos();
    }

    return lista;
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

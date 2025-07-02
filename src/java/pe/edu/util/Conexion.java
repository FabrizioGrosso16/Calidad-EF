/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Estudiante
 */
public class Conexion {
    private Connection cnx;
    public Connection conecta() throws ClassNotFoundException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            String usr = "root";
            String psw = "admin";
            String url = "jdbc:mysql://localhost:3306/mitienda";
            cnx= DriverManager.getConnection(url, usr, psw);
            return cnx;
        }catch(SQLException e){
            
            System.out.println(e.getMessage());
    
        }
        return null;
    }
    public int pruebaConexion() throws ClassNotFoundException{
    Connection c = conecta();
    if(c!=null){return 1;}else{return 0;}
    }

}

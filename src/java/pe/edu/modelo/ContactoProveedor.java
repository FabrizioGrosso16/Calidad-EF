/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.modelo;

/**
 *
 * @author Estudiante
 */
public class ContactoProveedor {

    
    private int idContacto;
    private String nombre;
    private String telefono;
    private String email;
    private int idProveedor;
    
       
    public ContactoProveedor(int idContacto, String nombre, String telefono, String email, int idProveedor) {
        this.idContacto = idContacto;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.idProveedor = idProveedor;
    }

  
    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
   

}



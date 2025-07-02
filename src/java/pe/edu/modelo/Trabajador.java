/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.modelo;

/**
 *
 * @author Estudiante
 */
public class Trabajador {

    private int idTrabajador;
    private String nombre;
    private String dni;
    private String apellido;
    private String telefono;
    private String correo;
    private int idRol;
    private String clave;
    
    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

  

    public Trabajador(int idTrabajador, String nombre, String dni, String apellido, String telefono, String correo, int idRol, String clave) {
        this.idTrabajador = idTrabajador;
        this.nombre = nombre;
        this.dni = dni;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.idRol = idRol;
        this.clave = clave;
    }

}



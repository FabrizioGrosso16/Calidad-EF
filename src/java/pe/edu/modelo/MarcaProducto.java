/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.modelo;

/**
 *
 * @author Estudiante
 */
public class MarcaProducto {

    
    
    private int idMarcaProducto;
    private String nombre;
    private String descripcion;
    
    public int getIdMarcaProducto() {
        return idMarcaProducto;
    }

    public void setIdMarcaProducto(int idMarcaProducto) {
        this.idMarcaProducto = idMarcaProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
   

    public MarcaProducto(int idMarcaProducto, String nombre, String descripcion) {
        this.idMarcaProducto = idMarcaProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


}

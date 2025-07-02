/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.modelo;

/**
 *
 * @author Estudiante
 */
public class CategoriaProducto {

    
    private int idCategoriaProducto;
    private String nombre;
    private String descripcion;
    
    public int getIdCategoriaProducto() {
        return idCategoriaProducto;
    }

    public void setIdCategoriaProducto(int idCategoriaProducto) {
        this.idCategoriaProducto = idCategoriaProducto;
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
   

    public CategoriaProducto(int idCategoriaProducto, String nombre, String descripcion) {
        this.idCategoriaProducto = idCategoriaProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
      public CategoriaProducto() {
      
    }

  
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.modelo;

/**
 *
 * @author Estudiante
 */
public class CategoriaProveedor {
    private int idCategoriaProveedor;
    private String nombreCategoria;
    private String descripcion;

    public CategoriaProveedor(int idCategoriaProveedor, String nombreCategoria, String descripcion) {
        this.idCategoriaProveedor = idCategoriaProveedor;
        this.nombreCategoria = nombreCategoria;
        this.descripcion = descripcion;
    }

    public int getIdCategoriaProveedor() {
        return idCategoriaProveedor;
    }

    public void setIdCategoriaProveedor(int idCategoriaProveedor) {
        this.idCategoriaProveedor = idCategoriaProveedor;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public CategoriaProveedor() {
      
    }
}

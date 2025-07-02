/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.modelo;

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private String estado;
    private int idCategoriaProducto;
    private int idMarcaProducto;

    // Constructor vacío
    public Producto() {
    }

    // Constructor con todos los campos
    public Producto(int idProducto, String nombre, String descripcion, double precio, String estado, int idCategoriaProducto, int idMarcaProducto) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
        this.idCategoriaProducto = idCategoriaProducto;
        this.idMarcaProducto = idMarcaProducto;
    }

    // Getters y Setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdCategoriaProducto() {
        return idCategoriaProducto;
    }

    public void setIdCategoriaProducto(int idCategoriaProducto) {
        this.idCategoriaProducto = idCategoriaProducto;
    }

    public int getIdMarcaProducto() {
        return idMarcaProducto;
    }

    public void setIdMarcaProducto(int idMarcaProducto) {
        this.idMarcaProducto = idMarcaProducto;
    }
}

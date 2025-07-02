/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.modelo;

/**
 *
 * @author Estudiante
 */
public class ListaInventarioTotal {

    private int idLIT;
    private int cantidad;
    private double precio;
    private String lote;
    private int idTrabajador;
    private int idProducto;

    public ListaInventarioTotal() {
    // constructor vacío
}
    
    public int getIdLIT() {
        return idLIT;
    }

    public void setIdLIT(int idLIT) {
        this.idLIT = idLIT;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    public ListaInventarioTotal(int idLIT, int cantidad, double precio, String lote, int idTrabajador, int idProducto) {
        this.idLIT = idLIT;
        this.cantidad = cantidad;
        this.precio = precio;
        this.lote = lote;
        this.idTrabajador = idTrabajador;
        this.idProducto = idProducto;
    }

  
}

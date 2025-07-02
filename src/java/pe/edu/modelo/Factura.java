/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.modelo;
import java.util.Date;

public class Factura {
    private int idFactura;
    private double precio;
    private Date fechaIngreso;
    private int idProducto;
    private int idTrabajador;
    private int idProveedor;

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Factura(int idFactura, double precio, Date fechaIngreso, int idProducto, int idTrabajador, int idProveedor) {
        this.idFactura = idFactura;
        this.precio = precio;
        this.fechaIngreso = fechaIngreso;
        this.idProducto = idProducto;
        this.idTrabajador = idTrabajador;
        this.idProveedor = idProveedor;
    }

}


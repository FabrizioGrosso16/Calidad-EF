/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.modelo;
import java.util.Date;

public class DetalleProducto {

    private int idDetalleProducto;
    private Date fecha;
    private int cantidad;
    private int idProducto;
    private int idTrabajador;

   
    
    public int getIdDetalleProducto() {
        return idDetalleProducto;
    }

    public void setIdDetalleProducto(int idDetalleProducto) {
        this.idDetalleProducto = idDetalleProducto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
   

    public DetalleProducto(int idDetalleProducto, Date fecha, int cantidad, int idProducto, int idTrabajador) {
        this.idDetalleProducto = idDetalleProducto;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.idProducto = idProducto;
        this.idTrabajador = idTrabajador;
    }

}


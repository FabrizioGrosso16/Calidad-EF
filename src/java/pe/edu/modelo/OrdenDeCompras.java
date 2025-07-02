/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.modelo;

/**
 *
 * @author Estudiante
 */
import java.util.Date;

public class OrdenDeCompras {

    private int idOrdenCompra;
    private Date fechaOrden;
    private double total;
    private int idTrabajador;
    private int idProveedor;
    private String estado;
    
    
     public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public int getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(int idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
    

     public OrdenDeCompras(int idOrdenCompra, Date fechaOrden, double total, int idTrabajador, int idProveedor, String estado) {
        this.idOrdenCompra = idOrdenCompra;
        this.fechaOrden = fechaOrden;
        this.total = total;
        this.idTrabajador = idTrabajador;
        this.idProveedor = idProveedor;
        this.estado = estado;
    }
      public OrdenDeCompras() {
   
    }


}

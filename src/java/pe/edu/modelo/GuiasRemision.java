/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.modelo;
import java.util.Date;
/**
 *
 * @author Estudiante
 */
public class GuiasRemision {

    private int idGuia;
    private String razonSocial;
    private Date fecha;
    private int cantidad;
    private int idTrabajador;
    private int idProveedor;
    private int idProducto;
    private String estado;

    public GuiasRemision(int idGuia, String razonSocial, Date fecha, int cantidad, int idTrabajador, int idProveedor, int idProducto, String estado) {
        this.idGuia = idGuia;
        this.razonSocial = razonSocial;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.idTrabajador = idTrabajador;
        this.idProveedor = idProveedor;
        this.idProducto = idProducto;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    public int getIdGuia() {
        return idGuia;
    }

    public void setIdGuia(int idGuia) {
        this.idGuia = idGuia;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
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

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    

}

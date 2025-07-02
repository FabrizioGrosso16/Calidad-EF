/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.modelo;

/**
 *
 * @author Estudiante
 */
public class Proveedor {

    private int idProveedor;
    private String razonSocial;
    private String ruc;
    private String direccion;
    private String correo;
    private int idCategoriaProveedor;
    
    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdCategoriaProveedor() {
        return idCategoriaProveedor;
    }

    public void setIdCategoriaProveedor(int idCategoriaProveedor) {
        this.idCategoriaProveedor = idCategoriaProveedor;
    }

    public Proveedor(int idProveedor, String razonSocial, String ruc, String direccion, String correo, int idCategoriaProveedor) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.direccion = direccion;
        this.correo = correo;
        this.idCategoriaProveedor = idCategoriaProveedor;
    }
  
     public Proveedor() {
       
    }
 }

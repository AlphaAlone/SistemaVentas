/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaventas.controller.clases;


/**
 *
 * @author presi
 */
public class VentaConcepto {
    private int idProducto;
    private String producto;
    private double precioUnitario;
    private int cantidad;
    private double total;

    public VentaConcepto(int idProducto, String producto, double precioUnitario, int cantidad, double total) {
        this.idProducto = idProducto;
        this.producto = producto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.total = total;
    }
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
    
    
}

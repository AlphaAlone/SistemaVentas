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
public class Producto {
    private int id;
    private String nombre;
    private double precioUnitario;
    private double costo;
    private int inventario;

    public Producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double  getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double  precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double  getCosto() {
        return costo;
    }

    public void setCosto(double  costo) {
        this.costo = costo;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }
}

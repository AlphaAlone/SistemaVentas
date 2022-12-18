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
public class Venta {
    private int id;
    private String fechaVenta;
    private double total;
    private String folioUsuario;

    public Venta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFolioUsuario() {
        return folioUsuario;
    }

    public void setFolioUsuario(String folioUsuario) {
        this.folioUsuario = folioUsuario;
    }
}

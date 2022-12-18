/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaventas.controller;

import java.util.ArrayList;
import sistemaventas.controller.clases.Producto;
import sistemaventas.model.MdlProducto;

/**
 *
 * @author presi
 */
public class CtrlProducto extends Producto{
    private MdlProducto mdlProducto;

    public CtrlProducto() {
        this.mdlProducto = new MdlProducto();
    }
    
    public void setProducto(){
        String nombre = this.getNombre();
        double precioUnitario = this.getPrecioUnitario();
        double costo = this.getCosto();
        int inventario = this.getInventario();
        
        boolean requestProducto = this.mdlProducto.insertProducto(nombre, precioUnitario, costo, inventario);

        if (requestProducto) {
            System.out.println("Prodcuto agregado correctamente");
        } else {
            System.out.println("BUEN INTENTO c:<");
        }
    }
    
    public ArrayList getProductos() {
        ArrayList<String> producto = this.mdlProducto.selectProductos();
        return producto;
    }
    
    public ArrayList getProductosVenta() {
        ArrayList<String> producto = this.mdlProducto.selectProductosVenta();
        return producto;
    }

    public void getProducto() {
        int id = this.getId();
        System.out.println(this.mdlProducto.selectProducto(id));
    }
    
    public String getNombreProducto() {
        int id = this.getId();
        String producto = this.mdlProducto.selectNombreProducto(id);
        return producto;
    }
    
    public void setProductoUp() {
        int id = this.getId();
        String nombre = this.getNombre();
        double precioUnitario = this.getPrecioUnitario();
        double costo = this.getCosto();
        int inventario = this.getInventario();

        boolean requestProducto = this.mdlProducto.updateProducto(id, nombre, precioUnitario, costo, inventario);
        if (requestProducto) {
            System.out.println("Producto Actualizado correctamente");
        } else {
            System.out.println("BUEN INTENTO c:<");
        }
    }
    
    public void setInventarioUp() {
        int id = this.getId();
        int inventario = this.getInventario();

        boolean requestProducto = this.mdlProducto.updateInventarioProducto(id, inventario);
        if (requestProducto) {
            System.out.println("Inventario Actualizado correctamente");
        } else {
            System.out.println("BUEN INTENTO c:<");
        }
    }
    
    public void delProducto(){
        int id = this.getId();
        boolean requestDel = this.mdlProducto.deleteProducto(id);
        if (requestDel) {
            System.out.println("Producto Eliminado correctamente");
        } else {
            System.out.println("BUEN INTENTO c:<");
        }
    }
}

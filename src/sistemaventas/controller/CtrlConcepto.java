/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaventas.controller;

import java.util.ArrayList;
import sistemaventas.controller.clases.Concepto;
import sistemaventas.model.MdlConcepto;

/**
 *
 * @author presi
 */
public class CtrlConcepto extends Concepto{
     private MdlConcepto mdlConcepto;

    public CtrlConcepto() {
        this.mdlConcepto = new MdlConcepto();
    }
    
    public void setConcepto(){
        int id_venta = this.getId_venta();
        int cantidad = this.getCantidad();
        double precioUnitario = this.getPrecioUnitario();
        double importe = this.getImporte();
        int id_producto = this.getId_producto();
        
        boolean requestProducto = this.mdlConcepto.insertConcepto(id_venta, cantidad, precioUnitario, importe, id_producto);

        if (requestProducto) {
            System.out.println("Concepto agregado correctamente");
        } else {
            System.out.println("BUEN INTENTO c:<");
        }
    }
    
    public void getConceptos() {
        ArrayList<String> producto = this.mdlConcepto.selectConceptos();
        for (int i = 0; i < producto.size(); i++) {
            System.out.println(producto.get(i));
        }
    }
    
    public ArrayList getConceptosById() {
        int id_venta= this.getId_venta();
        ArrayList<String> producto = this.mdlConcepto.selectConceptosById(id_venta);
        return producto;
    }

    public void getConcepto() {
        int id = this.getId();
        System.out.println(this.mdlConcepto.selectConcepto(id));
    }
    
    public void setConceptoUp(){
        int id = this.getId();
        int id_venta = this.getId_venta();
        int cantidad = this.getCantidad();
        double precioUnitario = this.getPrecioUnitario();
        double importe = this.getImporte();
        int id_producto = this.getId_producto();
        
        boolean requestProducto = this.mdlConcepto.updateConcepto(id, id_venta, cantidad, precioUnitario, importe, id_producto);

        if (requestProducto) {
            System.out.println("Concepto Actualizado correctamente");
        } else {
            System.out.println("BUEN INTENTO c:<");
        }
    }
    
    public void delConcepto(){
        int id = this.getId();
        boolean requestDel = this.mdlConcepto.deleteConcepto(id);
        if (requestDel) {
            System.out.println("Concepto Eliminado correctamente");
        } else {
            System.out.println("BUEN INTENTO c:<");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaventas.controller;

import java.util.ArrayList;
import sistemaventas.controller.clases.Venta;
import sistemaventas.model.MdlVenta;

/**
 *
 * @author presi
 */
public class CtrlVenta extends Venta{
    private MdlVenta mdlVenta;

    public CtrlVenta() {
        this.mdlVenta = new MdlVenta();
    }
    
    public void setVenta(){
        String folioUsuario = this.getFolioUsuario();
        
        boolean requestVenta = this.mdlVenta.insertVenta(folioUsuario);

        if (requestVenta) {
            System.out.println("Venta Iniciada correctamente");
        } else {
            System.out.println("BUEN INTENTO c:<");
        }
    }
    
    public int setVentasSize(){
        ArrayList<String> ventas = this.mdlVenta.selectVentas();
        int ventasSize = ventas.size();
        return ventasSize;
    }
    
    public void getVentas() {
        ArrayList<String> ventas = this.mdlVenta.selectVentas();
        for (int i = 0; i < ventas.size(); i++) {
            System.out.println(ventas.get(i));
        }
    }

    public String getVenta() {
        int id = this.getId();
        return this.mdlVenta.selectVenta(id);
    }
    
    public String getLastVenta(){
        int id = this.getId();
        return this.mdlVenta.selectLastVenta();
    }
    
    public void setVentaUp() {
        int id = this.getId();
        double total = this.getTotal();

        boolean requestVenta = this.mdlVenta.updateVenta(id, total);
        if (requestVenta) {
            System.out.println("Venta Generada correctamente");
        } else {
            System.out.println("BUEN INTENTO c:<");
        }
    }
    
    public void delVenta(){
        int id = this.getId();
        boolean requestDel = this.mdlVenta.deleteVenta(id);
        if (requestDel) {
            System.out.println("Venta Cancelada correctamente");
        } else {
            System.out.println("BUEN INTENTO c:<");
        }
    }
}

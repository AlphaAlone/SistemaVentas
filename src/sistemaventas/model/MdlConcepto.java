/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaventas.model;

import java.util.ArrayList;
import sistemaventas.config.MySQL;

/**
 *
 * @author presi
 */
public class MdlConcepto extends MySQL{
    private int id;
    private int id_venta;
    private int cantidad;
    private double precioUnitario;
    private double importe;
    private int id_producto;
    
    public Boolean insertConcepto(int id_venta, int cantidad,  double precioUnitario, double importe, int id_producto){
        this.id_venta = id_venta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.id_producto = id_producto;
        
        String insertar = "INSERT INTO t_concepto(id_venta, cantidad, precioUnitario, importe, id_producto) VALUES (?, ?, ?, ?, ?)";
        String[] arrData = {""+this.id_venta, ""+this.cantidad, ""+this.precioUnitario, ""+this.importe, ""+this.id_producto};
        boolean requestInsert = this.insert(insertar, arrData);
        
        boolean result = requestInsert;
        
        return result;
    }
    
    public ArrayList selectConceptos(){
        String sql = "SELECT * FROM t_concepto";
        ArrayList<String> result = this.selectAll(sql);
        return result;
    }
    
    public ArrayList selectConceptosById(int id_venta){
        this.id_venta = id_venta;
        String sql = "SELECT * FROM t_concepto WHERE id_venta ='" + this.id_venta +"'";
        ArrayList<String> result = this.selectAll(sql);
        return result;
    }
    
    public String selectConcepto(int id_venta){
        this.id_venta = id_venta;
        String sql = "SELECT * FROM t_concepto WHERE id ='" + this.id_venta +"'";
        String request = this.select(sql);
        return request;
    }
    
    public Boolean updateConcepto(int id, int id_venta, int cantidad,  double precioUnitario, double importe, int id_producto){
        this.id = id;
        this.id_venta = id_venta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.id_producto = id_producto;
        
        String insertar = "UPDATE t_concepto SET id_venta = ?, cantidad = ?, precioUnitario = ?, importe = ?, id_producto = ? WHERE id = "+ this.id +"";
        String[] arrData = {""+this.id_venta, ""+this.cantidad, ""+this.precioUnitario, ""+this.importe, ""+this.id_producto};
        boolean requestUpdate = this.insert(insertar, arrData);
        
        boolean result = requestUpdate;
        
        return result;
    }
    
    public Boolean deleteConcepto(int id){
        this.id = id;
        String sql = "DELETE FROM t_concepto WHERE id = "+ this.id +"";
        boolean result = this.delete(sql);
        return result;
    }
}

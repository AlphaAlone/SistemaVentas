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
public class MdlProducto extends MySQL{
    private int id;
    private String nombre;
    private double precioUnitario;
    private double costo;
    private int inventario;
    
    public Boolean insertProducto(String nombre, double precioUnitario,  double costo, int inventario){
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.costo = costo;
        this.inventario = inventario;
        
        String insertar = "INSERT INTO t_producto(nombre, precioUnitario, costo, inventario) VALUES (?, ?, ?, ?)";
        String[] arrData = {this.nombre, ""+this.precioUnitario, ""+this.costo, ""+this.inventario};
        boolean requestInsert = this.insert(insertar, arrData);
        
        boolean result = requestInsert;
        
        return result;
    }
    
    public ArrayList selectProductos(){
        String sql = "SELECT * FROM t_producto";
        ArrayList<String> result = this.selectAll(sql);
        return result;
    }
    
    public ArrayList selectProductosVenta(){
        String sql = "SELECT id, nombre, costo, inventario FROM t_producto";
        ArrayList<String> result = this.selectAll(sql);
        return result;
    }
    
    public String selectProducto(int id){
        this.id = id;
        String sql = "SELECT * FROM t_producto WHERE Id ='" + this.id +"'";
        String request = this.select(sql);
        return request;
    }
    
    public String selectNombreProducto(int id){
        this.id = id;
        String sql = "SELECT nombre FROM t_producto WHERE Id ='" + this.id +"'";
        String request = this.select(sql);
        return request;
    }
    
    public Boolean updateProducto(int id, String nombre, double precioUnitario,  double costo, int inventario){
        boolean result;
        this.id = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.costo = costo;
        this.inventario = inventario;
        
        String update = "UPDATE t_producto SET nombre = ?, precioUnitario = ?, costo = ?, inventario = ? WHERE id = "+ this.id +"";
        String[] arrData = {this.nombre, ""+this.precioUnitario, ""+this.costo, ""+this.inventario};
        boolean requestUpdate = this.update(update, arrData);
        
        result = requestUpdate;
        
        return result;
    }
    
    public Boolean updateInventarioProducto(int id, int inventario){
        boolean result;
        this.id = id;
        this.inventario = inventario;
        
        String update = "UPDATE t_producto SET inventario = ? WHERE id = "+ this.id +"";
        String[] arrData = {""+this.inventario};
        boolean requestUpdate = this.update(update, arrData);
        
        result = requestUpdate;
        
        return result;
    }
    
    public Boolean deleteProducto(int id){
        this.id = id;
        String sql = "DELETE FROM t_producto WHERE id = "+ this.id +"";
        boolean result = this.delete(sql);
        return result;
    }
}

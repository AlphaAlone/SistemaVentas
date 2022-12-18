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
public class MdlVenta extends MySQL{
    private int id;
    private String fechaVenta;
    private double total;
    private String folioUsuario;
    
    public Boolean insertVenta(String folioUsuario){
        this.folioUsuario = folioUsuario;
        
        String insertar = "INSERT INTO t_ventas(fechaVenta, folioUsuario) VALUES (now(), ?)";
        String[] arrData = {this.folioUsuario};
        boolean requestInsert = this.insert(insertar, arrData);
        
        boolean result = requestInsert;
        
        return result;
    }
    
    public ArrayList selectVentas(){
        String sql = "SELECT * FROM t_ventas";
        ArrayList<String> result = this.selectAll(sql);
        return result;
    }
    
    public String selectLastVenta(){
        String sql = "SELECT * FROM t_ventas ORDER by ID DESC LIMIT 1";
        String request = this.select(sql);
        return request;
    }
    
    public String selectVenta(int id){
        this.id = id;
        String sql = "SELECT * FROM t_ventas WHERE Id ='" + this.id +"'";
        String request = this.select(sql);
        return request;
    }
    
    public Boolean updateVenta(int id, double total){
        boolean result;
        this.id = id;
        this.total = total;
        
        String update = "UPDATE t_ventas SET total = ? WHERE id = "+ this.id +"";
        String[] arrData = {""+this.total};
        boolean requestUpdate = this.update(update, arrData);
        
        result = requestUpdate;
        
        return result;
    }
    
    public Boolean deleteVenta(int id){
        this.id = id;
        String sql = "DELETE FROM t_ventas WHERE id = "+ this.id +"";
        boolean result = this.delete(sql);
        return result;
    }
}

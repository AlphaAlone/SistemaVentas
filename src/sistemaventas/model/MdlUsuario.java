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
public class MdlUsuario extends MySQL{
    private int id;
    private String nombre;
    private String ap_pat;
    private String ap_mat;
    private String curp;
    private String fechaNacimiento;
    private String tipoUsuario;
    private String folioUsuario;
    
    public Boolean insertarUsuario(String nombre, String ap_pat, String ap_mat, String curp, String fechaNacimiento, String tipoUsuario, String folioUsuario){
        boolean result;
        this.nombre = nombre;
        this.ap_pat = ap_pat;
        this.ap_mat = ap_mat;
        this.curp = curp;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoUsuario = tipoUsuario;
        this.folioUsuario = folioUsuario;
        
        String insertar = "INSERT INTO t_ususario(nombre, ap_pat, ap_mat, curp, fechaNacimiento, tipoUsuario, folioUsuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String[] arrData = {this.nombre, this.ap_pat, this.ap_mat, this.curp, this.fechaNacimiento, this.tipoUsuario, this.folioUsuario};
        boolean requestInsert = this.insert(insertar, arrData);
        
        result = requestInsert;
        
        return result;
    }
    
    public ArrayList selectUsuarios(){
        String sql = "SELECT * FROM t_ususario";
        ArrayList<String> result = this.selectAll(sql);
        return result;
    }
    
    public String selectUsuario(int id){
        this.id = id;
        String sql = "SELECT * FROM t_ususario WHERE Id ='" + this.id +"'";
        String request = this.select(sql);
        return request;
    }
    
    public Boolean updateUsuario(int id, String nombre, String ap_pat, String ap_mat, String curp, String fechaNacimiento, String tipoUsuario, String folioUsuario){
        boolean result;
        this.id = id;
        this.nombre = nombre;
        this.ap_pat = ap_pat;
        this.ap_mat = ap_mat;
        this.curp = curp;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoUsuario = tipoUsuario;
        this.folioUsuario = folioUsuario;
        
        String update = "UPDATE t_ususario SET nombre = ?, ap_pat = ?, ap_mat = ?, curp = ?, fechaNacimiento = ?, tipoUsuario = ?, folioUsuario = ? WHERE id = "+ this.id +"";
        String[] arrData = {this.nombre, this.ap_pat, this.ap_mat, this.curp, this.fechaNacimiento, this.tipoUsuario, this.folioUsuario};
        boolean requestInsert = this.update(update, arrData);
        
        result = requestInsert;
        
        return result;
    }
    
    public Boolean deleteUsuario(int id){
        this.id = id;
        String sql = "DELETE FROM t_ususario WHERE id = "+ this.id +"";
        boolean result = this.delete(sql);
        return true;
    }
    
}

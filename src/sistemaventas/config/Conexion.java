/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaventas.config;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author presi
 */
public class Conexion {
    Connection con = null;
    
    public Connection conexion(){
        
        try{
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sistemaventas", "root", "");
            System.out.println("Conexion Exitosa");
        }catch(Exception e){
           System.out.println("Error de Conexion: " + e.getMessage());
        }
        return con;
    }
}

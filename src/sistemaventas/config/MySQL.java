/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaventas.config;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author presi
 */
public class MySQL extends Conexion {

    private Connection cn;
    private String query;
    private String[] array;

    public MySQL() {
        Conexion con = new Conexion();
        cn = con.conexion();
    }

    public Boolean insert(String query, String[] array) {
        this.query = query;
        this.array = array;
        boolean result;
        try {
            PreparedStatement insertar = cn.prepareStatement(query);
            for (int i = 0; i < this.array.length; i++) {
                insertar.setString(i + 1, this.array[i]);
            }
            insertar.execute();
            return true;
        } catch (SQLException e) {
            System.out.print(e);
            return false;
        }

    }

    public ArrayList selectAll(String query) {
        this.query = query;
        ResultSet result = null;
        ResultSetMetaData rsmd = null;
        ArrayList<String> data = new ArrayList();
        String dataAux = "";
        try {
            PreparedStatement select = cn.prepareStatement(query);
            result = select.executeQuery();
            rsmd = result.getMetaData();
            while(result.next()){
                for(int i = 0; i<rsmd.getColumnCount(); i++){
                    dataAux = dataAux + "" + result.getObject(i+1) + "|";
                }
                data.add(dataAux);
                dataAux = "";
            }
            return data;
        } catch (SQLException e) {
            System.out.print(e);
            return data;
        }
    }
    
    public String select(String query){
        String data = "";
        ResultSet result = null;
        ResultSetMetaData rsmd = null;
        try {
            PreparedStatement select = cn.prepareStatement(query);
            result = select.executeQuery();
            rsmd = result.getMetaData();
            if(result.next()){
                for(int i = 0; i < rsmd.getColumnCount(); i++){
                    data = data + "" + result.getObject(i+1) + "|";
                }
            }
            return data;
        } catch (SQLException e) {
            System.out.print(e);
            return data;
        }
    }
    
    public Boolean update(String query, String[] array){
        this.query = query;
        this.array = array;
        try {
            PreparedStatement update = cn.prepareStatement(query);
            for (int i = 0; i < this.array.length; i++) {
                update.setString(i + 1, this.array[i]);
            }
            update.execute();
            return true;
        } catch (SQLException e) {
            System.out.print(e);
            return false;
        }
    }
    
    public Boolean delete(String query){
        this.query = query;
        try {
            PreparedStatement delete = cn.prepareStatement(query);
            delete.execute();
            return true;
        } catch (SQLException e) {
            System.out.print(e);
            return false;
        }
    }

}

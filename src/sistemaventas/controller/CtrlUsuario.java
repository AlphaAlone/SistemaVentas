/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaventas.controller;

import java.util.ArrayList;
import sistemaventas.controller.clases.Usuario;
import sistemaventas.model.MdlUsuario;

/**
 *
 * @author presi
 */
public class CtrlUsuario extends Usuario {

    private MdlUsuario mdlUsuario;

    public CtrlUsuario() {
        this.mdlUsuario = new MdlUsuario();
    }

    public void setUsuario() {
        String nombre = this.getNombre();
        String ap_pat = this.getAp_pat();
        String ap_mat = this.getAp_mat();
        String curp = this.getCurp();
        String fechaNacimiento = this.getFechaNacimiento();
        String tipoUsuario = this.getTipoUsuario();
        String folioUsuario = this.getFolioUsuario();

//        System.out.println(nombre +" - "+ ap_pat +" - "+ ap_mat +" - "+ curp +" - "+ fechaNacimiento +" - "+ tipoUsuario +" - "+ folioUsuario);
        boolean requestUsuario = this.mdlUsuario.insertarUsuario(nombre, ap_pat, ap_mat, curp, fechaNacimiento, tipoUsuario, folioUsuario);

        if (requestUsuario) {
            System.out.println("Usuario agregado correctamente");
        } else {
            System.out.println("BUEN INTENTO c:<");
        }
    }

    public void getUsuarios() {
        ArrayList<String> usuarios = this.mdlUsuario.selectUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println(usuarios.get(i));
        }
    }

    public void getUsuario() {
        int id = this.getId();
        System.out.println(this.mdlUsuario.selectUsuario(id));
    }

    public void setUsuarioUp() {
        int id = this.getId();
        String nombre = this.getNombre();
        String ap_pat = this.getAp_pat();
        String ap_mat = this.getAp_mat();
        String curp = this.getCurp();
        String fechaNacimiento = this.getFechaNacimiento();
        String tipoUsuario = this.getTipoUsuario();
        String folioUsuario = this.getFolioUsuario();

        boolean requestUsuario = this.mdlUsuario.updateUsuario(id, nombre, ap_pat, ap_mat, curp, fechaNacimiento, tipoUsuario, folioUsuario);
        if (requestUsuario) {
            System.out.println("Usuario Actualizado correctamente");
        } else {
            System.out.println("BUEN INTENTO c:<");
        }
    }
    
    public void delUsuario(){
        int id = this.getId();
        boolean requestDel = this.mdlUsuario.deleteUsuario(id);
        if (requestDel) {
            System.out.println("Usuario Eliminado correctamente");
        } else {
            System.out.println("BUEN INTENTO c:<");
        }
    }
    
}

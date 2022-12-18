/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaventas.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sistemaventas.controller.CtrlConcepto;
import sistemaventas.controller.CtrlProducto;
import sistemaventas.controller.CtrlVenta;
import sistemaventas.controller.clases.VentaConcepto;

/**
 * FXML Controller class
 *
 * @author presi
 */
public class FXMLVentasController implements Initializable {
    
    private CtrlVenta ctrlVenta;
    private CtrlConcepto ctrlConcepto;
    private CtrlProducto ctrlProducto;
            
    private int idVenta;
    private String fechaVenta;
    private double totalVenta;
    private String folioUsuario = "AAVA97030801";
    
    @FXML
    private Label lblVenta;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblTotalVenta;
    @FXML
    private Button btnAgregarP;
    @FXML
    private TableView<VentaConcepto> tablVenta;
    @FXML
    private TableColumn colIdProducto;
    @FXML
    private TableColumn colProducto;
    @FXML
    private TableColumn colPreUni;
    @FXML
    private TableColumn colCantidad;
    @FXML
    private TableColumn colTotal;
    
    private ObservableList<VentaConcepto> ventaConcepto;
    @FXML
    private Button btnGnrTPagar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ventaConcepto = FXCollections.observableArrayList();
        this.tablVenta.setItems(ventaConcepto);
        
        this.colIdProducto.setCellValueFactory(new PropertyValueFactory("idProducto")); 
        this.colProducto.setCellValueFactory(new PropertyValueFactory("producto")); 
        this.colPreUni.setCellValueFactory(new PropertyValueFactory("precioUnitario")); 
        this.colCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        
        ctrlProducto = new CtrlProducto();
        ctrlVenta = new CtrlVenta();
        ctrlConcepto = new CtrlConcepto();
        ctrlVenta.setFolioUsuario(folioUsuario);
        ctrlVenta.setVenta();
        String separador = Pattern.quote("|");
        String[] tventasParts = ctrlVenta.getLastVenta().split(separador);
        idVenta = Integer.parseInt(tventasParts[0]);
        fechaVenta = tventasParts[1];
        totalVenta = 0.00;
        
        lblVenta.setText("Venta #" + idVenta);
        lblFecha.setText("Fecha venta: " + fechaVenta);
        lblTotalVenta.setText("Total $" + totalVenta);
        lblUsuario.setText("Usuario: " + tventasParts[3]);
//        
//        btnGnrTPagar.setDisable(true);
    }    

    @FXML
    private void accionAgregarP(ActionEvent event) {
        try {
            FXMLLoader modalAddProducto = new FXMLLoader(getClass().getResource("FXMLModalAddProducto.fxml"));
            
            Parent root = modalAddProducto.load(); 
            FXMLModalAddProductoController modalAddProductoController = modalAddProducto.getController();
            modalAddProductoController.idVentaProducto(idVenta);
            modalAddProductoController.initAtributtes(ventaConcepto);
            
            Scene scenemodalAddProducto= new Scene(root);
            Stage stagemodalAddProducto = new Stage();
            stagemodalAddProducto.initModality(Modality.APPLICATION_MODAL);
            stagemodalAddProducto.setScene(scenemodalAddProducto);
            stagemodalAddProducto.showAndWait();
            
            VentaConcepto vc = modalAddProductoController.getVentaconcepto();
            if(vc != null){
                this.ventaConcepto.add(vc);
                this.tablVenta.refresh();
                tablVenta.getItems().forEach(ventaConcepto -> this.totalVenta += ventaConcepto.getTotal());
                lblTotalVenta.setText("Total $" + this.totalVenta);
                ctrlVenta.setTotal(totalVenta);
                ctrlVenta.setVentaUp();
//                btnGnrTPagar.setDisable(false);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLSistemaVentasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void accionTicket(ActionEvent event) {
        System.out.println(totalVenta);
        ctrlVenta.setId(idVenta);
        ctrlVenta.setTotal(totalVenta);
        ctrlVenta.setVentaUp();
        
        ctrlConcepto.setId_venta(idVenta);
        ArrayList<String> concepto = ctrlConcepto.getConceptosById();
        System.out.println(concepto);
       
        String separador = Pattern.quote("|");
        String contenido = "============================================================\n";
        contenido = contenido + "||                   Ticket de Venta                      ||\n";
        contenido = contenido + "============================================================\n";
        contenido = contenido + "Venta #" + idVenta + "\n" + "Fecha: " + fechaVenta + "\n" + " Usuario " + folioUsuario + "\n";
        contenido = contenido + "============================================================\n";
        contenido = contenido + "============================================================\n";
        contenido = contenido + "\n";
        contenido = contenido + "\n";
        contenido = contenido + "id Producto - Producto - Precio unitario - cantidad - Total \n";
        contenido = contenido + "\n";
        contenido = contenido + "\n";
        contenido = contenido + "============================================================\n";
        contenido = contenido + "============================================================\n";
        contenido = contenido + "\n";
        contenido = contenido + "\n";
        for (int i = 0; i < concepto.size(); i++) {
            String[] conceptoParts = concepto.get(i).split(separador);
            ctrlProducto.setId(Integer.parseInt(conceptoParts[5]));
            contenido = contenido + "   " + conceptoParts[5] + "    " + ctrlProducto.getNombreProducto() + "    " + conceptoParts[3] + "    " + conceptoParts[2] + "    " + conceptoParts[4] + "\n";
            contenido = contenido + "\n";
        }
        contenido = contenido + "\n";
        contenido = contenido + "============================================================\n";
        contenido = contenido + "============================================================\n";
        contenido = contenido + "Total $" + totalVenta;
        
        
        File archivo = new File("TicketVenta"+ idVenta +".txt");
        try{
            PrintWriter salida = new PrintWriter(archivo);
            salida.println(contenido);
            salida.close();
            System.out.println("Se creo el archivo");
        }catch(FileNotFoundException ex){
            ex.printStackTrace(System.out);
        }
        
        
    }
    
}
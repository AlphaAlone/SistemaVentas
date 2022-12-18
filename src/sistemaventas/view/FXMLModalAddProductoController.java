/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaventas.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sistemaventas.controller.CtrlConcepto;
import sistemaventas.controller.CtrlProducto;
import sistemaventas.controller.clases.VentaConcepto;

/**
 * FXML Controller class
 *
 * @author presi
 */
public class FXMLModalAddProductoController implements Initializable {
    
    private CtrlProducto ctrlProducto;
    private ArrayList<String> productos;
    private String separador = Pattern.quote("|");
    public int fxidVenta;
    private int id;
    private String nombre;
    private double costo;
    private int inventario;
    @FXML
    private Label lblVenta;
    @FXML
    private ChoiceBox<String> cboxProducto;
    @FXML
    private Label lblPrecio;
    @FXML
    private Label lblCantidad;
    @FXML
    private Text lblActualmente;
    @FXML
    private Button btnAgregarVenta;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtCantidad;
    
    private VentaConcepto ventaconcepto;
    
    private ObservableList<VentaConcepto> ventasConceptos;
    
    public void idVentaProducto(int idVenta){
        this.fxidVenta = idVenta;
    }
    
    public int getIdVentaProducto(){
        return this.fxidVenta;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ctrlProducto = new CtrlProducto();
        productos = ctrlProducto.getProductosVenta();
        String[] cbox = new String[productos.size()];
        
        
        for(int i = 0; i<productos.size(); i++){
            String[] productosParts = productos.get(i).split(separador);
            cbox[i] = productosParts[0] + "- " + productosParts[1];
            
        }
        txtCantidad.setText("0");
        txtCantidad.setDisable(true);
        cboxProducto.getItems().addAll(cbox);
        cboxProducto.setOnAction(this::getCboxProducto); 
    }
    
    public void initAtributtes(ObservableList<VentaConcepto> ventasConceptos){
        this.ventasConceptos = ventasConceptos;
        
    }

    public void getCboxProducto(ActionEvent event){
        String producSelect = cboxProducto.getValue();
        String separadorG = Pattern.quote("-");
        String[] idProductSlectParts = producSelect.split(separadorG);
        String idProductSelect = idProductSlectParts[0];
        for(int i = 0; i < productos.size(); i++){
            String[] productosParts = productos.get(i).split(separador);
            if(idProductSelect.equals(productosParts[0])){
                id = Integer.parseInt(productosParts[0]);
                nombre = productosParts[1];
                costo = Double.parseDouble(productosParts[2]);
                lblPrecio.setText("Precio $"+productosParts[2]);
                inventario = Integer.parseInt(productosParts[3]);
                lblActualmente.setText("Actualmente en inventario: "+productosParts[3]+"pzas");
                txtCantidad.setDisable(false);
            }
        }
    }

    @FXML
    private void accionAgregarVenta(ActionEvent event) {
        CtrlConcepto ctrlconcepto = new CtrlConcepto();
        ctrlconcepto.setId_venta(this.getIdVentaProducto());
        ctrlconcepto.setCantidad(Integer.parseInt(txtCantidad.getText()));
        ctrlconcepto.setPrecioUnitario(this.costo);
        ctrlconcepto.setImporte(this.costo * Integer.parseInt(txtCantidad.getText()));
        ctrlconcepto.setId_producto(this.id);
        ctrlconcepto.setConcepto();
        
        ctrlProducto.setId(id);
        ctrlProducto.setInventario(inventario - Integer.parseInt(txtCantidad.getText()));
        ctrlProducto.setInventarioUp();
        
        int productoId = this.id;
        String productoNombre = this.nombre;
        double productoPU = this.costo;
        int produtoCantidad = Integer.parseInt(txtCantidad.getText());
        double productoImporte = produtoCantidad * productoPU;
        
        VentaConcepto vc = new VentaConcepto(productoId, productoNombre, productoPU, produtoCantidad, productoImporte);
        if(!ventasConceptos.contains(vc)){
            this.ventaconcepto = vc;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Exito");
            alert.setContentText("Se ha aadido correctamente");
            alert.showAndWait();
            
            Stage stage = (Stage) this.btnAgregarVenta.getScene().getWindow();
            stage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Errror");
            alert.setContentText("Informacion duplicada");
            alert.showAndWait();
        }
    }

    @FXML
    private void accionCancelar(ActionEvent event) {
        this.ventaconcepto = null;
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void keyReleCantidad(KeyEvent event) {
        int calcInventario = inventario;
        int cantidad = Integer.parseInt(txtCantidad.getText());
        calcInventario = calcInventario - cantidad;
        if(calcInventario > 1){
            lblActualmente.setText("Actualmente en inventario: "+ calcInventario);
        }else{
            lblActualmente.setText("Sobrepasa el Stock actual.");
            txtCantidad.setText("0");
        }
        System.out.println(txtCantidad.getText());
    }

    public VentaConcepto getVentaconcepto() {
        return ventaconcepto;
    }
    
}


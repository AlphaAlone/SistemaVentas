/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaventas.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author presi
 */
public class FXMLSistemaVentasController implements Initializable {
    
    @FXML
    private Button btnVender;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void accionVender(ActionEvent event) {
        try {
            FXMLLoader ventanaVenta = new FXMLLoader(getClass().getResource("FXMLVentas.fxml"));
            
            Parent root = ventanaVenta.load();
            FXMLVentasController ventaController = ventanaVenta.getController();
            
            Scene sceneVenta = new Scene(root);
            Stage stageVenta = new Stage();
            stageVenta.initModality(Modality.APPLICATION_MODAL);
            stageVenta.setScene(sceneVenta);
            stageVenta.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(FXMLSistemaVentasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}

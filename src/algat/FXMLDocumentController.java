/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algat;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Simone
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    public void iniziamo(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("lezioni.fxml"));
        Scene newScene = new Scene(parent);

        //prendiamo le informazioni di Stage
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        window.setScene(newScene);
        window.setTitle("AlgaT - Lista delle lezioni");
        window.show();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

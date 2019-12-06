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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Francesco
 */
public class WeightAdderController implements Initializable {

    int input;

    @FXML
    private TextField weightInput;

    @FXML
    private Label errore;

    @FXML
    void setWeight(ActionEvent event) {
        try {
            input = Integer.parseInt(weightInput.getText());
            if(input < 0) {
                errore.setText("Non possono essere inseriti costi negativi.");
                weightInput.setText("");
            } else {
                AlgoritmoController.setCost(input);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.close();
            }    
        } catch (Exception e) {
            errore.setText("Errore! Inserisci un intero.");
            weightInput.setText("");
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

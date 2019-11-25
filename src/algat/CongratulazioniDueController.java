/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Simone
 */
public class CongratulazioniDueController implements Initializable {
    
    @FXML
    private ImageView fotosx;
    
    @FXML
    private ImageView fotodx;
    
    public void backToLezioni(ActionEvent event) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("lessonsList.fxml"));
        Scene newScene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("AlgaT - Lista delle lezioni");
        window.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fotosx.setImage(new Image("file:img/sparaCoriandoli.png"));
        fotodx.setImage(new Image("file:img/sparaCoriandoli2.png"));
    }    
    
}

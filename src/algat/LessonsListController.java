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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Francesco
 */ 
public class LessonsListController implements Initializable {
    
    @FXML
    private ImageView graduationCap;

    @FXML
    private Button btnLezioneUno;

    @FXML
    private Button btnLezioneUno1;

    //metodo che cambia la scene quando il bottone viene premuto
    public void apriLezioneUno(ActionEvent event) throws IOException {
        Parent lezioneUNO = FXMLLoader.load(getClass().getResource("lezioneUno.fxml"));
        Scene newScene = new Scene(lezioneUNO);

        //prendiamo le informazioni di Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("AlgaT - Lezione Uno - Grafi");
        window.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image gb = new Image("file:img/12382-graduation-cap.png");
        graduationCap.setImage(gb);
    }

}
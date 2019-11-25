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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Simone
 */
public class LezioneDueController implements Initializable {
    
    public void backToLessonsList(ActionEvent event) throws IOException {
        Parent lezioneDue = FXMLLoader.load(getClass().getResource("lessonsList.fxml"));
        Scene newScene = new Scene(lezioneDue);

        //prendiamo le informazioni di Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("AlgaT - Lista delle lezioni");
        window.show();
    }
    
    //metodo che cambia la scene quando il bottone viene premuto
    public void nextToQuiz(ActionEvent event) throws IOException {
        Parent lezioneUNO = FXMLLoader.load(getClass().getResource("quizUnoLezioneDue.fxml"));
        Scene newScene = new Scene(lezioneUNO);

        //prendiamo le informazioni di Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("AlgaT - Quiz Cammini Minimi");
        window.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Francesco
 */
public class QuizDueLezioneUnoController implements Initializable {

    @FXML
    private ToggleGroup quiz;

    @FXML
    private Label msg;

    @FXML
    private ImageView img;

    @FXML
    private Button btnNextUno;

    @FXML
    private RadioButton opz1;

    @FXML
    private RadioButton opz2;

    @FXML
    private Button btnConfirm;

    //metodo che cambia la scene quando il bottone viene premuto
    public void backToDue(ActionEvent event) throws IOException {
        Parent lezioneUNO = FXMLLoader.load(getClass().getResource("QuizUnoLezioneUno.fxml"));
        Scene newScene = new Scene(lezioneUNO);

        //prendiamo le informazioni di Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("AlgaT - Quiz Grafi");
        window.show();
    }

    //metodo che cambia la scene quando il bottone viene premuto
    public void NextToDue(ActionEvent event) throws IOException {
        Parent lezioneUNO = FXMLLoader.load(getClass().getResource("QuizUnoLezioneUno.fxml"));
        Scene newScene = new Scene(lezioneUNO);

        //prendiamo le informazioni di Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("AlgaT - Quiz Grafi");
        window.show();
    }

    @FXML
    void radioSelect(ActionEvent event) {
        RadioButton radio = (RadioButton) quiz.getSelectedToggle();
        System.out.println(radio.getText() + " ID: " + radio.getId());
        String messaggio = "";
        String selected = "";
        selected = radio.getId();
        if (selected.equals("opz1")) {
            messaggio += "Risposta corretta !";
            btnNextUno.setDisable(false);
        } else if (selected.equals("opz2")) {
            messaggio += "Risposta errata !";
        } 
        msg.setText(messaggio);
    }

    @FXML
    void radioHasChanged(ActionEvent event) {
        btnConfirm.setDisable(false);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("file:img/grafoquiz.png");
        img.setImage(image);
    }

}

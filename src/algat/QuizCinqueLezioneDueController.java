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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Simone
 */
public class QuizCinqueLezioneDueController implements Initializable {
    
    @FXML
    private ToggleGroup quiz;
    
    @FXML
    private ToggleGroup quiz2;

    @FXML
    private Label msg;
    
    @FXML
    private Label p;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnConfirm;

    @FXML
    private RadioButton opz1;

    @FXML
    private RadioButton opz2;

    @FXML
    private RadioButton opz3;

    @FXML
    private RadioButton opz4;

    //metodo che cambia la scene quando il bottone viene premuto
    public void previousOne(ActionEvent event) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("quizQuattroLezioneDue.fxml"));
        Scene newScene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("AlgaT - Quiz Cammini Minimi");
        window.show();
    }

        //metodo che cambia la scene quando il bottone viene premuto
    public void nextOne(ActionEvent event) throws IOException {
        Parent nextQuiz = FXMLLoader.load(getClass().getResource("congratulazioniDue.fxml"));
        Scene newScene = new Scene(nextQuiz);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("AlgaT - Cammini Minimi");
        window.show();
    }
    
    @FXML
    void radioSelect(ActionEvent event) {
        RadioButton radio = (RadioButton) quiz.getSelectedToggle();
        RadioButton r = (RadioButton) quiz2.getSelectedToggle();
        System.out.println(radio.getText() + " ID: " + radio.getId());
        String messaggio = "";
        String selected1 = radio.getId();
        if(!p.isVisible()) {
            if (selected1.equals("opz1")) {
                messaggio = "Risposta corretta !";
                msg.setTextFill(Color.web("#008000"));
                opz3.setDisable(false);
                opz4.setDisable(false);
                opz3.setVisible(true);
                opz4.setVisible(true);
                p.setVisible(true);
            } else if (selected1.equals("opz2")) {
                messaggio = "Risposta errata !";
                msg.setTextFill(Color.web("#FF0000"));
            }
        } else {
            String selected2 = r.getId();
            if (selected2.equals("opz3")) {
                messaggio = "Risposta corretta !";
                msg.setTextFill(Color.web("#008000"));
                btnNext.setDisable(false);
            } else if (selected2.equals("opz4")) {
                messaggio = "Risposta errata !";
                msg.setTextFill(Color.web("#FF0000"));
            } 
        }    
        msg.setText(messaggio);
    }

    @FXML
    void radioHasChanged(ActionEvent event) {
        btnConfirm.setDisable(false);
        msg.setText("");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

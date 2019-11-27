/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author francesco
 */
public class QuizController implements Initializable {

    @FXML
    private Label title;

    @FXML
    private Label console;

    @FXML
    private Label question;

    @FXML
    private VBox radioBox;

    @FXML
    private ImageView image;

    @FXML
    private Button avanti;

    @FXML
    private Button indietro;

    @FXML
    private Button conferma;

    @FXML
    void avanti(ActionEvent event) throws FileNotFoundException {
        growQuiz();
        if (quizPos < quizLength) {
            loadComponents();
        } else {
            //le domande sono terminate
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Quiz completato");
            alert.setHeaderText(null);
            alert.setContentText("Ce l'hai fatta, grande! Spero ti sia stato utile per comprendere meglio questi concetti ;)");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource("lezioni.fxml"));
                        Scene newScene = new Scene(parent);
                        
                        //prendiamo le informazioni di Stage
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(newScene);
                        window.setTitle("AlgaT - Lista delle lezioni");
                        window.show();
                    } catch (IOException ex) {
                        Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }

    @FXML
    void conferma(ActionEvent event) {
        RadioButton selected = (RadioButton) rgroup.getSelectedToggle();
        if (selected != null) {
            if (selected.getId().equals(correct)) {
                avanti.setDisable(false);
                rgroup.getToggles().forEach(toggle -> {
                    Node node = (Node) toggle;
                    node.setDisable(true);
                });
                selected.setDisable(false);
                console.setText("Perfetto! Risposta corretta");
                console.setTextFill(Color.web("#38afff"));
            } else {
                console.setText("Errore! Risposta sbagliata, riprova");
                console.setTextFill(Color.web("#ff4530"));
            }
        } else {
            console.setText("Attento! Nessuna risposta selezionata");
            console.setTextFill(Color.web("#ff4530"));
        }
    }

    @FXML
    void indietro(ActionEvent event) throws IOException {
        FXMLLoader lezioneLoader = new FXMLLoader(getClass().getResource("lezione.fxml"));
        Scene newScene = new Scene(lezioneLoader.load());

        LezioneController controller = lezioneLoader.getController();
        controller.setCurrentLesson(currentLesson);
        //prendiamo le informazioni di Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("AlgaT - " + currentLesson);
        window.show();
    }

    private String currentLesson;
    private ToggleGroup rgroup = null;
    private int quizPos = 0;
    private int quizLength = 0;
    String filetxt = "";
    String correct = "";

    public void setCurrentLesson(String currentLesson) {
        this.currentLesson = currentLesson;
    }

    public void growQuiz() {
        quizPos++;
    }

    public void readFile() throws FileNotFoundException {
        File file = new File("quiz/" + currentLesson + ".txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            filetxt += sc.nextLine() + "\n";
        }
    }

    public void loadComponents() throws FileNotFoundException {
        rgroup = new ToggleGroup();
        quizLength = filetxt.split(";").length;
        String currentQuiz = filetxt.split(";")[quizPos];
        String[] quizCompo = currentQuiz.split("\\r?\\n");
        console.setText("");
        title.setText(quizCompo[0].split(":")[1]);
        question.setText(quizCompo[1].split(":")[1]);
        image.setImage(null);
        avanti.setDisable(true);
        if (!quizCompo[2].split(":")[1].equals("-")) {
            image.setImage(new Image("file:" + (quizCompo[2].split(":")[1])));
        }
        String[] answers = quizCompo[3].split(":")[1].split("/");
        int count = 1;
        radioBox.getChildren().clear();
        for (String ans : answers) {
            RadioButton btn = new RadioButton();
            btn.setToggleGroup(rgroup);
            btn.setText(ans);
            btn.setId("opt" + count);
            btn.setWrapText(true);
            //btn.setPadding(new Insets(5,5,5,5));
            radioBox.setSpacing(12);
            radioBox.getChildren().add(btn);
            count++;
        }
        correct = quizCompo[4].split(":")[1];
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            try {
                readFile();
                loadComponents();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(QuizController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}

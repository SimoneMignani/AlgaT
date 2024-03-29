/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algat;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author francesco
 */
public class LezioniController implements Initializable {

    @FXML
    private ImageView graduationCap;

    @FXML
    private VBox buttonBox;

    @FXML
    private ImageView algoImg;

    @FXML
    private Button runBtn;

    private String currentLesson;

    @FXML
    void runAlgorithm(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("algoritmo.fxml"));
        Scene newScene = new Scene(parent);

        //prendiamo le informazioni di Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("AlgaT - Dijkstra");
        window.show();
    }

    public void checkLessons() throws IOException { //prende i file txt da una cartella quiz
        File dir = new File("quiz");

        File[] filelist = dir.listFiles((File dir1, String name) -> name.toLowerCase().endsWith(".txt"));

        int count = 1;
        
        for (File filelist1 : filelist) {
            String fileName = filelist1.getName();
            if (fileName.equals("Grafi.txt")) {
                String lessonName = fileName.split("\\.")[0];
                createLessonButton(lessonName, count);
                count++;
                break;
            }
        }
        
        for (File filelist1 : filelist) {
            String fileName = filelist1.getName();
            if (!fileName.equals("Grafi.txt")) {
                String lessonName = fileName.split("\\.")[0];
                createLessonButton(lessonName, count);
                count++;
            }
        }
        /*for (File filelist1 : filelist) {
            String fileName = filelist1.getName();
            String lessonName = fileName.split("\\.")[0];
            createLessonButton(lessonName, count);
            count++;
        }*/
    }

    public void createLessonButton(String lessonName, int lessonNumber) {
        Button btn = new Button();
        btn.setText("Lezione " + lessonNumber + " - " + lessonName);
        btn.setPadding(new Insets(10, 10, 10, 10));
        btn.setFont(Font.font(15));
        btn.setPrefSize(269, 29);
        //btn.setId(lessonName);
        btn.setOnAction((ActionEvent event) -> {
            try {
                //Parent lezione = FXMLLoader.load(getClass().getResource("lezione.fxml"));
                FXMLLoader lezioneLoader = new FXMLLoader(getClass().getResource("lezione.fxml"));
                Scene newScene = new Scene(lezioneLoader.load());

                LezioneController controller = lezioneLoader.getController();
                controller.setCurrentLesson(lessonName);
                //prendiamo le informazioni di Stage
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(newScene);
                window.setTitle("AlgaT - " + lessonName);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(LezioniController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        buttonBox.setSpacing(5);
        buttonBox.getChildren().add(btn);
    }

    public String getCurrentLesson() {
        return currentLesson;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image gb = new Image("file:img/graduation-cap_1f393.png");
        graduationCap.setImage(gb);
        gb = new Image("file:img/electric-light-bulb_1f4a1.png");
        algoImg.setImage(gb);

        try {
            checkLessons();
        } catch (IOException ex) {
            Logger.getLogger(LezioniController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

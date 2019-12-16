/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author francesco
 */
public class LezioneController implements Initializable {

    @FXML
    private WebView webview;

    @FXML
    private Button btnContinua;

    @FXML
    private Button btnIndietro;

    @FXML
    void continua(ActionEvent event) throws IOException {
        FXMLLoader quizLoader = new FXMLLoader(getClass().getResource("quiz.fxml"));
        Scene newScene = new Scene(quizLoader.load());

        QuizController controller = quizLoader.getController();
        controller.setCurrentLesson(currentLesson);
        //prendiamo le informazioni di Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("AlgaT - Quiz");
        window.show();
    }

    @FXML
    void indietro(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("lezioni.fxml"));
        Scene newScene = new Scene(parent);

        //prendiamo le informazioni di Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("AlgaT - Lista delle lezioni");
        window.show();
    }

    private String currentLesson;

    public void setCurrentLesson(String currentLesson) {
        this.currentLesson = currentLesson;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //serve per avere i parametri passati correttamente dal padre
        Platform.runLater(() -> {
            final WebEngine web = webview.getEngine();
            web.setJavaScriptEnabled(true);

            //String urlHTML = "http://francesco.pezzulli.tw.cs.unibo.it/" + currentLesson + "/" + currentLesson + ".html";
            String urlPDF = "http://francesco.pezzulli.tw.cs.unibo.it/" + currentLesson + ".pdf";
            String urlweb = "http://docs.google.com/gview?embedded=true&url=" + urlPDF;
            //String urlwebGDrive = "https://drive.google.com/viewerng/viewer?embedded=true&url=" + urlPDF;
            
            web.load(urlweb);
        });
    }
}

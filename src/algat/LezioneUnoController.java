/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algat;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
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
 * @author Francesco
 */
public class LezioneUnoController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView arco;

    @FXML
    private ImageView grafo_non_orientato;

    @FXML
    private ImageView grafo_orientato;

    @FXML
    private ImageView grafo_base;

    //metodo che cambia la scene quando il bottone viene premuto
    public void backToLessonsList(ActionEvent event) throws IOException {
        Parent lezioneUNO = FXMLLoader.load(getClass().getResource("lessonsList.fxml"));
        Scene newScene = new Scene(lezioneUNO);

        //prendiamo le informazioni di Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("AlgaT - Lista delle lezioni");
        window.show();
    }

    //metodo che cambia la scene quando il bottone viene premuto
    public void nextToQuiz(ActionEvent event) throws IOException {
        Parent lezioneUNO = FXMLLoader.load(getClass().getResource("quizUnoLezioneUno.fxml"));
        Scene newScene = new Scene(lezioneUNO);

        //prendiamo le informazioni di Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        //window.setTitle("AlgaT - Lezione Uno - Grafi");
        window.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image gb = new Image("file:img/grafo_base.jpg");
        grafo_base.setImage(gb);

        Image arc = new Image("file:img/arco.jpg");
        arco.setImage(arc);

        Image go = new Image("file:img/grafo_orientato.jpg");
        grafo_orientato.setImage(go);

        Image gno = new Image("file:img/grafo_non_orientato.jpg");
        grafo_non_orientato.setImage(gno);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Francesco
 */
public class AlgoritmoController implements Initializable {

    //dati relativi alla costruzione e visualizzazione del grafo
    static Text peso;

    @FXML
    private Pane pannello;

    @FXML
    private Button destinazione;

    @FXML
    private Button run;

    @FXML
    private Button back;

    @FXML
    private Button clear;

    @FXML
    private Button runPassi;

    @FXML
    private ToggleGroup insert;

    @FXML
    private RadioButton opz1;

    @FXML
    private RadioButton opz2;

    @FXML
    private Label msg;

    @FXML
    private Label textCoda;

    boolean insert_nodi = true, insert_archi;

    int add_edge = 0;
    int num_nodi = 0;

    double last_x = 0;
    double last_y = 0;
    boolean destinazClicked = false;
    boolean destinazInserita = false;

    // dati relativi all'esecuzione di dijkstra
    //i primi sono static perchè devono essere utilizzati anche dentro il metodo static setCost() 
    static ArrayList<Nodo> codaPriorita;
    ArrayList<Nodo> codaScarto;
    static int lastNodeIndex;
    static String lastNodeId;
    static String updateNodeIndex;

    ArrayList<Text> etichette = new ArrayList<>();
    ArrayList<Circle> cerchi = new ArrayList<>();
    ArrayList<Line> linee = new ArrayList<>();

    @FXML
    void Menu(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("lezioni.fxml"));
        Scene newScene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("AlgaT - Menu Principale");
        window.show();
    }

    @FXML
    void radioSelect(ActionEvent event) {
        RadioButton radio = (RadioButton) insert.getSelectedToggle();
        String selected = "";
        selected = radio.getId();
        if (selected.equals("opz1")) {
            msg.setText("Inserisci i nodi cliccando nell'area specifica");
            insert_nodi = true;
            insert_archi = false;
            if (codaPriorita.size() > 1 && !destinazInserita) {
                destinazione.setDisable(false);
            }
        } else if (selected.equals("opz2")) {
            msg.setText("Inserisci gli archi cliccando sui nodi tra cui li si vuole inserire");
            insert_nodi = false;
            insert_archi = true;
            destinazione.setDisable(true);
        }
    }

    @FXML
    void clearGraph(ActionEvent event) {
        pannello.getChildren().clear();
        num_nodi = 0;
        msg.setText("Grafo rimosso!\nInserisci i nodi cliccando nell'area specifica");
        codaPriorita.clear();
        codaScarto.clear();
        back.setDisable(true);
        destinazione.setDisable(true);
        run.setDisable(true);
        runPassi.setDisable(true);
        insert_nodi = true;
        opz1.setSelected(true);
        opz1.setDisable(false);
        insert_archi = false;
        opz2.setSelected(false);
        opz2.setDisable(true);
        destinazClicked = false;
        destinazInserita = false;
        etichette.clear();
        cerchi.clear();
        linee.clear();
        textCoda.setText("Coda di priorità pulita.");
    }

    @FXML
    void clearLastModify(ActionEvent event) {
        if (pannello.getChildren().get(pannello.getChildren().size() - 3) instanceof Circle) {
            msg.setText("Nodo rimosso");
            int count = 0;
            //controllo se il nodo che ho cancellato è la destinazione
            for (int i = 0; i < codaPriorita.size(); i++) {
                if (pannello.getChildren().get(pannello.getChildren().size() - 3).getId().equals(codaPriorita.get(i).id)) {
                    if (codaPriorita.get(i).destinazione) {
                        if (opz1.isSelected()) {
                            destinazione.setDisable(false);
                        }
                        run.setDisable(true);
                        runPassi.setDisable(true);
                        codaPriorita.get(i).destinazione = false;
                        destinazInserita = false;
                        break;
                    }
                }
            }
            while (count != 3) {
                pannello.getChildren().remove(pannello.getChildren().size() - 1);
                count++;
            }
            etichette.remove(etichette.size() - 1);
            cerchi.remove(cerchi.size() - 1);
            codaPriorita.remove(codaPriorita.size() - 1);
            num_nodi--;

        } else if (pannello.getChildren().get(0) instanceof Line) {
            msg.setText("Arco rimosso");
            pannello.getChildren().remove(pannello.getChildren().size() - 1);
            pannello.getChildren().remove(0);
            linee.remove(linee.size() - 1);
        }

        if (pannello.getChildren().isEmpty()) {
            back.setDisable(true);
        }

        if (cerchi.size() < 2) {
            destinazione.setDisable(true);
            opz1.setSelected(true);
            opz2.setSelected(false);
            opz2.setDisable(true);
            insert_nodi = true;
            insert_archi = false;
        }
    }

    @FXML
    void impostaDestinazione(ActionEvent event) {
        if (insert_nodi && !insert_archi && !destinazClicked) {
            msg.setText("Clicca sulla destinazione che preferisci");
            destinazClicked = true;
        }

    }

    public void addWeight() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("weightAdder.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inserisci");
        stage.setOnCloseRequest(e -> { //se si chiude la finestra senza inserire un peso
            setCost(0);
        });
        stage.show();

    }

    public static void setCost(int i) {
        peso.setText("" + i);
        codaPriorita.get(lastNodeIndex).archiAdiacenti.replace(updateNodeIndex, i);
    }

    public void printCammino() {
        String mex = "";
        System.out.println("ALGORITMO FINITO - Destinazione in testa");
        codaScarto.add(codaPriorita.get(0));

        mex += "ALGORTIMO TERMINATO !\n\n Cammino minimo:\n";

        printCoda();

        // ricostruzione del percoso minimo
        ArrayList<String> camminoMinimo = new ArrayList<>();
        camminoMinimo.add(codaScarto.get(codaScarto.size() - 1).id);
        String pred = codaScarto.get(codaScarto.size() - 1).predecessore;

        while (!pred.equals("n0")) {
            camminoMinimo.add(0, pred);
            for (int i = 0; i < codaScarto.size(); i++) {
                if (codaScarto.get(i).id.equals(pred)) {
                    pred = codaScarto.get(i).predecessore;
                }
            }
        }
        camminoMinimo.add(0, "n0");
        for (int i = 0; i < camminoMinimo.size(); i++) {
            if ((i + 1) == camminoMinimo.size()) {
                mex += camminoMinimo.get(i);
            } else {
                mex += camminoMinimo.get(i) + " - ";
            }
        }

        msg.setText(mex);

        for (int i = 0; i < camminoMinimo.size(); i++) {
            //colora di giallo gli archi del cammino minimo
            if ((i + 1) != camminoMinimo.size()) {  //controllo se si è all'ultimo nodo (cioè non esistono più rami dopo)
                for (int j = 0; j < linee.size(); j++) {
                    if (linee.get(j).getId().equals(String.valueOf(camminoMinimo.get(i) + "/" + camminoMinimo.get(i + 1)))) {
                        linee.get(j).setStroke(Color.GOLD);

                        break;
                    }
                }

            }

            for (int j = 0; j < cerchi.size(); j++) {          //colora i nodi del cammino minimo di giallo
                if (cerchi.get(j).getId().equals(camminoMinimo.get(i))) {
                    cerchi.get(j).setFill(Color.GOLD);
                    cerchi.get(j).setStroke(Color.GOLD);
                    break;
                }
            }
            System.out.println((i + 1) + ") " + camminoMinimo.get(i) + "\n");
            run.setDisable(true);
            runPassi.setDisable(true);
        }
    }

    public void printCoda() {
        if (!codaPriorita.isEmpty()) {
            String m = "";
            for (int i = 0; i < codaPriorita.size(); i++) {
                if (codaPriorita.get(i).dist == Integer.MAX_VALUE) {
                    m += "" + codaPriorita.get(i).id + "      |      ∞      |      " + codaPriorita.get(i).predecessore + "\n";
                } else {
                    m += "" + codaPriorita.get(i).id + "      |      " + codaPriorita.get(i).dist + "      |      " + codaPriorita.get(i).predecessore + "\n";
                }

                System.out.println("" + codaPriorita.get(i).id + " | " + codaPriorita.get(i).dist + " | " + codaPriorita.get(i).predecessore);
            }
            System.out.println("-----------------------------");
            textCoda.setText(m);
        }
    }

    public void dijkstra() throws InterruptedException {
        //controlla se esiste un cammino completo (circa ahahah)
        if ((linee.size() + 1) >= cerchi.size()) {

            back.setDisable(true);
            opz1.setDisable(true);
            opz2.setDisable(true);
            insert_archi = false;
            insert_nodi = false;
            runPassi.setDisable(true);
            while (!codaPriorita.isEmpty()) {
                if (codaPriorita.get(0).destinazione) {
                    System.out.println("ALGORITMO FINITO - Destinazione in testa");
                    codaScarto.add(codaPriorita.get(0));
                    break;
                }
                for (HashMap.Entry<String, Integer> indexHash : codaPriorita.get(0).archiAdiacenti.entrySet()) {  //indice è  "index"
                    int indexDest = 0;
                    for (int j = 0; j < codaPriorita.size(); j++) {
                        if (codaPriorita.get(j).id.equals(indexHash.getKey())) {
                            indexDest = j;
                            break;
                        }
                    }

                    if (Integer.valueOf(codaPriorita.get(0).dist) + indexHash.getValue() < codaPriorita.get(indexDest).dist) {
                        codaPriorita.get(indexDest).dist = Integer.valueOf(codaPriorita.get(0).dist) + indexHash.getValue(); //aggiorna etichetta del nodo
                        for (int i = 0; i < etichette.size(); i++) {
                            if (etichette.get(i).getId().equals("dist" + indexHash.getKey())) {
                                etichette.get(i).setText(String.valueOf(codaPriorita.get(indexDest).dist));
                                break;
                            }
                        }
                        codaPriorita.get(indexDest).predecessore = codaPriorita.get(0).id;  //aggiorna predecessore del nodo   
                    }
                } //chiude ciclo HASH

                //riordinamento coda priorità
                Collections.sort(codaPriorita, Nodo.ordinaPerEtichetta); //riordinamento coda priorità
                codaScarto.add(codaPriorita.get(0));
                codaPriorita.remove(0);
            } // chiude ciclo While
            printCammino();

        } else { //se non esiste un cammino completo
            msg.setText("ERRORE! Bisogna creare un cammino completo per eseguire l'algoritmo.");
        }
    }//chiude metodo

    String nodoPrecedente = "";

    public void dijkstraPerPassi() throws InterruptedException {
        //controlla se esiste un cammino completo (circa ahahah)
        if ((linee.size() + 1) >= cerchi.size()) {

            back.setDisable(true);
            clear.setDisable(true);
            opz1.setDisable(true);
            opz2.setDisable(true);
            insert_archi = false;
            insert_nodi = false;
            run.setDisable(true);
            runPassi.setText("Passo successivo");
            String m = "";
            if (!nodoPrecedente.equals("")) {
                //ridà il colore originario al nodo precedente
                for (int i = 0; i < cerchi.size(); i++) {
                    if (nodoPrecedente.equals(cerchi.get(i).getId())) {
                        if (nodoPrecedente.equals("n0")) {
                            cerchi.get(i).setFill(Color.GREEN);
                            cerchi.get(i).setStroke(Color.GREEN);
                        } else {
                            cerchi.get(i).setFill(Color.DARKORANGE);
                            cerchi.get(i).setStroke(Color.DARKORANGE);
                        }
                    }
                }
                //ridà il colore originario alle linee precedenti
                for (int i = 0; i < linee.size(); i++) {
                    String[] l = linee.get(i).getId().split("/");
                    if (l[0].equals(nodoPrecedente)) {
                        linee.get(i).setStroke(Color.BLACK);
                    }
                }
            }
            //fine algoritmo
            if (codaPriorita.get(0).destinazione || codaPriorita.isEmpty()) {
                runPassi.setText("Esegui per passi");
                clear.setDisable(false);
                printCammino();
                nodoPrecedente = "";
            } else { //ogni passo del algortimo
                //colora il nodo attuale di giallo
                for (int j = 0; j < cerchi.size(); j++) {
                    if (cerchi.get(j).getId().equals(codaPriorita.get(0).id)) {
                        cerchi.get(j).setFill(Color.YELLOW);
                        cerchi.get(j).setStroke(Color.YELLOW);
                        nodoPrecedente = codaPriorita.get(0).id;
                        break;
                    }
                }
                //colora le linee attuali di giallo
                for (int i = 0; i < linee.size(); i++) {
                    String[] l = linee.get(i).getId().split("/");
                    if (l[0].equals(codaPriorita.get(0).id)) {
                        linee.get(i).setStroke(Color.YELLOW);
                    }
                }
                m += "NODO " + codaPriorita.get(0).id + "\n";
                for (HashMap.Entry<String, Integer> indexHash : codaPriorita.get(0).archiAdiacenti.entrySet()) {  //indice è  "index"
                    int indexDest = 0;
                    int indexCircle = 0;
                    for (int j = 0; j < codaPriorita.size(); j++) {
                        if (codaPriorita.get(j).id.equals(indexHash.getKey())) {
                            indexDest = j;
                            break;
                        }
                    }
                    m += "Valutando l'arco " + codaPriorita.get(0).id + " --> " + indexHash.getKey() + "\n";
                    msg.setText(m);

                    if (Integer.valueOf(codaPriorita.get(0).dist) + indexHash.getValue() < codaPriorita.get(indexDest).dist) {
                        //aggiorna etichetta del nodo
                        codaPriorita.get(indexDest).dist = Integer.valueOf(codaPriorita.get(0).dist) + indexHash.getValue();
                        for (int i = 0; i < etichette.size(); i++) {
                            if (etichette.get(i).getId().equals("dist" + indexHash.getKey())) {
                                etichette.get(i).setText(String.valueOf(codaPriorita.get(indexDest).dist));
                                break;
                            }

                        }
                        codaPriorita.get(indexDest).predecessore = codaPriorita.get(0).id;  //aggiorna predecessore del nodo   
                    }
                    indexCircle = 0;
                } //chiude ciclo HASH

                Collections.sort(codaPriorita, Nodo.ordinaPerEtichetta); //riordinamento coda priorità
                printCoda();
                codaScarto.add(codaPriorita.get(0));
                codaPriorita.remove(0);
            }
        } else {  //se non esiste un cammino completo
            msg.setText("ERRORE! Bisogna creare un cammino completo per eseguire l'algoritmo.");
        }
    }//chiude metodo

    String lineId = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        msg.setPadding(new Insets(0, 5, 0, 5));
        msg.setText("Inserisci i nodi cliccando nell'area specifica");
        codaPriorita = new ArrayList<>();
        codaScarto = new ArrayList<>();

        pannello.setOnMouseClicked(e -> {
            Circle circle = new Circle(e.getX(), e.getY(), 15);
            circle.setId(String.valueOf("n" + num_nodi));
            circle.setStrokeWidth(20);
            circle.setOnMouseClicked(k -> {
                if (!destinazClicked) {
                    if (!insert_nodi && insert_archi) {  //modalità inserisci archi
                        if (add_edge == 0) {
                            add_edge++;
                            last_x = e.getX();
                            last_y = e.getY();
                            lineId += circle.getId() + "/";
                            msg.setText("PRIMO nodo cliccato. Seleziona il secondo.");

                            for (int i = 0; i < codaPriorita.size(); i++) {
                                if (codaPriorita.get(i).id.equals(circle.getId())) {
                                    lastNodeIndex = i;
                                    lastNodeId = circle.getId();

                                }
                            }
                        } else if (add_edge == 1) {
                            Line line = new Line(last_x, last_y, e.getX(), e.getY());
                            lineId += circle.getId();
                            line.setId(String.valueOf(lineId));
                            line.setStrokeWidth(3);
                            pannello.getChildren().add(line);
                            line.toBack();
                            linee.add(line);
                            lineId = "";
                            /*Polygon freccia = new Polygon();
                            freccia.getPoints().addAll(new Double[]{
                                (e.getX()+((e.getX()+last_x)/2))/2, (e.getY()+((e.getY()+last_y)/2))/2,
                                ((e.getX()+last_x)/2)-10,((e.getY()+last_y)/2)-10,
                                ((e.getX()+last_x)/2)-10,((e.getY()+last_y)/2)+10});
                            pannello.getChildren().add(freccia);
                            freccia.setFill(Color.RED);*/
                            msg.setText("SECONDO nodo cliccato.\nArco creato.");
                            try {
                                addWeight();
                            } catch (IOException ex) {
                                Logger.getLogger(AlgoritmoController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            updateNodeIndex = circle.getId();
                            peso = new Text("");
                            peso.setText("");
                            peso.setFont(Font.font(15));
                            peso.toFront();
                            peso.setLayoutX((last_x + e.getX()) / 2 + 10);
                            peso.setLayoutY((last_y + e.getY()) / 2 + 10);
                            peso.setFill(Color.BLACK);
                            pannello.getChildren().add(peso);

                            codaPriorita.get(lastNodeIndex).archiAdiacenti.put(circle.getId(), 0);

                            add_edge = 0;
                            last_x = 0;
                            last_y = 0;
                        }
                    }
                } else {
                    if (!destinazInserita) {
                        if (circle.getId().equals("n0")) {
                            msg.setText("L'origine non può essere la destinazione.\nRicliccare 'Destinazione' per aggiungerne una nuova.");
                        } else {
                            msg.setText("Destinazione aggiunta: " + circle.getId());
                            System.out.println("DESTINAZIONE AGGIUNTA --> " + circle.getId());
                            circle.setFill(Color.RED);
                            circle.setStroke(Color.RED);
                            destinazione.setDisable(true);
                            run.setDisable(false);
                            runPassi.setDisable(false);
                            destinazInserita = true;
                            for (int i = 0; i < codaPriorita.size(); i++) {
                                if (codaPriorita.get(i).id.equals(circle.getId())) {
                                    codaPriorita.get(i).destinazione = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            });

            if (insert_nodi && !insert_archi && !destinazClicked) {  //modalità inserisci nodi
                System.out.println(e.getX() + " - " + e.getY());
                Text text = new Text(String.valueOf(num_nodi));
                text.setFont(Font.font(20));
                Text dist;
                if (circle.getId().equals("n0")) {
                    circle.setFill(Color.GREEN);
                    circle.setStroke(Color.GREEN);
                    text.setFill(Color.WHITE);
                    dist = new Text("0");
                    dist.setId("distn" + num_nodi);
                    dist.setLayoutX(e.getX());
                    dist.setLayoutY(e.getY() - 30);
                    dist.setFont(Font.font(23));
                    dist.setFill(Color.RED);
                    cerchi.add(circle);
                    etichette.add(dist);
                    codaPriorita.add(new Nodo(circle.getId(), 0, "-", false));
                } else {
                    circle.setFill(Color.DARKORANGE);
                    circle.setStroke(Color.DARKORANGE);
                    text.setFill(Color.BLACK);
                    dist = new Text("∞");
                    dist.setId("distn" + num_nodi);
                    dist.setLayoutX(e.getX());
                    dist.setLayoutY(e.getY() - 30);
                    dist.setFont(Font.font(23));
                    dist.setFill(Color.RED);
                    cerchi.add(circle);
                    etichette.add(dist);
                    codaPriorita.add(new Nodo(circle.getId(), Integer.MAX_VALUE, "-", false));
                }
                text.toFront();
                text.layoutXProperty().bind(circle.centerXProperty().add(-text.getLayoutBounds().getWidth() / 2));
                text.layoutYProperty().bind(circle.centerYProperty().add(5));

                if (codaPriorita.size() > 1) {
                    opz2.setDisable(false);
                    if (!destinazInserita) {
                        destinazione.setDisable(false);
                    }
                }

                num_nodi++;
                pannello.getChildren().addAll(circle, text, dist);
                msg.setText("Nodo " + circle.getId() + " inserito");
                for (int i = 0; i < codaPriorita.size(); i++) {
                    System.out.println(codaPriorita.get(i).toString());

                }

                if (!pannello.getChildren().isEmpty()) {
                    back.setDisable(false);
                }
            }
            destinazClicked = false; //per evitare che inserisca un nuovo nodo quando cerco di inserire la destinazione
        });

    } //chiude initialize

} // chiude classe


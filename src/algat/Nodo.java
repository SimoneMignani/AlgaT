/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algat;

import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author Francesco
 */
public class Nodo {
    
    String id; // identifica il nodo
    int dist;
    String predecessore; // indica l'id del nodo che al momento è il predecessore 
    boolean destinazione;

    public Nodo(String id, int dist, String predecessore, boolean destinazione) {
        this.id = id;
        this.dist = dist;
        this.predecessore = predecessore;
        this.destinazione = destinazione;
    }
    HashMap<String, Integer> archiAdiacenti = new HashMap<>(); //lista degli archi adiacenti composta da idAcrco - peso 

    @Override
    public String toString() {
        return "Nodo{" + "id=" + id + ", dist=" + dist + ", predecessore=" + predecessore + ", archiAdiacenti=" + archiAdiacenti + ", destinazione: " + destinazione + '}';
    }

    public void setPredecessore(String predecessore) {
        this.predecessore = predecessore;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public int getDist() {
        return dist;
    }
    
    public void setArchiAdiacenti(HashMap<String, Integer> archiAdiacenti) {
        this.archiAdiacenti = archiAdiacenti;
    }

    public boolean isDestinazione() {
        return destinazione;
    }
    
    public static Comparator<Nodo> ordinaPerEtichetta = new Comparator<Nodo>() {
        public int compare(Nodo e1, Nodo e2) {
            Integer uno = new Integer(e1.getDist());
            Integer due = new Integer(e2.getDist());

            return uno.compareTo(due);
        }
    };
    
    
    
}

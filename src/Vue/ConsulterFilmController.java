/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Vue;

import Commun.AppContext;
import Model.Cinema.Evaluation;
import Model.Cinema.Film;
import Model.Utilisateurs.Utilisateur;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ConsulterFilmController implements Initializable {

    @FXML
    private TextField TitreFilm;
    @FXML
    private TableColumn<Evaluation, Integer> Note;
    @FXML
    private TableColumn<Evaluation, String> Commentaire;
    @FXML
    private TableColumn<Evaluation, String> Film;
    @FXML
    private TableColumn<Evaluation, String> utilisateur;
    @FXML
    private TableView<Evaluation> tableevaluation;
    @FXML
    private Label erreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void RechercherClicked(ActionEvent event) {
        AppContext context = AppContext.getInstance();
        String titre = TitreFilm.getText().trim();
        ArrayList<Evaluation> L= new ArrayList<>();
        Film film = null;
        for(Film f : context.getCinema().getListedefilms()){
            if(f.getTitre().equals(titre)){
                film=f;
            }
        }
        if(film!=null){
            erreur.setText("");
            for(Utilisateur u : context.getUsers()){
                for(Evaluation e : u.getHistoriqueEvaluation()){
                    if(e.getFilm().equals(titre)){
                        L.add(e);
                    }
                }
            }
            ObservableList<Evaluation> evaluations = FXCollections.observableArrayList(L);
        Note.setCellValueFactory(new PropertyValueFactory<>("note"));
        Commentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        Film.setCellValueFactory(new PropertyValueFactory<>("film"));
        utilisateur.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
        tableevaluation.setItems(evaluations);
        if(evaluations.size()==0){
            erreur.setText("il y a pas des evaluations pour ce film");
        }
        }else{
            erreur.setText("film n'existe pas ");
        }
        
    }
    
}

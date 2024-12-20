/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Vue;

import Commun.AppContext;
import Model.Cinema.Evaluation;
import Model.Cinema.Film;
import Model.Utilisateurs.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amani
 */
public class EvaluationfController implements Initializable {
    private Integer ID ;
    @FXML
    private TableView<Evaluation> evaluationTable;
    @FXML
    private TableColumn<Evaluation, Double> noteColumn;
    @FXML
    private TableColumn<Evaluation, String> commentaireColumn;
    @FXML
    private TableColumn<Evaluation, Film> filmColumn;
    @FXML
    private TableColumn<Evaluation, Utilisateur> utilisateurColumn;
    @FXML
    private Button quiterlisteval;
    @FXML
    private Label msgeval;
    public void setID(Integer id ){
        this.ID=id;
        AppContext context = AppContext.getInstance();
    Utilisateur user = context.getUsers().get(ID-1); // Récupérer l'utilisateur courant

    ObservableList<Evaluation> evaluations = FXCollections.observableArrayList(user.getHistoriqueEvaluation());

    // Vérifier s'il y a des évaluations
    if (evaluations.isEmpty()) {
        msgeval.setText("Aucune évaluation effectuée par vous, " + user.getNom() + " !");
        evaluationTable.setItems(null); // Effacer les données de la table
    } else {
        msgeval.setText(""); // Effacer les anciens messages
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
        commentaireColumn.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        filmColumn.setCellValueFactory(new PropertyValueFactory<>("film"));
        utilisateurColumn.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
        evaluationTable.setItems(evaluations);
    }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Liez les colonnes aux propriétés
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
        commentaireColumn.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        filmColumn.setCellValueFactory(new PropertyValueFactory<>("film"));
        utilisateurColumn.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
        

       
      
    
}

    @FXML
    private void Regenereval(ActionEvent event) {
        
    AppContext context = AppContext.getInstance();
    Utilisateur user = context.getUsers().getFirst(); // Récupérer l'utilisateur courant

    ObservableList<Evaluation> evaluations = FXCollections.observableArrayList(user.getHistoriqueEvaluation());

    // Vérifier s'il y a des évaluations
    if (evaluations.isEmpty()) {
        msgeval.setText("Aucune évaluation effectuée par vous, " + user.getNom() + " !");
        evaluationTable.setItems(null); // Effacer les données de la table
    } else {
        msgeval.setText(""); // Effacer les anciens messages
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
        commentaireColumn.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        filmColumn.setCellValueFactory(new PropertyValueFactory<>("titreFilm"));
        utilisateurColumn.setCellValueFactory(new PropertyValueFactory<>("emailUser"));
        evaluationTable.setItems(evaluations);
    }
}

   
    
    @FXML
    private void quitterlisteeval(ActionEvent event) {
         try {
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
        Parent root = loader.load();
        
        // Show the new stage
        Stage signinStage = new Stage();
        signinStage.setTitle("Reserver SEance");
        signinStage.setScene(new Scene(root));
        signinStage.show();
    } catch (IOException ex) {
            System.out.println("erreur");
    }
    }
}



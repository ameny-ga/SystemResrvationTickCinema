/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Vue;

import Commun.AppContext;
import Model.Cinema.Reservation;
import Model.Cinema.Seance;
import Model.Commun.Date;
import Model.Utilisateurs.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class ListeResrevController implements Initializable {
    private Integer ID ;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label userEmailLabel;
    @FXML
    private TableView<Reservation> tableView;
    @FXML
    private TableColumn<Reservation, Integer> colId;
    @FXML
    private TableColumn<Reservation, Integer> colSeance;
    @FXML
    private TableColumn<Reservation, Double> colPrix;
    @FXML
    private Label msgResrv;
    @FXML
    private TableColumn<Reservation, String> Salle;
    @FXML
    private TableColumn<Reservation, String> Film;
    @FXML
    private TableColumn<Reservation, Date> Date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setID(Integer id){
        this.ID=id;
        AppContext context = AppContext.getInstance();
    Utilisateur user = context.getUsers().get(ID-1); // Récupérer l'utilisateur courant

    
    ObservableList<Reservation> reservations = FXCollections.observableArrayList(user.getHistoriqueReservation());
                  userNameLabel.setText(user.getNom()); 
                userEmailLabel.setText(user.getEmail());      
    // Vérifier s'il y a des réservations
    if (reservations.isEmpty()) {
        msgResrv.setText("Pas encore de réservations !");
        tableView.setItems(null); // Effacer les données de la table
    } else {
        msgResrv.setText(""); // Effacer les anciens messages
         colId.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("id"));
          colSeance.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("seanceId"));
           colPrix.setCellValueFactory(new PropertyValueFactory<Reservation, Double>("Prix"));
           Salle.setCellValueFactory(cellData -> {Reservation reservation = cellData.getValue();return new SimpleStringProperty(reservation.getSeance() != null && reservation.getSeance().getSalle() != null ? reservation.getSeance().getSalle().getLabelle(): "");});
           Film.setCellValueFactory(cellData -> {Reservation reservation = cellData.getValue();return new SimpleStringProperty(reservation.getSeance() != null && reservation.getSeance().getFilm() != null ? reservation.getSeance().getFilm().getTitre(): "");});
           Date.setCellValueFactory(cellData -> {Reservation reservation = cellData.getValue();return new SimpleObjectProperty<>(reservation.getSeance() != null? reservation.getSeance().getDate(): null);});
    }
     tableView.setItems(reservations);
        
        
    }

    @FXML
    private void RegenerListResrv(ActionEvent event) {
 
    AppContext context = AppContext.getInstance();
    Utilisateur user = context.getUsers().get(ID-1); // Récupérer l'utilisateur courant

    
    ObservableList<Reservation> reservations = FXCollections.observableArrayList(user.getHistoriqueReservation());
                  userNameLabel.setText(user.getNom()); 
                userEmailLabel.setText(user.getEmail());      
    // Vérifier s'il y a des réservations
    if (reservations.isEmpty()) {
        msgResrv.setText("Pas encore de réservations !");
        tableView.setItems(null); // Effacer les données de la table
    } else {
        msgResrv.setText(""); // Effacer les anciens messages
         colId.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("id"));
          colSeance.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("seanceId"));
           colPrix.setCellValueFactory(new PropertyValueFactory<Reservation, Double>("Prix"));
           Salle.setCellValueFactory(new PropertyValueFactory<Reservation, String>("seance.getSalle()"));
           Film.setCellValueFactory(new PropertyValueFactory<Reservation, String>("seance.getFilm().getTitre()"));
           Date.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("seance.getDate()"));
    }
     tableView.setItems(reservations);
   }

    @FXML
    private void OnQuitter(ActionEvent event) {
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

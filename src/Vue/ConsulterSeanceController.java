/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Vue;

import Commun.AppContext;
import Model.Cinema.Reservation;
import Model.Cinema.Seance;
import Model.Utilisateurs.Utilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class ConsulterSeanceController implements Initializable {
    private boolean trouve = false ;
    @FXML
    private Button Rechercher;
    @FXML
    private Label SeanceTrouve;
    private TableColumn<Reservation, Integer> id;
    private TableColumn<Reservation, String> Sid;
    private TableColumn<Reservation, Utilisateur > utilisateur;
    @FXML
    private Label nbrReservation;
    @FXML
    private TextField IDseance;
    @FXML
    private TableView<Reservation> ListUtilisatuer;
    @FXML
    private TableColumn<Reservation, Integer> Rid;
    @FXML
    private TableColumn<Reservation, String> Utilisateur;
    @FXML
    private TableColumn<Reservation, Double> Prix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RechercherButtonClicked(ActionEvent event) {
        Integer idseance = Integer.parseInt(IDseance.getText());
        AppContext context = AppContext.getInstance();
        Seance san = null;
        for(Seance s : context.getCinema().getListedeseances()){
            if (s.getSid()==idseance){
                SeanceTrouve.setText("Seance Trouve");
                trouve=true;
                san=s;    
            }
        }
        if(!trouve){
            SeanceTrouve.setText("Seance Non Trouve");
        }
        if(trouve){
            ObservableList<Reservation> resers = FXCollections.observableArrayList(san.getListReservation());System.out.println(san);
            Rid.setCellValueFactory(new PropertyValueFactory<Reservation , Integer>("id"));
            Utilisateur.setCellValueFactory(new PropertyValueFactory<Reservation , String>("utilisateurNom"));
            Prix.setCellValueFactory(new PropertyValueFactory<Reservation , Double>("prix"));
            ListUtilisatuer.setItems(resers);
            nbrReservation.setText(String.valueOf(san.getListReservation().size()));
        }
        
    }
    
}

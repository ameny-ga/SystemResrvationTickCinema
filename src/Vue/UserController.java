/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Vue;


import Commun.AppContext;
import Model.Cinema.Seance;
import Model.Commun.Date;
import Model.Utilisateurs.Utilisateur;
import com.sun.javafx.logging.PlatformLogger.Level;
import com.sun.media.jfxmedia.logging.Logger;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amani
 */
public class UserController implements Initializable {
    private Integer ID ;
    private Utilisateur user ;
    @FXML
    private AnchorPane anhide;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label mn;
    @FXML
    private TableView<Seance> seancesTable;
    @FXML
    private Button Quiter;
    @FXML
    private TableColumn<Seance, Integer> IDSeance;
    @FXML
    private TableColumn<Seance, String> Titrefilm;
    @FXML
    private TableColumn<Seance, String> labelleS;
    @FXML
    private TableColumn<Seance, Date> Dateseance;

    /**
     * Initializes the controller class.
     */
    
    public void setID(Integer id){
          this.ID=id;
          if (this.ID != null) {
            System.out.println("Setting ID: " + this.ID);
            AppContext context = AppContext.getInstance();

            // Retrieve the user based on the ID
            for (Utilisateur u : context.getUsers()) {
                if (u.getPid() == this.ID) {
                    this.user = u;
                    break;
                }
            }
            name.setText(user.getNom());
            email.setText(user.getEmail());
            mn.setText(user.getMontant().toString());
             ObservableList<Seance> seances = FXCollections.observableArrayList(context.getCinema().getListedeseances());

    // Définir les colonnes du TableView
    IDSeance.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("Sid"));
    Titrefilm.setCellValueFactory(new PropertyValueFactory<Seance, String>("TitreFilm"));
    labelleS.setCellValueFactory(new PropertyValueFactory<Seance, String>("SalleLabelle"));
    Dateseance.setCellValueFactory(new PropertyValueFactory<Seance, Date>("date"));

    // Ajouter les séances au TableView
    seancesTable.setItems(seances);
          }
          
        
        
    }
    public Integer getID(){
        return this.ID;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void ClickedAfficherEvaluations(ActionEvent event) {
         
        try {
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Evaluationf.fxml"));
        Parent root = loader.load();
        EvaluationfController evaluations = loader.getController();
        evaluations.setID(ID);
        
        // Show the new stage
        Stage signinStage = new Stage();
        signinStage.setTitle("Liste des Evaluations");
        signinStage.setScene(new Scene(root));
        signinStage.show();
    } catch (IOException ex) {
            System.out.println("erreur");
    }
    }

    @FXML
    private void ClickedAfficherReservations(ActionEvent event) {
         
        try {
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeResrev.fxml"));
        Parent root = loader.load();
        ListeResrevController listreserv = loader.getController();
        listreserv.setID(ID);
        
        // Show the new stage
        Stage sgStage = new Stage();
        sgStage.setTitle("Liste Reservation");
        sgStage.setScene(new Scene(root));
        sgStage.show();
    } catch (IOException ex) {
            System.out.println("erreur");
    }
    }

    @FXML
    private void ClickedEvaluerFilm(ActionEvent event) {
        
         try {
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EvaluerFilm.fxml"));
        Parent root = loader.load();
        
        EvaluerFilmController evaluerfilm = loader.getController();
        evaluerfilm.setID(ID);
        
        // Show the new stage
        Stage resrvStage = new Stage();
       resrvStage.setTitle("Reserver SEance");
        resrvStage.setScene(new Scene(root));
       resrvStage.show();
    } catch (IOException ex) {
            System.out.println("erreur");
    }
    }

    @FXML
    private void ClickedAttribuerFilm(ActionEvent event) {
        
        try {
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationS.fxml"));
        Parent root = loader.load();
        ReservationSController reservation = loader.getController();
        reservation.setID(ID);
        // Show the new stage
        Stage resrvStage = new Stage();
       resrvStage.setTitle("Reserver SEance");
        resrvStage.setScene(new Scene(root));
       resrvStage.show();
    } catch (IOException ex) {
            System.out.println("erreur");
    }

        
    }

    @FXML
    private void QuiterButtonClicked(ActionEvent event) {
        Stage currentStage = (Stage) Quiter.getScene().getWindow();
    currentStage.close();
    try {
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        Parent root = loader.load();
        // Show the new stage
        Stage signinStage = new Stage();
        signinStage.setTitle("PageAjoutSeance");
        signinStage.setScene(new Scene(root,720, 530));
        signinStage.show();
    }   catch (IOException ex) {
        java.util.logging.Logger.getLogger(AccueilController.class.getName()).log(java.util.logging.Level.SEVERE,
                "Erreur lors du chargement du fichier FXML", ex);
    }
    
    
    
    }

    @FXML
    private void voirSeancesNouveaux(MouseEvent event) {
        
        
         AppContext context = AppContext.getInstance();

    // Récupérer les séances depuis le contexte
        ObservableList<Seance> seances = FXCollections.observableArrayList(context.getCinema().getListedeseances());

    // Définir les colonnes du TableView
    IDSeance.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("Sid"));
    Titrefilm.setCellValueFactory(new PropertyValueFactory<Seance, String>("TitreFilm"));
    labelleS.setCellValueFactory(new PropertyValueFactory<Seance, String>("SalleLabelle"));
    Dateseance.setCellValueFactory(new PropertyValueFactory<Seance, Date>("date"));

    // Ajouter les séances au TableView
    seancesTable.setItems(seances);
    for (Utilisateur u : context.getUsers()) {
                if (u.getPid() == this.ID) {
                    this.user = u;
                    break;
                }
            }
            name.setText(user.getNom());
            email.setText(user.getEmail());
            mn.setText(user.getMontant().toString());
        
    }

    @FXML
    private void ConsulterFilmClicked(ActionEvent event) {
        try {
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterFilm.fxml"));
        Parent root = loader.load();
        // Show the new stage
        Stage signinStage = new Stage();
        signinStage.setTitle("PageAjoutSeance");
        signinStage.setScene(new Scene(root,720, 530));
        signinStage.show();
    }   catch (IOException ex) {
        java.util.logging.Logger.getLogger(AccueilController.class.getName()).log(java.util.logging.Level.SEVERE,
                "Erreur lors du chargement du fichier FXML", ex);
    }
    }
    
}
   
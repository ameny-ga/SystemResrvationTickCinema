/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Vue;




import Commun.AppContext;
import Model.Cinema.Seance;
import Model.Commun.Date;
import Model.Commun.GenerateurDesID;
import Model.Utilisateurs.Administrateur;
import Model.Utilisateurs.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AdminPanelController implements Initializable {
    private Administrateur admin;
    private Integer ID ;
    @FXML
    private Button Quite;
    @FXML
    private Button AjouterSeance;
    @FXML
    private Button CalculerRevenue;
    @FXML
    private TableView<Utilisateur> ListUtilisateurs;
    @FXML
    private TableView<Seance> ListSeances;
    @FXML
    private TableColumn<Utilisateur, String> uNom;
    @FXML
    private TableColumn<Utilisateur, String> uEmail;
    @FXML
    private TableColumn<Utilisateur, Double> uMontant;
    @FXML
    private TableColumn<Utilisateur, Integer> uID;
    @FXML
    private TableColumn<Utilisateur, String> uPassword;
    @FXML
    private Button AlimenterCompte;
    //for test reasons (synthetic data)
    @FXML
    private Label nomAdmin;
    @FXML
    private Label emailAdmin;
    @FXML
    private Button AjouterFilm;
    @FXML
    private TableColumn<Seance, Integer> Idseance;
    @FXML
    private TableColumn<Seance, String > Titrefilm;
    @FXML
    private TableColumn<Seance, String> Labelsalle;
    @FXML
    private TableColumn<Seance, Date> Date;
    @FXML
    private Button Regenerer;
    @FXML
    private Button RegenererUtilisateur;
    @FXML
    private Label Dateact;
    @FXML
    private Label Revenue;
    @FXML
    private Button ConsulterListReservation;
    @FXML
    private Label NbrSalles;
    @FXML
    private Label NbrFilm;
    

    
   
    public void setID(Integer id ){
        this.ID = id ;
        if (this.ID != null) {
            System.out.println("Setting ID: " + this.ID);
            AppContext context = AppContext.getInstance();

            // Retrieve the user based on the ID
            for (Administrateur a : context.getAdmins()) {
                if (a.getPid() == this.ID) {
                    this.admin = a;
                    break;
                }
            }
            nomAdmin.setText(admin.getNom());
            emailAdmin.setText(admin.getEmail());
            
          }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AppContext context = AppContext.getInstance();
        ObservableList<Utilisateur> users = FXCollections.observableArrayList(context.getUsers());
        uID.setCellValueFactory(new PropertyValueFactory<Utilisateur , Integer>("Pid"));
        uNom.setCellValueFactory(new PropertyValueFactory<Utilisateur , String>("nom"));
        uEmail.setCellValueFactory(new PropertyValueFactory<Utilisateur , String>("email"));
        uPassword.setCellValueFactory(new PropertyValueFactory<Utilisateur , String>("password"));
        uMontant.setCellValueFactory(new PropertyValueFactory<Utilisateur , Double>("montant"));
        ListUtilisateurs.setItems(users);
    // Récupérer les séances depuis le contexte
        ObservableList<Seance> seances = FXCollections.observableArrayList(context.getAdmins().get(0).consulterSeance(context.getCinema()));

    // Définir les colonnes du TableView
    Idseance.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("Sid"));
    Titrefilm.setCellValueFactory(new PropertyValueFactory<Seance, String>("TitreFilm"));
    Labelsalle.setCellValueFactory(new PropertyValueFactory<Seance, String>("SalleLabelle"));
    Date.setCellValueFactory(new PropertyValueFactory<Seance, Date>("date"));

    // Ajouter les séances au TableView
    ListSeances.setItems(seances);
    

    // Ajouter les séances au TableView
    ListSeances.setItems(seances);
    Dateact.setText(context.getDateact().toString());
    NbrSalles.setText(String.valueOf(context.getCinema().getListedesalles().size()));
    NbrFilm.setText(String.valueOf(context.getCinema().getListedefilms().size()));
        
    }    

    @FXML
    private void QuiteClicked(ActionEvent event) {
        Stage currentStage = (Stage) Quite.getScene().getWindow();
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
        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE,
                "Erreur lors du chargement du fichier FXML", ex);
    }
    }
    

    @FXML
    private void AjouterSeanceClicked(ActionEvent event) {
        
        try {
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PageAjoutSeance.fxml"));
        Parent root = loader.load();
        // Show the new stage
        Stage signinStage = new Stage();
        signinStage.setTitle("PageAjoutSeance");
        signinStage.setScene(new Scene(root,720, 530));
        signinStage.show();
    }   catch (IOException ex) {
        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE,
                "Erreur lors du chargement du fichier FXML", ex);
    }
    }

    @FXML
    private void CalculerRevenueClicked(ActionEvent event) {
        AppContext context = AppContext.getInstance();
        double revenue = context.getCinema().calculerRevenue();
        Revenue.setText(String.valueOf(revenue));
        
    }

    @FXML
    private void AlimenterCompteClicked(ActionEvent event) {
       
        try {
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PageAlimenterCompte.fxml"));
        Parent root = loader.load();
        // Show the new stage
        Stage signinStage = new Stage();
        signinStage.setTitle("PageAjoutSeance");
        signinStage.setScene(new Scene(root,600, 400));
        signinStage.show();
    }   catch (IOException ex) {
        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE,
                "Erreur lors du chargement du fichier FXML", ex);
    }
    }

    @FXML
    private void AjouterFilmClicked(ActionEvent event) {
        try {
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PageAjouterFilm.fxml"));
        Parent root = loader.load();
        // Show the new stage
        Stage signinStage = new Stage();
        signinStage.setTitle("PageAjoutSeance");
        signinStage.setScene(new Scene(root,720, 530));
        signinStage.show();
    }   catch (IOException ex) {
        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE,
                "Erreur lors du chargement du fichier FXML", ex);
    }
    }

    @FXML
    private void RegenererButtonClicked(ActionEvent event) {
        AppContext context = AppContext.getInstance();

    // Récupérer les séances depuis le contexte
        ObservableList<Seance> seances = FXCollections.observableArrayList(context.getCinema().getListedeseances());

    // Définir les colonnes du TableView
    Idseance.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("Sid"));
    Titrefilm.setCellValueFactory(new PropertyValueFactory<Seance, String>("TitreFilm"));
    Labelsalle.setCellValueFactory(new PropertyValueFactory<Seance, String>("SalleLabelle"));
    Date.setCellValueFactory(new PropertyValueFactory<Seance, Date>("date"));

    // Ajouter les séances au TableView
    ListSeances.setItems(seances);
      NbrSalles.setText(String.valueOf(context.getCinema().getListedesalles().size()));
    NbrFilm.setText(String.valueOf(context.getCinema().getListedefilms().size()));  
    }

    @FXML
    private void RegenererUtilisateurButtonClicked(ActionEvent event) {
         ListUtilisateurs.getItems().clear();
        System.out.println("regeneer clicked");
        AppContext context = AppContext.getInstance();
        ObservableList<Utilisateur> users = FXCollections.observableArrayList(context.getUsers());
        uID.setCellValueFactory(new PropertyValueFactory<Utilisateur , Integer>("Pid"));
        uNom.setCellValueFactory(new PropertyValueFactory<Utilisateur , String>("nom"));
        uEmail.setCellValueFactory(new PropertyValueFactory<Utilisateur , String>("email"));
        uPassword.setCellValueFactory(new PropertyValueFactory<Utilisateur , String>("password"));
        uMontant.setCellValueFactory(new PropertyValueFactory<Utilisateur , Double>("montant"));
        ListUtilisateurs.setItems(users);
        
    
    }

    @FXML
    private void ConsulterListReservationButtonClicked(ActionEvent event) {
        try {
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterSeance.fxml"));
        Parent root = loader.load();
        // Show the new stage
        Stage signinStage = new Stage();
        signinStage.setTitle("PageAjoutSeance");
        signinStage.setScene(new Scene(root,720, 530));
        signinStage.show();
    }   catch (IOException ex) {
        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE,
                "Erreur lors du chargement du fichier FXML", ex);
    }
    }
    
}

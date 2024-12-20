/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Vue;

import Commun.AppContext;
import Model.Cinema.Cinema;
import Model.Utilisateurs.Administrateur;
import Model.Utilisateurs.Utilisateur;
import Model.Utilisateurs.Visiteur;
import Vue.SigninController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AccueilController implements Initializable {
    private String userType ; 
    @FXML
    private Button VisitorButton;
    @FXML
    private Button UserButton;
    @FXML
    private Button AdminButton;
    @FXML
    private Label CinemaName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        setUserType(null);
         AppContext context = AppContext.getInstance();
         Cinema cinema =context.getCinema();
         CinemaName.setText(cinema.getCname());
         System.out.println("list des film: "+context.getCinema().getListedefilms());
         System.out.println("list des seances : "+context.getCinema().getListedeseances());
         System.out.println("list des utilisateurs:"+context.getUsers());   
         System.out.println("list des reservation pour la seance d'id 1 : "+context.getCinema().getListedeseances().get(0).getListReservation());
    }    
    
    public void setUserType(String usertype){
        this.userType=usertype;
}
    public String  getUserType(){
         return this.userType;   
    }
    
    

    @FXML
    private void VisitorButtonClicked(ActionEvent event) {
        setUserType("visitor");
        // Close the current stage
    Stage currentStage = (Stage) AdminButton.getScene().getWindow();
    currentStage.close();

    try {
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signin.fxml"));
        Parent root = loader.load();

        // Get the controller and set the userType
        SigninController signinController = loader.getController();
        signinController.setUserType(this.userType);

        // Show the new stage
        Stage signinStage = new Stage();
        signinStage.setTitle("Signin");
        signinStage.setScene(new Scene(root ,720, 530));
        signinStage.show();
    } catch (IOException ex) {
        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE,
                "Erreur lors du chargement du fichier FXML", ex);
    }
    }

    @FXML
    private void UserButtonClicked(ActionEvent event) {
        setUserType("User");
        Stage currentStage = (Stage) AdminButton.getScene().getWindow();
    currentStage.close();

    

    try {
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signin.fxml"));
        Parent root = loader.load();
        // Get the controller and set the userType
        SigninController signinController = loader.getController();
        signinController.setUserType(this.userType);
        // Show the new stage
        Stage signinStage = new Stage();
        signinStage.setTitle("Signin");
        signinStage.setScene(new Scene(root ,720, 530));
        signinStage.show();
    } catch (IOException ex) {
        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE,
                "Erreur lors du chargement du fichier FXML", ex);
    }
    }

@FXML
private void AdminButtonClicked(ActionEvent event) {
    setUserType("Admin");
    // Close the current stage
    Stage currentStage = (Stage) AdminButton.getScene().getWindow();
    currentStage.close();

    try {
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signin.fxml"));
        Parent root = loader.load();

        // Get the controller and set the userType
        SigninController signinController = loader.getController();
        signinController.setUserType(this.userType);

        // Show the new stage
        Stage signinStage = new Stage();
        signinStage.setTitle("Signin");
        signinStage.setScene(new Scene(root, 720, 530));
        signinStage.show();
    } catch (IOException ex) {
        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE,
                "Erreur lors du chargement du fichier FXML", ex);
    }
}

    
}

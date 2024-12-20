/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package Vue;

import Commun.AppContext;
import Model.Cinema.Cinema;
import Model.Cinema.Film;
import Model.Cinema.Seance;
import Model.Cinema.categoryfilm;
import Model.Commun.Date;
import Model.Commun.Duree;
import Model.Commun.GenerateurDesID;
import Model.Utilisateurs.Administrateur;
import Model.Utilisateurs.Utilisateur;
import Model.Utilisateurs.Visiteur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SystemReservationTicketPourCinema extends Application {
    
    
    @Override
    public void start(Stage primaryStage) {
       AppContext context = AppContext.getInstance();
       context.getAdmins().add(new Administrateur(context.getGenid().getIdAdministrateur(),"Amine","amine@gmail.com","123456")); 
       context.getUsers().add(new Utilisateur(context.getGenid().getIdUtilisateur(),"Ameni","ameni@gmail.com","123456"));
       context.getUsers().add(new Utilisateur(context.getGenid().getIdUtilisateur(),"Eya","eya@gmail.com","1234"));
       context.getUsers().get(0).setMontant(200.0);
       context.getUsers().get(1).setMontant(300.0);
       Duree d1 = new Duree(1,30,0);
       Date da = new Date(2025,12,5);
       context.getCinema().getListedefilms().add(new Film("Inception","Terrintino",2015,d1,Set.of(categoryfilm.Action)));
       context.getCinema().getListedeseances().add(new Seance(context.getGenid().getIdSeance(),context.getCinema().getListedefilms().get(0),context.getCinema().getListedesalles().get(0),da));
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            Scene scene = new Scene(root, 720, 530);
            
            primaryStage.setTitle("GestionCinema");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(SystemReservationTicketPourCinema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

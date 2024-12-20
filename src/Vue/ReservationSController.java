/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Vue;

import Commun.AppContext;
import Model.Cinema.Reservation;
import Model.Cinema.Seance;
import Model.Exception.MontantInsuffisant;
import Model.Exception.SeanceDejaReserve;
import Model.Exception.SeanceNonExistante;
import Model.Exception.SeanceSaturee;
import Model.Utilisateurs.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amani
 */
public class ReservationSController implements Initializable {
    private Reservation res;
    private Seance seance=null;
    private Integer ID = null;
    @FXML
    private TextField ids;
    @FXML
    private Label idseanc;
    @FXML
    private Label idf;
    @FXML
    private Label iddate;
    @FXML
    private Label idsalle;
    @FXML
    private Label idprixs;
    @FXML
    private Label seancetrouve;
    @FXML
    private Label msgresr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    public void setID(Integer id){
       this.ID=id;
          if (this.ID != null) {
            
          }
          
            
    }
    
    
  

    @FXML
    private void onConfirmerReservation(ActionEvent event) {
        
         AppContext context = AppContext.getInstance();

    try {
        if(seance==null){
            
        }else{
            
        }

        // Appeler la méthode de réservation
        Utilisateur user = context.getUsers().get(ID-1);
        user.ReserverUneSeance(this.res, context.getCinema(), context.getCinema().getListedeseances().get(seance.getSid()-1));
        
        // Confirmation visuelle pour l'utilisateur
        msgresr.setText("Séance réservée avec succès !");
    } catch (SeanceDejaReserve e) {
        msgresr.setText("Erreur : La séance est déjà réservée !");
    } catch (SeanceSaturee e) {
        msgresr.setText("Erreur : La séance est complète !");
    } catch (MontantInsuffisant e) {
        msgresr.setText("Erreur : Montant insuffisant !");
    } catch (Exception e) {
        // Gérer d'autres erreurs éventuelles
        e.printStackTrace();
        msgresr.setText("Erreur inattendue !");
    }
        
        
    
    }

    @FXML
    private void SeanceCherchee(ActionEvent event) {
      
    boolean trouve = false;
    AppContext context = AppContext.getInstance();

    try {
        // Récupérer l'ID de la séance depuis l'entrée utilisateur
        String idseance = ids.getText().trim();
        int idseanceInt = Integer.parseInt(idseance); // Convertir en entier

        // Parcourir les séances
        for (Seance s : context.getCinema().getListedeseances()) {
            if (s.getSid() == idseanceInt) { // Comparer avec l'ID
                trouve = true;
                seancetrouve.setText("Oui");
                this.seance=s;

                // Mettre à jour les TextField avec les données trouvées
                idseanc.setText(String.valueOf(s.getSid())); 
                idf.setText(s.getTitreFilm());              
                idsalle.setText(s.getSalleLabelle());       
                iddate.setText(s.getDate().toString());

                // Calculer le prix de la réservation (affichage uniquement)
                Reservation reservation = new Reservation(context.getGenid().getIdReservation(), context.getCinema().getListedeseances().get(s.getSid()-1), context.getUsers().get(ID-1));
                this.res=reservation;
                res.CalculerPrixReservation();
                idprixs.setText(String.valueOf(res.getPrix()));
                

                break; // Quitter la boucle dès que la séance est trouvée
            }
        }

        // Si aucune séance n'a été trouvée
        if (!trouve) {
            seancetrouve.setText("Non");
        }

    } catch (NumberFormatException e) {
        // Afficher un message d'erreur en cas d'entrée invalide
        seancetrouve.setText("ID invalide !");
    } catch (Exception e) {
        // Gérer d'autres erreurs éventuelles
        e.printStackTrace();
        seancetrouve.setText("Erreur inattendue !");
    }
}

   
}
 



    


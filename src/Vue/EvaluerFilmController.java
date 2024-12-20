/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Vue;

import Commun.AppContext;
import Model.Cinema.Film;
import Model.Cinema.Reservation;
import Model.Utilisateurs.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amani
 */
public class EvaluerFilmController implements Initializable {
    private Integer ID ;
    @FXML
    private TextField filmIdInput;
    @FXML
    private Label titreFilmLabel;
    @FXML
    private Label noteLabel;
    @FXML
    private TextField noteInput;
    @FXML
    private Label commentaireLabel;
    @FXML
    private TextField commentaireInput;
    @FXML
    private Button btnAjouter;
    @FXML
    private Label messageLabel;
    AppContext context =AppContext.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void setID(Integer id){
        this.ID=id;
    }

    @FXML
    private void verifierFilm(ActionEvent event) 
    {
        
          String filmIdText = filmIdInput.getText().trim();
        if (filmIdText.isEmpty()) {
            messageLabel.setText("Veuillez entrer le Titre du film !");
            return;
        }

        try {
            

            // Vérifier si l'utilisateur a une réservation pour ce film
            Utilisateur user = context.getUsers().get(ID-1); // Utilisateur courant
            boolean filmVu = false;
            
             //String Filmuser=user.getHistoriqueReservation().stream().map(reservation -> reservation.getSeance().getFilm().getTitre().equals(filmIdText)).toString() ;
            for(Reservation r : user.getHistoriqueReservation()){
                if(r.getSeance().getFilm().getTitre().equals(filmIdText))
                {
                   filmVu=true;
                }
            }
            if (filmVu) {
               
                titreFilmLabel.setText("Titre du film : " +filmIdText  );
                afficherChamps(true);
                
            } else {
                messageLabel.setText("Ce film n'est pas encore regardé !");
                afficherChamps(false);
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("titre  du film invalide !");
        }
    }
     private void afficherChamps(boolean visible) {
        titreFilmLabel.setVisible(visible);
        noteLabel.setVisible(visible);
        noteInput.setVisible(visible);
        commentaireLabel.setVisible(visible);
        commentaireInput.setVisible(visible);
        btnAjouter.setVisible(visible);
    }

    @FXML
   
    private void ajouterAvis(ActionEvent event) {
    String noteText = noteInput.getText().trim(); // Assurez-vous que noteInput contient bien un texte
    String commentaire = commentaireInput.getText().trim();

    if (noteText.isEmpty() || commentaire.isEmpty()) {
        messageLabel.setText("Veuillez remplir tous les champs !");
        return;
    }

    try {
        // Vérification que la note est un entier
        int note = Integer.parseInt(noteText);
        if (note < 0 || note > 10) {
            messageLabel.setText("La note doit être entre 0 et 10 !");
            return;
        }

        // Récupérer l'utilisateur connecté
        Utilisateur user = context.getUsers().getFirst();

        // Trouver le film correspondant dans l'historique des réservations
        Optional<Film> filmOpt = user.getHistoriqueReservation().stream().map(reservation -> reservation.getSeance().getFilm()).filter(film -> film.getTitre() == filmIdInput.getText().trim()).findFirst();

        if (filmOpt.isPresent()) {
            Film filmreg = filmOpt.get();

            // Appeler la méthode pour évaluer un film
            user.EvaluerUnFilm(filmreg, note, commentaire);
            messageLabel.setText("Avis ajouté avec succès pour le film : " + filmreg.getTitre());

            // Réinitialiser les champs
            noteInput.clear();
            commentaireInput.clear();
        } else {
            messageLabel.setText("Ce film n'existe pas dans votre historique !");
        }

    } catch (NumberFormatException e) {
        messageLabel.setText("La note doit être un entier valide !");
    } catch (Exception e) {
        e.printStackTrace();
        messageLabel.setText("Une erreur inattendue s'est produite !");
    }
}



    

    @FXML
    private void confirmer(ActionEvent event) 
    {
      
    String noteText = noteInput.getText().trim();
    String commentaire = commentaireInput.getText().trim();

    if (noteText.isEmpty() || commentaire.isEmpty()) {
        messageLabel.setText("Veuillez remplir tous les champs !");
        return;
    }

    try {
        int note = Integer.parseInt(noteText);

        if (note < 0 || note > 10) {
            messageLabel.setText("La note doit être entre 0 et 10 !");
            return;
        }

        // Récupérer l'utilisateur connecté
        Utilisateur user = context.getUsers().get(ID-1);

        // Trouver le film correspondant dans l'historique des réservations
        Optional<Film> filmOpt = user.getHistoriqueReservation().stream()
                .map(reservation -> reservation.getSeance().getFilm())
                .filter(film -> film.getTitre().equals(filmIdInput.getText().trim()))
                .findFirst();

        if (filmOpt.isPresent()) {
            Film filmreg = filmOpt.get();

            // Appel de la méthode pour évaluer un film
            user.EvaluerUnFilm(filmreg, note, commentaire);

            messageLabel.setText("Avis ajouté pour : " + filmreg.getTitre());

            // Mise à jour de l'interface des évaluations automatiquement
         
            // Réinitialiser les champs après confirmation
            noteInput.clear();
            commentaireInput.clear();

        } else {
            messageLabel.setText("Ce film n'existe pas dans votre historique !");
        }
        
    } catch (NumberFormatException e) {
        messageLabel.setText("La note doit être un entier valide !");
    } catch (Exception e) {
        e.printStackTrace();
        messageLabel.setText("Une erreur inattendue est survenue !");
    }
}


    
    }
 
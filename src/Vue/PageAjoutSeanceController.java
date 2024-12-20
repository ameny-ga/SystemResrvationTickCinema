/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Vue;

import Commun.AppContext;
import Model.Cinema.Film;
import Model.Cinema.Salle;
import Model.Cinema.Seance;
import Model.Commun.Date;
import Model.Utilisateurs.Administrateur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class PageAjoutSeanceController implements Initializable {
    private Film film;
    private Salle salle ;
    @FXML
    private TextField Annee;
    @FXML
    private TextField Mois;
    @FXML
    private TextField Jours;
    @FXML
    private Button Ajouter;
    @FXML
    private TextField TitreFilm;
    @FXML
    private Label FilmTrouve;
    @FXML
    private TextField LabelSalle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterButtonClicked(ActionEvent event) {
 try {
        AppContext context = AppContext.getInstance();

        // Get and validate Salle
        String labelsalle = LabelSalle.getText().trim();
        if (labelsalle.isEmpty()) {
            throw new IllegalArgumentException("Le label de la salle ne peut pas être vide.");
        }

        Salle selectedSalle = null;
        for (Salle s : context.getCinema().getListedesalles()) {
            if (s.getLabelle().equals(labelsalle)) {
                selectedSalle = s;
                break;
            }
        }
        if (selectedSalle == null) {
            throw new IllegalArgumentException("La salle spécifiée n'existe pas.");
        }

        // Parse and validate date
        int annee, mois, jours;
        try {
            annee = Integer.parseInt(Annee.getText().trim());
            mois = Integer.parseInt(Mois.getText().trim());
            jours = Integer.parseInt(Jours.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Les valeurs de date doivent être des nombres valides.");
        }

        // Create Date object (assuming Date constructor requires year, month, day)
        Date date = new Date(annee , mois , jours); // Adjust for Date's offset years/months

        // Validate Film
        if (this.film == null) {
            throw new IllegalArgumentException("Aucun film sélectionné.");
        }

        // Create Seance
        Seance seance = new Seance(context.getGenid().getIdSeance(), this.film, selectedSalle, date);

        // Add Seance to context
        Administrateur.ajouterSeance(context.getCinema(), seance);
        System.out.println(context.getCinema().getListedeseances());

        // Confirm addition
        System.out.println("Séance ajoutée avec succès : " + seance);

    } catch (IllegalArgumentException e) {
        // Handle validation errors
        System.err.println("Erreur lors de l'ajout de la séance : " + e.getMessage());
    } catch (Exception e) {
        // Handle any unexpected errors
        System.err.println("Une erreur inattendue s'est produite : " + e.getMessage());
    }
    }

    @FXML
    private void RechercherbuttonClicked(ActionEvent event) {
           AppContext context = AppContext.getInstance();
    String titre = TitreFilm.getText().trim(); // Supprime les espaces inutiles autour du titre
    boolean trouve = false;

    for (Film f : context.getCinema().getListedefilms()) {
        if (f.getTitre().equals(titre)) { // Utilisation de equals pour comparer les contenus des chaînes
            trouve = true;
            this.film = f; // Stocke le film trouvé
            break; // Quitte la boucle dès que le film est trouvé
        }
    }

    if (trouve) {
        FilmTrouve.setText("Oui");
    } else {
        FilmTrouve.setText("Non");
    }
    }
    
}

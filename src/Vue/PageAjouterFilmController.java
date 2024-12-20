/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Vue;

import Commun.AppContext;
import Model.Cinema.Film;
import Model.Cinema.categoryfilm;
import Model.Commun.Duree;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class PageAjouterFilmController implements Initializable {

    @FXML
    private TextField Titre;
    @FXML
    private TextField Realisateur;
    @FXML
    private TextField AnneSortie;
    @FXML
    private TextField Genre;
    @FXML
    private Button AjouterFilm;
    @FXML
    private TextField heures;
    @FXML
    private TextField minutes;
    @FXML
    private TextField secondes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
private void AjouterFilmClicked(ActionEvent event) {
    // Obtain the instance of AppContext
   try {
        // Obtain the instance of AppContext
        AppContext context = AppContext.getInstance();

        // Read and validate inputs
        String titre = Titre.getText().trim();
        String realisateur = Realisateur.getText().trim();
        int anneeSortie;
        try {
            anneeSortie = Integer.parseInt(AnneSortie.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("L'année de sortie doit être un nombre valide.");
        }

        // Create Duree object
        int heuresValue, minutesValue, secondesValue;
        try {
            heuresValue = Integer.parseInt(heures.getText().trim());
            minutesValue = Integer.parseInt(minutes.getText().trim());
            secondesValue = Integer.parseInt(secondes.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Les valeurs de durée doivent être des nombres valides.");
        }
        Duree duree = new Duree(heuresValue, minutesValue, secondesValue);

        // Convert genre to Set<categoryfilm>
        String[] genresArray = Genre.getText().trim().split(",");
        Set<categoryfilm> genre = Set.of(genresArray).stream()
                .map(String::trim)
                .map(categoryfilm::valueOf)
                .collect(Collectors.toSet());

        // Create Film object
        Film film = new Film(titre, realisateur, anneeSortie, duree, genre);

        // Add the film to the global context
        context.getCinema().getListedefilms().add(film);

        // Confirm addition
        System.out.println("Film ajouté avec succès : " + film);
    } catch (IllegalArgumentException e) {
        // Handle validation errors
        System.err.println("Erreur lors de l'ajout du film : " + e.getMessage());
    } catch (Exception e) {
        // Handle any unexpected errors
        System.err.println("Une erreur inattendue s'est produite : " + e.getMessage());
    }
    
}

}

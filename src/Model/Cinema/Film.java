package Model.Cinema;

import Model.Utilisateurs.Utilisateur;
import java.util.ArrayList;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

import Model.Commun.Duree;
public record Film(
        String titre,
        String realisateur,
        int anneeSortie,
        Duree duree,
        Set<categoryfilm> genre
) {
    // Constructeur compact avec validation
    public Film {
        if (titre == null || titre.isBlank()) {
            throw new IllegalArgumentException("Le titre ne peut pas être vide ou nul.");
        }
        if (realisateur == null || realisateur.isBlank()) {
            throw new IllegalArgumentException("Le réalisateur ne peut pas être vide ou nul.");
        }
        if (anneeSortie < 1888) { // Premier film réalisé en 1888
            throw new IllegalArgumentException("L'année de sortie est invalide.");
        }
        if (duree == null) {
            throw new IllegalArgumentException("La durée du film ne peut pas être nulle.");
        }
        if (genre.size()==0) {
            throw new IllegalArgumentException("Le film doit avoir au moins un genre.");
        }
    }

    // Méthode pour afficher les détails du film
    @Override
    public String toString() {
        return String.format(
                "Film : %s%nRéalisateur : %s%nAnnée de sortie : %d%nDurée : %s%nGenres : %s",
                titre, realisateur, anneeSortie, duree, genre
        );
    }

    public String getTitre() {
        return titre;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public int getAnneeSortie() {
        return anneeSortie;
    }

    public Duree getDuree() {
        return duree;
    }
  

    public Set<categoryfilm> getGenre() {
        return genre;
    }

    
    public ArrayList<Evaluation> consulterLesEvaluation(ArrayList<Utilisateur> LU) {
        ArrayList<Evaluation> evaluations = new ArrayList<>();
        for (Utilisateur usr : LU) {
            // Filter evaluations for the given film and add to the list
            List<Evaluation> evals = usr.getHistoriqueEvaluation()
                    .stream()
                    .filter(e -> e.getfilm() == this) // Corrected condition
                    .collect(Collectors.toList());   // Corrected syntax
            evaluations.addAll(evals); // Add filtered evaluations to the main list
        }
        return(evaluations);
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Commun;

public class Duree  implements Comparable<Duree>{
    private int heures;
    private int minutes;
    private int secondes;

    // Constructeur
    public Duree(int heures, int minutes, int secondes) {
        if (heures < 0 || minutes < 0 || minutes >= 60 || secondes < 0 || secondes >= 60) {
            throw new IllegalArgumentException("Heures, minutes ou secondes invalides.");
        }
        this.heures = heures;
        this.minutes = minutes;
        this.secondes = secondes;
    }

    // Getters
    public int getHeures() {
        return heures;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSecondes() {
        return secondes;
    }

    // Convertir la durée en secondes
    public int enSecondes() {
        return heures * 3600 + minutes * 60 + secondes;
    }

    // Ajouter une autre durée
    public void ajouter(Duree autreDuree) {
        int totalSecondes = this.enSecondes() + autreDuree.enSecondes();
        this.heures = totalSecondes / 3600;
        totalSecondes %= 3600;
        this.minutes = totalSecondes / 60;
        this.secondes = totalSecondes % 60;
    }

    // Afficher la durée sous forme de chaîne
    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", heures, minutes, secondes);
    }

    @Override
    public int compareTo(Duree autreDuree) {
        // Comparer les heures
        if (this.heures != autreDuree.heures) {
            return this.heures - autreDuree.heures;
        }

        // Comparer les minutes si les heures sont identiques
        if (this.minutes != autreDuree.minutes) {
            return this.minutes - autreDuree.minutes;
        }

        // Comparer les secondes si les minutes sont identiques
        return this.secondes - autreDuree.secondes;
    }

    public void setHeures(int heures) {
        this.heures = heures;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSecondes(int secondes) {
        this.secondes = secondes;
    }


}


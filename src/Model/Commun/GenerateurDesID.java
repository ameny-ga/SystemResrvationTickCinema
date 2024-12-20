/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Commun;

public class GenerateurDesID {
    private int idReservation;
    private int idUtilisateur;
    private int idAdministrateur;
    private int idSeance;
    private int idCinema;


    public GenerateurDesID() {
        this.idReservation = 0;
        this.idUtilisateur = 0;
        this.idAdministrateur = 0;
        this.idSeance = 0;
        this.idCinema = 0;
    }

    public int getIdReservation() {
        idReservation++;
        return idReservation;
    }
    public int getIdUtilisateur() {
        idUtilisateur++;
        return idUtilisateur;
    }
    public int getIdAdministrateur() {
        idAdministrateur++;
        return idAdministrateur;
    }
    public int getIdSeance() {
        idSeance++;
        return idSeance;
    }
    public int getIdCinema() {
        idCinema++;
        return idCinema;
    }

}


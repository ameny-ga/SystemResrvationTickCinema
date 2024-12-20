package Commun;

import Model.Cinema.Cinema;
import Model.Cinema.Salle;
import Model.Cinema.categoryecran;
import Model.Commun.Date;
import Model.Commun.GenerateurDesID;
import Model.Utilisateurs.Administrateur;
import Model.Utilisateurs.Utilisateur;
import Model.Utilisateurs.Visiteur;

import java.util.ArrayList;

public class AppContext {
    private static AppContext instance;

    private GenerateurDesID genid;
    private ArrayList<Utilisateur> users;
    private ArrayList<Administrateur> admins;
    private ArrayList<Visiteur> visitors;
    private Cinema cinema;
    private Date dateact ;

    private AppContext() {
        // Initialize variables
        genid = new GenerateurDesID();
        users = new ArrayList<>();
        admins = new ArrayList<>();
        visitors = new ArrayList<>();
        cinema = new Cinema(genid.getIdCinema(), "GizAm", "Chargia");
        cinema.ajouterSalle(new Salle(categoryecran.e3D,20,"A"));
        cinema.ajouterSalle(new Salle(categoryecran.Imax,30,"B"));
    }

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    public GenerateurDesID getGenid() {
        return genid;
    }

    public ArrayList<Utilisateur> getUsers() {
        return users;
    }

    public ArrayList<Administrateur> getAdmins() {
        return admins;
    }

    public ArrayList<Visiteur> getVisitors() {
        return visitors;
    }

    public Cinema getCinema() {
        return cinema;
    }
    public void updateUser(Utilisateur updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getPid() == updatedUser.getPid()) {
                users.set(i, updatedUser);  // Replace the old user with the updated one
                System.out.println(users.get(i));
                break;  // Stop looping once the user is found and updated
            }
        }
    }
    public Date getDateact(){
        return Date.getDateActuelle();
    }
    
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


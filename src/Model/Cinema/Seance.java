/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Cinema;

import java.util.ArrayList;
import Model.Commun.Date;

public class Seance {
    private Integer Sid;
    private Film film;
    private Salle salle;
    private Date date;
    private ArrayList<Reservation> listReservation;



    public Seance(int Sid, Film film, Salle salle , Date date) {
        this.Sid = Sid;
        this.film = film;
        this.salle = salle;
        this.date = date;
        this.listReservation = new ArrayList<Reservation>();
    }
    public int getSid() {
        return Sid;
    }
    public Film getFilm() {
        return film;
    }
    public Salle getSalle() {
        return salle;
    }
    public Date getDate(){return date;}

    public void setDate(Date date) {
        this.date = date;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
    public void setSalle(Salle salle) {
        this.salle = salle;
    }
    public void setdate(Date date) {
        this.date = date;
    }
    public ArrayList<Reservation> getListReservation() {
        return listReservation;
    }
    public void setListReservation(ArrayList<Reservation> listReservation) {
        this.listReservation = listReservation;
    }
    public int getnbrplacesoccupes(){
        return this.listReservation.size();
    }
     public String getTitreFilm() {
        return film != null ? film.getTitre() : ""; // Récupère le titre du film
    }

    public String getSalleLabelle() {
        return salle != null ? salle.getLabelle() : ""; // Récupère le labelle de la salle
    }



    @Override
    public String toString() {
        return "Seance d'id : "+Sid+" dont le Film est : ( "+film.toString()+" )  la salle est : ("+salle.toString()+")"+"Date de seance est : "+date.toString();
    }
    //methode de comparaison pour la methode contains de arraylist
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Seance seance = (Seance) obj;
        return Sid == seance.getSid();  // Compare by unique ID (or other identifying attributes)
    }


    public double calculerRevenue(){
        double revenue = 0.0;
        for(Reservation res : listReservation){
            revenue+=res.getPrix();
        }
        return(revenue);
    }



}

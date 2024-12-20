package Model.Utilisateurs;
import Model.Cinema.Cinema;
import Model.Cinema.Seance;
import Model.Commun.Date;
import Model.Commun.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Visiteur extends Personne{

    private String email;

    public Visiteur(int pid ,String nom, String email){
        super(pid,nom);
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    // consulter les seances a venir
    public  ArrayList<Seance> consulterSeance(Cinema cinema){
        //Date acctuelle de la consultation ...
        Date dateAct = Date.getDateActuelle();
        ArrayList<Seance> seances = cinema.getListedeseances();
        List<Seance> L=seances.stream().filter(s -> s.getDate().compareTo(dateAct)>0).collect(Collectors.toList());
        ArrayList<Seance>seancesFutures = new ArrayList<>(seances);
        return seancesFutures;
    }

}

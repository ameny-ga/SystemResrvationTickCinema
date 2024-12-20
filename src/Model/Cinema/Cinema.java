package Model.Cinema;

import java.util.ArrayList;
import Model.Commun.Date;
import java.util.HashSet;
import java.util.Set;
public class Cinema {
    private int Cid;
    private String Cname;
    private String lieu;
    private ArrayList<Salle> listedesalles;
    private ArrayList<Seance> listedeseances;
    private ArrayList<Film> listedefilms;

    public Cinema(int Cid, String Cname, String lieu) {
        this.Cid = Cid;
        this.Cname = Cname;
        this.lieu = lieu;
        this.listedesalles = new ArrayList<Salle>();
        this.listedeseances = new ArrayList<Seance>();
        this.listedefilms = new ArrayList<Film>();
    }

    public int getCid() {
        return Cid;
    }

    public void setCid(int Cid) {
        this.Cid = Cid;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String Cname) {
        this.Cname = Cname;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public ArrayList<Salle> getListedesalles() {
        return listedesalles;
    }

    public void setListedesalles(ArrayList<Salle> listedesalles) {
        this.listedesalles = listedesalles;
    }

    public ArrayList<Seance> getListedeseances() {
        return listedeseances;
    }

    public void setListedeseances(ArrayList<Seance> listedeseances) {this.listedeseances = listedeseances;}


    public ArrayList<Film> getListedefilms() {
        return listedefilms;
    }
    public void setListedefilms(ArrayList<Film> listedefilms) {
        this.listedefilms = listedefilms;
    }

    @Override
    public String toString() {
        // Utilisation de stream pour afficher les détails des salle
        String detailsSalles = listedesalles.stream()
                .map(s -> s.toString())// Appel de toString() pour chaque salle
                .collect(java.util.stream.Collectors.joining("***"));
        String detailsSeance = listedeseances.stream().map(se -> se.toString()).collect(java.util.stream.Collectors.joining("-*-"));

        return "Model.Cinema{" +
                "Cid=" + Cid +
                ", Cname='" + Cname + '\'' +
                ", Lieu='" + lieu + '\'' +
                ", Details des salles: " + detailsSalles + '\'' +
                ", Details des seances: " + detailsSeance + '\'' +
                '}';
    }

    public boolean ajouterSalle(Salle salle) {

        for (Salle s : listedesalles) {
            if (s.getLabelle() == salle.getLabelle()) {
                System.out.println("Erreur : Une salle avec cet ID existe déjà.");
                return false;
            }
        }

        listedesalles.add(salle);
        System.out.println("Salle ajoutée avec succès");
        return true;
    }


    public Set<Salle> sallesOccupes(Date date) {
        Set<Salle> salleOccup = new HashSet<>();
        for (Seance seance : listedeseances) {
            if (seance.getDate().equals(date)) {
                salleOccup.add(seance.getSalle());
            }
        }
        return salleOccup;
    }



    public double calculerRevenue(){
        double revenue = 0.0;
        for(Seance seance : listedeseances){
            revenue+=seance.calculerRevenue();
        }
        return(revenue);
    }




}







package Model.Cinema;

import Model.Utilisateurs.Utilisateur;

public class Reservation {
    private int id;
    private Seance seance;
    private Utilisateur utilisateur;
    private Double prix;



    public Reservation(int id , Seance seance, Utilisateur utilisateur) {
        this.id = id;
        this.seance = seance;
        this.utilisateur = utilisateur;
        this.prix = 0.0;
    }
    @Override
    public String toString() {
        return "L'id de la Reservation est : "+id+"  affichage des detail sur la seacnce ( "+seance.toString()+") affichage de detail sur l'utilisateur ( nom = "+utilisateur.getNom()+" ) prix de cette reservation est : "+prix;
    }
    public int getId() {
        return id;
    }
    public Seance getSeance() {
        return seance;
    }
    public Integer getSeanceId() {
    return seance != null ? seance.getSid() : null;
}
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public String getUtilisateurNom(){
        return utilisateur.getNom();
    }
    public Double getPrix(){return prix;}


    public void CalculerPrixReservation(){
        this.prix+=20;
        if(this.seance.getSalle().getTypeecran()==categoryecran.Dolby_Cinema || seance.getSalle().getTypeecran()==categoryecran.Imax){
            this.prix+=20;
        }else{
            this.prix+=10;
        }
        int placerestantes = (this.seance.getSalle().getCapacite()-this.seance.getListReservation().size());
        if(placerestantes!=0){double increment = 5 * ((double) this.seance.getSalle().getCapacite() / placerestantes);
    this.prix += Double.parseDouble(String.format("%.3f", increment));}
        System.out.println("place restantes dans la seance est : "+placerestantes);
    }

}

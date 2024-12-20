package Model.Utilisateurs;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import Model.Cinema.Cinema;
import Model.Cinema.Seance;
import Model.Commun.Date;
import Model.Cinema.Reservation;
import Model.Cinema.Evaluation;
import Model.Cinema.Film;
import Model.Exception.EvaluationInvalid;
import Model.Exception.MontantInsuffisant;
import Model.Exception.SeanceDejaReserve;
import Model.Exception.SeanceNonExistante;
import Model.Exception.SeanceSaturee;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
public final class Utilisateur extends Personne {
    private String email;
    private String password;
    private ArrayList<Reservation> historiqueReservation;
    private ArrayList<Evaluation> historiqueEvaluation;
    private Double montant ; //montant de compte de l'utilisateur

    public Utilisateur(int pid , String nom , String e , String p){
        super(pid,nom);
        email = e;
        password = p;
        historiqueReservation = new ArrayList<>();
        historiqueEvaluation = new ArrayList<>();
        montant = 0.0;
    }
    public String getemail(){
        return email;
    }
    public String getEmail(){
        return email;
    }
    public String getpassword(){
        return password;
    }
    public String getPassword(){
        return password;
    }
    public ArrayList<Reservation> getHistoriqueReservation(){
        return historiqueReservation;
    }
    public ArrayList<Evaluation> getHistoriqueEvaluation(){
        return historiqueEvaluation;
    }
    public Double getMontant(){
        return montant;
    }
    public void setMontant(Double montant){
        this.montant = montant;
    }




    public boolean seanceEsteReserve(Seance seance){
        for(Reservation reservation : historiqueReservation){
            if(seance.getSid()==reservation.getSeance().getSid()){
                return true;
            }
        }return false;
    }



    public  ArrayList<Seance> consulterSeance(Cinema cinema){
        //Date acctuelle de la consultation ...
        Date dateAct = Date.getDateActuelle();
        ArrayList<Seance> seances = cinema.getListedeseances();
        List<Seance> L=seances.stream().filter(s -> s.getDate().compareTo(dateAct)>0).collect(Collectors.toList());
        ArrayList<Seance>seancesFutures = new ArrayList<>(L);
        return seancesFutures;
    }



    public void ReserverUneSeance ( Reservation res , Cinema cinema , Seance seance)throws SeanceDejaReserve, SeanceNonExistante , SeanceSaturee , MontantInsuffisant {
          ArrayList<Seance> seances = this.consulterSeance(cinema);
          if(seances.contains(seance)){
              Reservation reservation = new Reservation(res.getId(),seance,this);
              reservation.CalculerPrixReservation();
              if(this.getMontant()>=reservation.getPrix()){
                  if(seance.getnbrplacesoccupes()<seance.getSalle().getCapacite()){
                      if(this.seanceEsteReserve(seance)){
                          throw( new SeanceDejaReserve("La Seance est deja reserve"));
                      }else{
                          this.getHistoriqueReservation().add(reservation);
                          seance.getListReservation().add(reservation);
                          this.setMontant(this.getMontant()-reservation.getPrix());
                      }

                  }else{
                      throw(new SeanceSaturee("la seance est deja saturee "));
                  }
              }else{
                  throw(new MontantInsuffisant("Montant de l'utilisateur est insuffisant pour affecter cette trasaction"));
              }
          }else{
              throw(new SeanceNonExistante("Seance non Existant"));
          }

    }


    





    public void EvaluerUnFilm(Film film,double note, String commentaire) throws EvaluationInvalid
    { if (note < 1 || note > 20)
    {
        throw new EvaluationInvalid("La note doit être comprise entre 1 et 20");
    }
        Evaluation eval=new Evaluation(note, commentaire, film, this);
        historiqueEvaluation.add(eval);
    }// verification que le film est dans la list des films regarder par ce utilisateur


    public double montantUtilise(Cinema cinema)
    { double montant=0.0;
        for(Seance sc : cinema.getListedeseances()){
            ArrayList<Reservation> listres = sc.getListReservation();
            montant+= listres.stream().mapToDouble(Reservation::getPrix).sum();
        }
        return montant;
    }



    @Override
    public String toString() {
        return "Utilisateur{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", historiqueReservation=" + historiqueReservation.size()+
                ", LesReservation=" +historiqueReservation.stream().map(r->r.toString()).collect(Collectors.joining(","))+
                ", historiqueEvaluation=" + historiqueEvaluation.size() +
                ", lesReservation=" +historiqueEvaluation.stream().map(r->r.toString()).collect(Collectors.joining(","))+
                ", montant apres reservation=" + montant + '}';
    }
    
}

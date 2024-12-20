package Model.Utilisateurs;
import Model.Cinema.Seance;
import java.util.ArrayList;
import Model.Cinema.Cinema;
public abstract sealed class Personne permits Utilisateur , Visiteur, Administrateur {
    private int Pid;
    private String nom;
    public Personne (int Pid , String nom  ){
        this.Pid = Pid;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public int getPid() {
        return Pid;
    }

    public void setPid(int pid) {
        Pid = pid;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "Pid=" + Pid +
                ", nom='" + nom + '\'' +
                '}';
    }

    abstract  ArrayList<Seance>  consulterSeance(Cinema cinema);



}

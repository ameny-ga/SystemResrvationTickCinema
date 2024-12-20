package Model.Utilisateurs;
import Model.Cinema.Seance;
import Model.Cinema.Cinema;
import Model.Cinema.Salle;
import Model.Cinema.Film;
import Model.Commun.Date;
import Model.Exception.UtilisateurNotFound;


import Model.Exception.SeanceNonExistante;
import Model.Exception.SallesEstOccupe;



import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class Administrateur extends Personne implements Alimentable {
    private String email;
    private String password;

    public Administrateur(int id , String nom, String email, String password) {
        super(id,nom);
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrateur [email=" + email + ", password=" + password + "]";
    }

    @Override
    public  ArrayList<Seance> consulterSeance(Cinema cinema){
        //Date acctuelle de la consultation ...
        Date dateAct = Date.getDateActuelle();
        ArrayList<Seance> seances = cinema.getListedeseances();
        List<Seance> List =seances.stream().filter(s -> s.getDate().compareTo(dateAct)>0).collect(Collectors.toList());
        ArrayList<Seance>seancesFutures = new ArrayList<>(List);
        return seancesFutures;
    }
    @ Override
    public void AlimenterCompte(Utilisateur utilisateur , double montant){
        utilisateur.setMontant(utilisateur.getMontant()+montant);
    }

    public void annulerSeance(Cinema cinema , Seance seance) throws SeanceNonExistante {
        ArrayList<Seance> seancesFutures = this.consulterSeance(cinema);
        if(seancesFutures.contains(seance)){
            System.out.println("contains works");
            seancesFutures.remove(seance);
            cinema.setListedeseances(seancesFutures);

        }else{
            throw(new SeanceNonExistante("La seance a annuler n'existe pas"));
        }
    }



    public static void  ajouterSeance(Cinema cinema , Seance seance)
    {
        Set<Salle> salleOccupe = cinema.sallesOccupes(seance.getDate());
        if(salleOccupe.contains(seance.getSalle())){
            throw(new SallesEstOccupe());
        }else{
            cinema.getListedeseances().add(seance);
        }
    }


// un utilisateur ne peut pas reserver plus d'une fois a la meme seance.

    public ArrayList<Utilisateur> ConsulterListUtilisateur(Seance seance)
    {
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        List<Utilisateur>  listUtilisateurs = seance.getListReservation().stream().map(r->r.getUtilisateur()).collect(Collectors.toList());
        utilisateurs = (ArrayList<Utilisateur>) listUtilisateurs;
        return utilisateurs;
    }
    //mettre a jour la date  dans une seance
    public boolean mettreAJourSeance(Cinema cinema , Seance seance , Date date  ) {
        for (Seance s : cinema.getListedeseances())
        {
            if (seance.getSid() == s.getSid())
            {
                seance.setdate(date);
                System.out.println("Séance mise à jour avec succès.");
                return true;
            }
            return false;

        } return false;
    }
    //mettre a jour le film dans une seance
    public boolean mettreAJourSeance(Cinema cinema , Seance seance ,  Film film) {
        for (Seance s : cinema.getListedeseances())
        {
            if (seance.getSid() == s.getSid())
            {

                seance.setFilm(film);
                System.out.println("Séance mise à jour avec succès.");
                return true;
            }
            return false;

        } return false;
    }


      // mettre a jours la date et le film d'une seance
    public boolean mettreAJourSeance(Cinema cinema , Seance seance , Date date  , Film film) {
        for (Seance s : cinema.getListedeseances())
        {
            if (seance.getSid() == s.getSid())
            {
                seance.setdate(date);
                seance.setFilm(film);
                System.out.println("Séance mise à jour avec succès.");
                return true;
            }
            return false;

        } return false;
    }


    //consulter un utilisateur dans la base de donne ( not tested)
    public Utilisateur consulterUtilisateur(Utilisateur user , ArrayList<Utilisateur> L) throws UtilisateurNotFound {
        for(Utilisateur usr : L ){
            if(user == usr ){
                return usr;
            }
        }
        throw(new UtilisateurNotFound("user not found"));
    }


    public static void alimenterCarte(double montant , Utilisateur user){
        Alimentable a = (Utilisateur U,double M)->U.setMontant((U.getMontant()+montant));
            a.AlimenterCompte(user,montant);
    }
    


}


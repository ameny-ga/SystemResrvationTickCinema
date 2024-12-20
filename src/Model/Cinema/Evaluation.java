package Model.Cinema;
import Model.Utilisateurs.Utilisateur;
// cette classe desinite a evaluer un film

public class Evaluation {

    private double note ;
    private String commentaire;
    private Film film;
    private Utilisateur utilisateur;


    public Evaluation(double note , String commentaire , Film film, Utilisateur utilisateur) {
        this.note = note;
        this.commentaire = commentaire;
        this.film = film;
        this.utilisateur = utilisateur;

    }

    public double getNote(){
        return this.note;
    }
    public String getCommentaire(){
        return this.commentaire;
    }
    public String getFilm(){
        return this.film.getTitre();
    }
    public Film getfilm(){
        return this.film;
    }
    public String getUtilisateur(){
        return utilisateur.getNom();
    }
    @Override
    public String toString() {
        return "Evaluation par l'utilisateur : "+utilisateur.getNom()+"{" + "note=" + note + ", commentaire=" + commentaire + '}';

    }

}

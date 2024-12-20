/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Vue;

import Commun.AppContext;
import Model.Utilisateurs.Administrateur;
import Model.Utilisateurs.Utilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class PageAlimenterCompteController implements Initializable {
    private boolean trouve= false ;
    private Integer ID =null;
    @FXML
    private Button AlimenterCart;
   
    @FXML
    private Label NomUtilisateur;
    @FXML
    private Button RechercherUtilisateur;
    @FXML
    private TextField IdUtilisateur;
    @FXML
    private TextField Montant;
    @FXML
    private Label ErreurUtilisateurNonTrouve;
    @FXML
    private Label Succe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    @FXML
    private void RechercherUtilisateurButtonClicked(ActionEvent event) {
        AppContext context = AppContext.getInstance();
        Utilisateur user=null;
        Integer ID = Integer.parseInt(IdUtilisateur.getText());
        for(Utilisateur u : context.getUsers()){
            if(u.getPid()==ID){
                this.trouve=true;
                user=u;
                this.ID=user.getPid();
            }
        }
        if(!trouve){
            NomUtilisateur.setText("utilisateur non trouve");
        }else{
            NomUtilisateur.setText(user.getNom());
        }    
    }

    @FXML
    private void AlimenterCartClicked(ActionEvent event) {
        if(trouve){
            AppContext context = AppContext.getInstance();
            Double montant = Double.parseDouble(Montant.getText());
            for(Utilisateur u : context.getUsers()){
                if(u.getPid()==ID){
                    Administrateur.alimenterCarte(montant,u);
                    context.updateUser(u);
                    System.out.println(u);
                }
            }
            Succe.setText("Succes");
        }else{
            Succe.setText("Selectioner Un Utilisateur");
        }
        
    }

    
    
}

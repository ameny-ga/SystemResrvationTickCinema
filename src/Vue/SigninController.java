package Vue;

import Commun.AppContext;
import Model.Utilisateurs.Administrateur;
import Model.Utilisateurs.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class SigninController implements Initializable {
    private boolean signin ;
    @FXML
    private Label userType;
    @FXML
    private Button SigninButton;

    private String userTypeValue;
    @FXML
    private Button CancelButton;
    @FXML
    private TextField emailfield;
    @FXML
    private PasswordField passwordfield;
    @FXML
    private Label erreur;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        signin=false;
        // Ensure the userType label is updated if the value is already set
        if (userTypeValue != null) {
            userType.setText("User Type: " + userTypeValue);
        }
    }

    public void setUserType(String userTypeValue) {
        this.userTypeValue = userTypeValue;

        // Update the label if the FXML has been loaded
        if (userType != null) {
            userType.setText("User Type: " + userTypeValue);
        }
    }

    @FXML
    private void SigninButtonClicked(ActionEvent event) {
        AppContext context = AppContext.getInstance();
        // Handle sign-in logic here
        
    if(userTypeValue == "Admin"){
        Integer ID = -1 ;
        String email = emailfield.getText();
        String password = passwordfield.getText();
        System.out.println(email);System.out.println(password);
        for(Administrateur admin : context.getAdmins()){
            System.out.println(admin);
            if(email.equals(admin.getEmail())){
                if(password.equals(admin.getPassword())){
                    signin = true ;
                    ID=admin.getPid();
                }else{
                    erreur.setText("Wrong password");
                }
            }
        }
        if(erreur.getText()==""){
            erreur.setText("Admin introuvable");
        }
        if(signin){
            Stage currentStage = (Stage) SigninButton.getScene().getWindow();
    currentStage.close();
               try {
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPanel.fxml"));
        Parent root = loader.load();
        
        AdminPanelController adminController = loader.getController();
        adminController.setID(ID);

        // Show the new stage
        Stage signinStage = new Stage();
        signinStage.setTitle("AdminPanel");
        signinStage.setScene(new Scene(root, 1142 , 738));
        signinStage.show();
    } catch (IOException ex) {
        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE,
                "Erreur lors du chargement du fichier FXML", ex);
    }
        }
 
    } else if(userTypeValue=="User"){
        Integer ID=-1;
        String email = emailfield.getText();
        String password = passwordfield.getText();
        System.out.println(email);System.out.println(password);
        for(Utilisateur user : context.getUsers()){
            System.out.println(user);
            if(email.equals(user.getEmail())){
                if(password.equals(user.getPassword())){
                    signin = true ;
                    ID=user.getPid();
                }else{
                    erreur.setText("Wrong password");
                }
            }
        }
        if(erreur.getText()==""){
            erreur.setText("utilisateur introuvable");
        }
        if(signin){
            Stage currentStage = (Stage) SigninButton.getScene().getWindow();
            currentStage.close();
               try {
                   
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
        Parent root = loader.load();
                   
        UserController userController = loader.getController();
        userController.setID(ID);
                   
        
        
        // Show the new stage
        Stage signinStage = new Stage();
        signinStage.setTitle("UserPanel");
        signinStage.setScene(new Scene(root, 870 , 550));
        signinStage.show();
    } catch (IOException ex) {
        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE,
                "Erreur lors du chargement du fichier FXML", ex);
    }
                
              
               
        }
        
    }
    }

    @FXML
    private void CancelButtonClicked(ActionEvent event) {
        Stage currentStage = (Stage) SigninButton.getScene().getWindow();
    currentStage.close();
    try {
        // Load the Signin FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        Parent root = loader.load();

        // Show the new stage
        Stage signinStage = new Stage();
        signinStage.setTitle("WelcomePage");
        signinStage.setScene(new Scene(root, 720, 530));
        signinStage.show();
    } catch (IOException ex) {
        Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE,
                "Erreur lors du chargement du fichier FXML", ex);
    }
        
    }
}


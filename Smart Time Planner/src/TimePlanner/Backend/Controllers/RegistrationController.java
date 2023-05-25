package TimePlanner.Backend.Controllers;

import java.io.IOException;

import TimePlanner.Backend.Models.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {

    @FXML
    private TextField usernameField;

    @FXML
    private Label usernameErrorMessage;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label passwordErrorMessage;

    @FXML
    private TextField emailField;

    @FXML
    private Label emailErrorMessage;

    @FXML
    private TextField telephoneField;

    @FXML
    private Label telephoneErrorMessage;

    /*
     * 
     * 
     */

    @FXML
    private Button SignInButton;

    @FXML
    private Button SignUpButton;

    /*
     * 
     * 
     * 
     * 
     */

    @FXML
    public void SignUp() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        String telephone = telephoneField.getText();

        // SERIALIZED AUTOMATICALLY IN THE CLASS UTILISATEUR
        Utilisateur user = new Utilisateur(username, email, password, telephone);

        // ROUTING TO THE PAGE SIGNIN
        SignIn();

    }
    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * AUTHENTIFICATION
     */

    @FXML
    public void SignIn() {
        String PageRouter = "Login/Login.fxml";
        try {
            Parent next = FXMLLoader.load(getClass().getResource("../../Frontend/Pages/" + PageRouter));

            // Get the current scene
            Scene currentScene = SignInButton.getScene();

            // Set the root of the current scene to the Step2 root
            currentScene.setRoot(next);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

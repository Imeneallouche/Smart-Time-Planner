package TimePlanner.Backend.Controllers;

import java.io.IOException;

import TimePlanner.Backend.Models.Utilisateur;
import TimePlanner.Backend.Services.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void loginButtonClicked(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Perform login authentication and retrieve the Utilisateur object
        Utilisateur utilisateur = authenticate(username, password);

        if (utilisateur != null) {
            // Set the Utilisateur in the DataManager
            DataManager.getInstance().setUtilisateur(utilisateur);

            // Load the next page
            loadNextPage(event);
        } else {
            // Handle login failure
            showErrorMessage("Invalid username or password");
        }
    }

    private Utilisateur authenticate(String username, String password) {
        // Perform authentication logic and retrieve the Utilisateur object
        Utilisateur user = new Utilisateur(); // Replace this with your actual authentication logic
        return user;
    }

    private void loadNextPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
            Parent root = loader.load();

            // Access the controller for the next page
            // ProfileController profileController = loader.getController();

            // Pass the Utilisateur object to the ProfileController
            // profileController.setUtilisateur(DataManager.getInstance().getUtilisateur());

            // Show the next page
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showErrorMessage(String message) {
        // Show an error message to the user
        // ...
    }

}

package TimePlanner.Backend.Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import TimePlanner.Backend.Models.Tache;
import TimePlanner.Backend.Models.Utilisateur;
import TimePlanner.Backend.Services.DataManager;
import TimePlanner.Backend.Services.TaskManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private Label usernameErrorMessage;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label passwordErrorMessage;

    @FXML
    private Button SignInButton;

    @FXML
    private Button SignUpButton;

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * EVENT LISTENER FOR THE SIGNIN BUTTON CLICK
     */

    @FXML
    public void SignIn() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Perform login authentication and retrieve the Utilisateur object
        Utilisateur utilisateur = authenticate(username, password);

        if (utilisateur != null) {
            // Set the Utilisateur in the DataManager
            DataManager.getInstance().setUtilisateur(utilisateur);

            // Set the tasks of the user (automatic pannification) in the Task Manager
            ArrayList<Tache> tasks = new ArrayList<>();
            TaskManager.getInstance().setTasks(tasks);

            // Load the next page
            loadNextPage(utilisateur);
        }
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
    public void SignUp() {

    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * AUTHENTIFICATION
     */

    private Utilisateur authenticate(String username, String password) {

        String pseudo = username.toLowerCase().replaceAll(" ", "");
        String filename = "./src/TimePlanner/UsersInformation/" + pseudo + ".ser";

        File file = new File(filename);

        if (file.exists()) {
            Utilisateur utilisateur = null;
            try (FileInputStream fileInputStream = new FileInputStream(file);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

                utilisateur = (Utilisateur) objectInputStream.readObject();
                String storedUsername = utilisateur.getProfile().getNom();
                String storedPassword = utilisateur.getProfile().getPassword();

                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                    return utilisateur;
                } else {
                    passwordErrorMessage.setText("Invalid password");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Working Directory: " + System.getProperty("user.dir"));
            System.out.println("File Path: " + file.getAbsolutePath());
            usernameErrorMessage.setText("Username does not exist");
        }
        return null;
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * GO TO THE NEXT APPROPRIATE PAGE
     */

    private void loadNextPage(Utilisateur utilisateur) {

        String nextPage;

        // IF THIS IS THE FIRST TIME THE USER ENTERS The app (NO PROJECT YET)
        // WE REDIRECT HIM TO THE 2 INITIAL STEPS (CALENDAR & CRENEAUXLIBRES)
        if (utilisateur.getProjets_en_cours() == null || utilisateur.getProjets_en_cours().size() == 0) {
            nextPage = "../../Frontend/Pages/PeriodChoice/PeriodChoice.fxml";
        }

        // ELSE (IF HE ALREADY GOT PROJECTS AND ENTERED BEFORE)
        // WE REDIRECT HIM TO THE HOME PAGE
        else {
            nextPage = "../../Frontend/Pages/Profile/Profile.fxml";
        }

        try {

            Parent next = FXMLLoader.load(getClass().getResource(nextPage));

            // Get the current scene
            Scene currentScene = SignUpButton.getScene();

            // Set the root of the current scene to the Step2 root
            currentScene.setRoot(next);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

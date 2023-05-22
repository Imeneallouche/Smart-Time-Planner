package TimePlanner.Backend.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import TimePlanner.Backend.Models.Profile;
import TimePlanner.Backend.Models.Utilisateur;
import TimePlanner.Backend.Services.DataManager;

public class ProfileController implements Initializable {

    @FXML
    private TextField nomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField telephoneField;

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     */
    // name fo the file is : "username.ser"
    // username = name tolowercase + white spaces removed
    // private String username = nomField.getText().toLowerCase().replace(" ", "");

    Utilisateur utilisateur = DataManager.getInstance().getUtilisateur();

    /*
     * 
     * 
     * 
     * 
     * 
     */
    // Deserialize the profile object from the file and populate the text fields

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeProfile();
    }

    private void initializeProfile() {

        if (utilisateur != null) {
            nomField.setText(utilisateur.getProfile().getNom());
            emailField.setText(utilisateur.getProfile().getEmail());
            passwordField.setText(utilisateur.getProfile().getPassword());
            telephoneField.setText(utilisateur.getProfile().getTelephone());
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
     */

    // This method is called when the "Save Changes" button is clicked
    @FXML
    private void saveChanges() {
        // Retrieve the entered values from the text fields
        String name = nomField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String telephone = telephoneField.getText();

        Profile profile = new Profile(name, email, password, telephone);

        // Update the profile object with the new values
        utilisateur.setProfile(profile);

        // Update the data manager as well
        DataManager.getInstance().setUtilisateur(utilisateur);

        // Serialize the new utilisateur object to a file
        serializeProfile(utilisateur);
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */

    private void serializeProfile(Utilisateur utilisateur) {

        String username = utilisateur.getProfile().getNom().toLowerCase().replaceAll(" ", "");

        try {
            FileOutputStream fileOut = new FileOutputStream("./src/TimePlanner/UsersInformation/" + username + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(utilisateur);
            out.close();
            fileOut.close();
            System.out.println("Profile serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
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
     * 
     * 
     * HANDLE THE ROUTINGS TO DIFFERENT PAGES
     */
    @FXML
    private void handleRouting(MouseEvent event) {
        Label label = (Label) event.getSource();
        String labelText = label.getText();

        String PageRouter;
        boolean newPage = false;

        switch (labelText) {
            case "Plan manuel":
                PageRouter = "ManualPlan/ManualPlan.fxml";
                break;

            case "Plan automatique":
                PageRouter = "AutomaticPlan/AutomaticPlan.fxml";
                break;

            case "Historique":
                PageRouter = "Historique/Historique.fxml";
                break;

            case "Votre profile":
                PageRouter = "Profile/Profile.fxml";
                break;

            case "Calendrier actuel":
                newPage = true;
                PageRouter = "Calendar/TaskCalendar";
                break;

            case "Se déconnecter":
                PageRouter = "Login/Login.fxml";
                break;

            default:
                PageRouter = "Profile/Profile.fxml";
                break;
        }

        if (!newPage) {
            try {
                Parent next = FXMLLoader.load(getClass().getResource("../../Frontend/Pages/" + PageRouter));

                // Get the current scene
                Scene currentScene = nomField.getScene();

                // Set the root of the current scene to the Step2 root
                currentScene.setRoot(next);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else {
            // WE WILL LOAD RTHE OTHER PAGES IN A NEW PAGE
        }
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     */
    /*
     * private Profile deserializeProfile(String username) {
     * try {
     * FileInputStream fileIn = new
     * FileInputStream("./src/TimePlanner/UsersInformation/" + username + ".ser");
     * ObjectInputStream in = new ObjectInputStream(fileIn);
     * Profile profile = (Profile) in.readObject();
     * in.close();
     * fileIn.close();
     * System.out.println("Profile deserialized successfully.");
     * return profile;
     * } catch (IOException | ClassNotFoundException e) {
     * System.out.println("Error deserializing the profile");
     * e.printStackTrace();
     * }
     * return null;
     * }
     */

}

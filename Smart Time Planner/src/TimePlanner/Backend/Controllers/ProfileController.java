package TimePlanner.Backend.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
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

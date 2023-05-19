package TimePlanner.Backend.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.*;

import TimePlanner.Backend.Models.Profile;

public class ProfileController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField telephoneField;

    @FXML
    private TextField BdayField;

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
    private String username = nomField.getText().toLowerCase().replace(" ", "");

    private Profile profile;

    /*
     * 
     * 
     * 
     * 
     * 
     */
    // Deserialize the profile object from the file and populate the text fields
    private void initializeProfile() {
        profile = deserializeProfile(username);

        if (profile != null) {
            nomField.setText(profile.getNom());
            emailField.setText(profile.getEmail());
            passwordField.setText(profile.getPassword());
            telephoneField.setText(profile.getTelephone());
            BdayField.setText(profile.getBirthday());
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
        String dob = BdayField.getText();

        // Update the profile object with the new values
        profile.setNom(name);
        profile.setEmail(email);
        profile.setPassword(password);
        profile.setTelephone(telephone);
        profile.setBirthday(dob);

        // Serialize the profile object to a file
        serializeProfile(profile);
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

    private void serializeProfile(Profile profile) {
        try {
            FileOutputStream fileOut = new FileOutputStream(username + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(profile);
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

    private Profile deserializeProfile(String username) {
        try {
            FileInputStream fileIn = new FileInputStream(username + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Profile profile = (Profile) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Profile deserialized successfully.");
            return profile;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

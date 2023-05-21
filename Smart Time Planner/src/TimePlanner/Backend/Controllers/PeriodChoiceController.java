package TimePlanner.Backend.Controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import TimePlanner.Backend.Models.Projet;
import TimePlanner.Backend.Models.Utilisateur;
import TimePlanner.Backend.Services.DataManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PeriodChoiceController {

    @FXML
    private DatePicker firstday;

    @FXML
    private DatePicker lastday;

    @FXML
    private Label FirstErrorMessage;

    @FXML
    private Label SecondErrorMessage;

    @FXML
    private Button ScheduleButton;

    @FXML
    private TextField projectName;

    @FXML
    private Label ProjectNameErrorMessage;

    Utilisateur utilisateur = DataManager.getInstance().getUtilisateur();

    /*
     * 
     * 
     * 
     * 
     * 
     * SCHEDULE BUTTON EVENT LISTENER
     */

    @FXML
    private void CheckandSet() {

        LocalDate currentDate = LocalDate.now();
        LocalDate firstDate = firstday.getValue();
        LocalDate lastDate = lastday.getValue();
        String project = projectName.getText();

        boolean FirstMessageconditions = firstDate != null && firstDate.isAfter(currentDate);
        boolean SecondMessageConditions = lastDate != null && lastDate.isAfter(firstDate);
        boolean ProjectNameConditions = project != null && project != "";

        if (ProjectNameConditions) {

            // valid project name -> perform further actions
            ProjectNameErrorMessage.setVisible(false);

            if (FirstMessageconditions) {

                // Valid first day of schedule period, perform further actions
                FirstErrorMessage.setVisible(false);

                if (SecondMessageConditions) {

                    // Valid last day of schedule -> start action
                    SecondErrorMessage.setVisible(false);

                    // here we will store the in the project arraylist one project
                    // We will store its: name, first & last date of period
                    Projet Newproject = new Projet(project, firstDate, lastDate);
                    utilisateur.ajouterProjet(Newproject);
                    DataManager.getInstance().setUtilisateur(utilisateur);
                    serializeUser(utilisateur);
                    /*
                     * 
                     * 
                     * 
                     * 
                     */
                    // here we will go to the next page (redirection to the page of les creneau
                    // libres)
                    // Load CreaneauLibres.fxml file
                    try {
                        Parent step2Root = FXMLLoader
                                .load(getClass()
                                        .getResource("../../Frontend/Pages/CreneauxLibres/CreneauxLibres.fxml"));

                        // Get the current scene
                        Scene currentScene = ScheduleButton.getScene();

                        // Set the root of the current scene to the Step2 root
                        currentScene.setRoot(step2Root);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {

                    SecondErrorMessage.setVisible(true);
                }
            } else {
                FirstErrorMessage.setVisible(true);
            }

        } else {
            ProjectNameErrorMessage.setVisible(true);
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
     * SERIALIZE USER AFTER MODIFICATION
     */

    private void serializeUser(Utilisateur utilisateur) {

        String username = utilisateur.getProfile().getNom().toLowerCase().replaceAll(" ", "");

        try {
            FileOutputStream fileOut = new FileOutputStream("./src/TimePlanner/UsersInformation/" + username + ".ser",
                    false);
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
     * CANCEL BUTTON EVENT LISTENER
     */

    @FXML
    private void CancelandExit() {

        try {
            Parent step2Root = FXMLLoader
                    .load(getClass().getResource("../../Frontend/Pages/Login/Login.fxml"));

            // Get the current scene
            Scene currentScene = ScheduleButton.getScene();

            // Set the root of the current scene to the Step2 root
            currentScene.setRoot(step2Root);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

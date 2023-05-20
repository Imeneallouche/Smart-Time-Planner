package TimePlanner.Backend.Controllers;

import java.io.IOException;
import java.time.LocalDate;

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
        // boolean ProjectNameCondition =
        if (ProjectNameConditions) {
            // valid project name -> perform further actions
            projectName.setVisible(false);

            if (FirstMessageconditions) {
                // Valid first day of schedule period, perform further actions
                FirstErrorMessage.setVisible(false);

                if (SecondMessageConditions) {
                    // Valid last day of schedule -> start action
                    SecondErrorMessage.setVisible(false);
                    // here we will store the first and last date of the period
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
                                .load(getClass().getResource("../../Frontend/Pages/Profile/Profile.fxml"));

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
            projectName.setVisible(true);
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
                    .load(getClass().getResource("../../Frontend/Pages/Profile/Profile.fxml"));

            // Get the current scene
            Scene currentScene = ScheduleButton.getScene();

            // Set the root of the current scene to the Step2 root
            currentScene.setRoot(step2Root);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

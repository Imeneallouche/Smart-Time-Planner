package TimePlanner.Backend.Controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ManualPlanController {

    @FXML
    private Button PlanTask;

    @FXML
    private Button CheckCalendar;

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
                Scene currentScene = PlanTask.getScene();

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

}

package TimePlanner.Backend.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import TimePlanner.Backend.Models.Projet;
import TimePlanner.Backend.Models.Utilisateur;
import TimePlanner.Backend.Services.DataManager;
import TimePlanner.Backend.Services.ProjectManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProjectController implements Initializable {

    @FXML
    private Button AddProjectbutton;

    @FXML
    private Label projet1;
    @FXML
    private Label projet2;
    @FXML
    private Label projet3;
    @FXML
    private Label projet4;
    @FXML
    private Label projet5;
    @FXML
    private Label projet6;

    Utilisateur utilisateur = DataManager.getInstance().getUtilisateur();
    int nb = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Projet project : utilisateur.getProjets_en_cours()) {

            nb++;
            setProjectName(nb, project.getNom());

        }
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * HANDLE ROUTING BETWEEN PAGES
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

            case "Projets":
                PageRouter = "Projets/Projets.fxml";
                break;

            case "Votre profile":
                PageRouter = "Profile/Profile.fxml";
                break;

            case "Calendrier actuel":
                newPage = true;
                PageRouter = "Calendar/TaskCalendar.fxml";
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
                Scene currentScene = AddProjectbutton.getScene();

                // Set the root of the current scene to the Step2 root
                currentScene.setRoot(next);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else {
            // WE WILL LOAD RTHE OTHER PAGES IN A NEW PAGE

            try {
                // Load the desired page
                Parent nextPage = FXMLLoader.load(getClass().getResource("../../Frontend/Pages/" + PageRouter));

                // Create a new Stage
                Stage newStage = new Stage();
                newStage.setTitle("Calendar");

                // Set the content of the new stage to the loaded page
                Scene newScene = new Scene(nextPage);

                // Set the width and height of the new stage
                double stageWidth = 1028; // Specify the desired width
                double stageHeight = 740; // Specify the desired height

                newStage.setWidth(stageWidth);
                newStage.setHeight(stageHeight);

                // Set the scene of the new stage
                newStage.setScene(newScene);

                // Show the new stage
                newStage.show();

                // Close the current stage (optional)
                // Stage currentStage = (Stage) PlanTask.getScene().getWindow();
                // currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

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
     * HANDLE CLICKING ON ADDING A NEW PROJECT
     */

    @FXML
    private void handleAddProject() {

        String PageRouter = "PeriodChoice/PeriodChoice.fxml";
        try {
            Parent next = FXMLLoader.load(getClass().getResource("../../Frontend/Pages/" + PageRouter));

            // Get the current scene
            Scene currentScene = AddProjectbutton.getScene();

            // Set the root of the current scene to the Step2 root
            currentScene.setRoot(next);

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
     * SET NAME OF THE PROJECTS
     */
    private void setProjectName(int n, String name) {
        switch (nb) {
            case 1:
                projet1.setText(name);
                break;
            case 2:
                projet2.setText(name);
                break;
            case 3:
                projet3.setText(name);
                break;
            case 4:
                projet4.setText(name);
                break;
            case 5:
                projet5.setText(name);
                break;
        }

    }

    @FXML
    private void handleStats(MouseEvent event) {

        VBox vbox = (VBox) event.getSource();
        Label label = (Label) vbox.getChildren().get(1); // Assuming the Label is the second child element of the VBox
        String projectName = label.getText();

        for (Projet project : utilisateur.getProjets_en_cours()) {
            if (project.getNom().equals(projectName)) {

                // ADD THE PROJECT TO THE PROJECTMANAGER
                ProjectManager.getInstance().setProject(project);

                // OPEN THE NEW STATS PAGE
                String PageRouter = "Historique/Historique.fxml";
                try {
                    // Load the desired page
                    Parent nextPage = FXMLLoader.load(getClass().getResource("../../Frontend/Pages/" + PageRouter));

                    // Create a new Stage
                    Stage newStage = new Stage();
                    newStage.setTitle("Statistics of " + projectName);

                    // Set the content of the new stage to the loaded page
                    Scene newScene = new Scene(nextPage);

                    // Set the width and height of the new stage
                    double stageWidth = 700; // Specify the desired width
                    double stageHeight = 800; // Specify the desired height

                    newStage.setWidth(stageWidth);
                    newStage.setHeight(stageHeight);

                    // Set the scene of the new stage
                    newStage.setScene(newScene);

                    // Show the new stage
                    newStage.show();

                    // Close the current stage (optional)
                    // Stage currentStage = (Stage) PlanTask.getScene().getWindow();
                    // currentStage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}

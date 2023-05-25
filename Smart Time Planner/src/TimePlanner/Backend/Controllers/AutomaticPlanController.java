package TimePlanner.Backend.Controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import TimePlanner.Backend.Models.Creneau;
import TimePlanner.Backend.Models.Tache;
import TimePlanner.Backend.Models.TacheDecomposable;
import TimePlanner.Backend.Models.Utilisateur;
import TimePlanner.Backend.Services.DataManager;
import TimePlanner.Backend.Services.TaskManager;
import TimePlanner.Backend.Models.TaskComparator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AutomaticPlanController {

    @FXML
    private Button PlanTask;

    @FXML
    private Button CheckCalendar;

    @FXML
    private Button automaticSchedule;

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

            case "Projets":
                PageRouter = "Projets/Projets.fxml";
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
                Scene currentScene = PlanTask.getScene();

                // Set the root of the current scene to the Step2 root
                currentScene.setRoot(next);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else {
            // WE WILL LOAD THE OTHER PAGES IN A SEPERATE STAGE

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
     * 
     * 
     * HANDLE PLANNING TASK POP UP
     */

    @FXML
    private void handlePlanTask() {

        String PageRouter = "AutomaticPlanPOP/AutomaticPlanPOP.fxml";

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

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * HANDLE OPENING THE PAGE OF THE CALENDAR
     */

    @FXML
    private void handleCheckCalendar() {

        String PageRouter = "Calendar/TaskCalendar.fxml";

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
     * 
     * AUTOMATIC SCHEDULE SUUUUUIIIIIIIIIIIIIIIIII
     */
    @FXML
    private void LaunchAutomaticSchedule() {

        // BOOLEAN FOR MISSING SCHEDULED TASKS
        boolean missing = false;

        // BRING ALL THE TASKS INSERTED BY THE USER
        ArrayList<Tache> tasks = TaskManager.getInstance().getTasks();

        // BRING THE USER
        Utilisateur utilisateur = DataManager.getInstance().getUtilisateur();

        // WE WILL TAKE LE PROJET LE PLUS RECENT AN PUT THEM THERE
        int index = utilisateur.getProjets_en_cours().size() - 1;

        // GET THE FREE SLOTS OF TIMES OF THAT PROJECT
        ArrayList<ArrayList<Creneau>> freeslots = utilisateur.getProjets_en_cours().get(index).getPeriode()
                .getCreneaux();

        // GET DATE DEBUT DU PROJET
        LocalDate datedebutprojet = utilisateur.getProjets_en_cours().get(index).getPeriode().getStartDate();

        // Sort the tasks using the custom comparator
        // taking into consideration : priority, deadline and duration

        Collections.sort(tasks, new TaskComparator());

        for (Tache task : tasks) {

            boolean taskScheduled = false;
            int j = 0;

            // for (int j = 0; j < freeslots.size(); j++) {
            while (!taskScheduled && j < freeslots.size()) {

                int k = 0;
                // for (int k = 0; k < freeslots.get(j).size(); k++) {
                while (!taskScheduled && k < freeslots.get(j).size()) {
                    // RETRIEVE THE SLOT
                    Creneau slot = new Creneau(freeslots.get(j).get(k).getDebut(), freeslots.get(j).get(k).getFin());

                    long dureeslot = Duration.between(slot.getDebut(), slot.getFin()).toMinutes();

                    if (task instanceof TacheDecomposable) {
                        // ANOTHER STORY TO TELL
                        // CHECK WHETHER THE TIME SLOT FITS ON THE CRENEAU
                        if (task.getDureeTache() <= dureeslot) {

                            // CREATE LE CRENEAU DATE START + DATE START + DURATION OF TASK
                            Creneau creneau = new Creneau(slot.getDebut(),
                                    slot.getDebut().plusMinutes(task.getDureeTache()));

                            // CREATE THE SHCEDULED SIMPLE TASK
                            Tache scheduledtask = new Tache(task.getTitre(), task.getDescription(), task.getCategorie(),
                                    datedebutprojet.plusDays(j), creneau, task.getEtatRealisation(), true);

                            // ADD THE TASK TO THE ARRAYLIST OF TASKS
                            utilisateur.getProjets_en_cours().get(index).ajouterTache(scheduledtask);

                            // UPDATE THE REMAINING FREE SLOT IN THE DATA MANAGER
                            freeslots.get(j).get(k).setDebut(slot.getDebut().plusMinutes(task.getDureeTache()));

                            // LITERALLY BREAK FROM THE TWO LOOPS
                            break;
                        }

                        else {

                            // NOT FINISHED YET (STILL ON WORK)

                        }
                    }

                    else {

                        // CHECK WHETHER THE TIME SLOT FITS ON THE CRENEAU
                        if (task.getDureeTache() <= dureeslot) {

                            // CREATE LE CRENEAU DATE START + DATE START + DURATION OF TASK
                            Creneau creneau = new Creneau(slot.getDebut(),
                                    slot.getDebut().plusMinutes(task.getDureeTache()));

                            // CREATE THE SHCEDULED SIMPLE TASK
                            Tache scheduledtask = new Tache(task.getTitre(), task.getDescription(), task.getCategorie(),
                                    datedebutprojet.plusDays(j), creneau, task.getEtatRealisation(), true);

                            // ADD THE TASK TO THE ARRAYLIST OF TASKS
                            utilisateur.getProjets_en_cours().get(index).ajouterTache(scheduledtask);

                            // UPDATE THE REMAINING FREE SLOT IN THE DATA MANAGER
                            freeslots.get(j).get(k).setDebut(slot.getDebut().plusMinutes(task.getDureeTache()));

                            // LITERALLY BREAK FROM THE TWO LOOPS
                            break;
                        }
                    }
                    k++;
                }
                j++;
            }

            if (!taskScheduled) {
                missing = true;
            }

        }
        serializeUser(utilisateur);
    }

    /**
     * 
     * 
     * 
     * 
     * 
     * 
     * SERIALIZATION
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
}

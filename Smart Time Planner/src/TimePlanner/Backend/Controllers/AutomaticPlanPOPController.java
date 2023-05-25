package TimePlanner.Backend.Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import TimePlanner.Backend.Models.EtatRealisation;
import TimePlanner.Backend.Models.Tache;
import TimePlanner.Backend.Models.TacheDecomposable;
import TimePlanner.Backend.Services.TaskManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AutomaticPlanPOPController implements Initializable {

    @FXML
    private TextField NomTache;
    @FXML
    private TextField DescriptionTache;
    @FXML
    private TextField CategorieTache;
    @FXML
    private ComboBox<Integer> priority;
    @FXML
    private ComboBox<String> TypeTache;
    @FXML
    private DatePicker deadline;
    @FXML
    private TextField dureetache;
    @FXML
    private ComboBox<EtatRealisation> stateTache;

    /*
     * 
     */

    @FXML
    private Button Annuler;

    @FXML
    private Button Plannifier;

    @FXML
    private Label errorPlannifier;

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * SET THE VALUES OF THE COMBOBOXES
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // SET THE VALUES OF THE COMBOBOXES
        stateTache.setItems(FXCollections.observableArrayList(EtatRealisation.values()));
        priority.setItems(FXCollections.observableArrayList(1, 2, 3));
        TypeTache.setItems(FXCollections.observableArrayList("Tache simple", "Tache décomposable"));
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * EVENT LISTENER FOR THE ANNULER BUTTON (CLOSE THE WINDOW)
     */

    @FXML
    private void handleAnnuler() {

        Stage currentStage = (Stage) Plannifier.getScene().getWindow();
        currentStage.close();
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
     * EVENT LISTENER FOR THE ADD TASK
     */

    @FXML
    private void handlePlannifier() {

        // WE WILL BRING ALL THE VALUES FROM THE FRONTEND
        String nom = NomTache.getText();
        String description = DescriptionTache.getText();
        String categorie = CategorieTache.getText();

        String type = TypeTache.getValue();
        EtatRealisation state = stateTache.getValue();
        Integer prio = priority.getValue();
        LocalDate dead = deadline.getValue();

        boolean a = nom != null && nom != "";
        boolean b = description != null && description != "";
        boolean c = categorie != null && categorie != "";

        boolean d = type != null && state != null && prio != null && dead != null;
        boolean e = isNumerical(dureetache.getText());

        // CIF CONDITION VERIFIED PERFORM SCHEDULE
        if (a && b && c && d && e) {
            // LETSS FIX LA DUREE
            int duree = Integer.parseInt(dureetache.getText());

            // DIFFERENT INSTANCES FOR THE CLASSES
            if (type.equals("Tache simple")) {
                // DIRECTALLY ADD IT TO THE TASK MANAGER
                Tache task = new Tache(nom, description, categorie, null, null, state, false);
                TaskManager.getInstance().addTask(task);
            } else {

                Tache task = new TacheDecomposable(nom, description, duree, prio, dead, categorie, false);
                TaskManager.getInstance().addTask(task);
            }

            // NOW LETS CLOSE UP THE WINDOW
            Stage currentStage = (Stage) NomTache.getScene().getWindow();
            currentStage.close();

        } else {
            if (!c) {
                errorPlannifier.setText("Durée de la tache doit etre un entier");
            } else {
                errorPlannifier.setText("Missing values");
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
     */
    public static boolean isNumerical(String str) {
        try {
            int num = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
